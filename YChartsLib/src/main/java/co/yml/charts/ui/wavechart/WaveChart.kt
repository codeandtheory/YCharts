@file:OptIn(ExperimentalMaterialApi::class)

package co.yml.charts.ui.wavechart

import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.core.graphics.withRotation
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.YAxis
import co.yml.charts.axis.getXAxisScale
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.LinePointInfo
import co.yml.charts.common.extensions.*
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.drawUnderScrollMask
import co.yml.charts.ui.linechart.*
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.wavechart.AxisPosition.*
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import kotlinx.coroutines.launch
import kotlin.math.*
import co.yml.charts.ui.linechart.getMaxElementInYAxis as getMaxElementInLineYAxis


/**
 *
 * [WaveChart] compose method used for drawing a Wave Chart.
 * @param modifier :All modifier related property.
 * Data class [WaveChartData] to save all params needed to draw the wave chart.
 * @param waveChartData : Add data related to wave chart.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WaveChart(modifier: Modifier, waveChartData: WaveChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()
    if (accessibilitySheetState.isVisible && isTalkBackEnabled
        && waveChartData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        BackHandler {
            scope.launch {
                accessibilitySheetState.hide()
            }
        }
    }
    Surface(modifier = modifier.testTag("wave_chart")) {
        with(waveChartData) {
            var columnWidth by remember { mutableStateOf(0f) }
            val rowHeight by remember { mutableStateOf(0f) }
            var xOffset by remember { mutableStateOf(0f) }
            val bgColor = MaterialTheme.colors.surface
            var isTapped by remember { mutableStateOf(false) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
            var selectionTextVisibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(Point(0f, 0f)) }
            val wave = wavePlotData.lines.first()
            // Update must required values
            val xAxisData = xAxisData.copy(
                axisBottomPadding = bottomPadding,
            )
            val yAxisData = yAxisData.copy(
                axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() },
            )

            val (xMin, xMax, xAxisScale) = getXAxisScale(wave.dataPoints, xAxisData.steps)
            val (yMin, _, yAxisScale) = getYAxisScale(wave.dataPoints, yAxisData.steps)
            val maxElementInYAxis = getMaxElementInLineYAxis(yAxisScale, yAxisData.steps)

            ScrollableCanvasContainer(modifier = modifier
                .semantics {
                    contentDescription = waveChartData.accessibilityConfig.chartDescription
                }
                .testTag("scrollable_container")
                .clickable {
                    if (isTalkBackEnabled) {
                        scope.launch {
                            accessibilitySheetState.animateTo(
                                ModalBottomSheetValue.Expanded
                            )
                        }
                    }
                },
                calculateMaxDistance = { xZoom ->
                    xOffset = xAxisData.axisStepSize.toPx() * xZoom
                    getMaxScrollDistance(
                        columnWidth,
                        xMax,
                        xMin,
                        xOffset,
                        paddingRight.toPx(),
                        size.width,
                        containerPaddingEnd.toPx()
                    )
                },
                containerBackgroundColor = backgroundColor,
                isPinchZoomEnabled = isZoomAllowed,
                drawXAndYAxis = { _, _ ->
                    YAxis(
                        modifier = Modifier
                            .fillMaxHeight()
                            .onGloballyPositioned {
                                columnWidth = it.size.width.toFloat()
                            }, yAxisData = yAxisData
                    )
                },
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - paddingTop.toPx()).div(maxElementInYAxis))
                    xOffset = xAxisData.axisStepSize.toPx() * xZoom
                    val xLeft = columnWidth // To add extra space if needed
                    val pointsData = getMappingPointsToGraph(
                        wave.dataPoints, xMin, xOffset, xLeft, scrollOffset, yBottom, yMin, yOffset
                    )
                    val (cubicPoints1, cubicPoints2) = getCubicPoints(
                        pointsData
                    )
                    val yPointOfOrigin = yBottom - ((0.minus(yMin)) * yOffset)
                    val tapPointLocks = mutableMapOf<Int, Pair<Point, Offset>>()

                    var xPos = xLeft - scrollOffset

                    // Draw guide lines
                    gridLines?.let {
                        drawGridLines(
                            yBottom,
                            yAxisData.axisTopPadding.toPx(),
                            xLeft,
                            paddingRight,
                            scrollOffset,
                            pointsData.size,
                            xZoom,
                            xAxisScale,
                            yAxisData.steps,
                            xAxisData.axisStepSize,
                            it
                        )
                    }

                    // Draw cubic line using the points and form a line graph
                    val cubicPath = drawStraightOrCubicLineForWave(
                        pointsData, wave.waveStyle, yPointOfOrigin, xPos, WaveFillColor()
                    )

                    // Draw Lines and Points and AreaUnderLine
                    // Draw area under curve
                    drawShadowUnderLineAndIntersectionPoint(
                        cubicPath,
                        pointsData,
                        yPointOfOrigin,
                        wave,
                        yPointOfOrigin,
                        cubicPoints1,
                        cubicPoints2
                    )

                    // Draw the X-axis
                    for (index in 0..xAxisData.steps) {
                        drawXAxisLabel(
                            xAxisData,
                            index,
                            xAxisScale,
                            yPointOfOrigin,
                            xPos
                        )
                        drawAxisLineWithPointers(
                            xPos,
                            xAxisData,
                            xZoom,
                            xAxisScale,
                            yPointOfOrigin,
                            index != xAxisData.steps,
                            index
                        )
                        xPos += (xAxisData.axisStepSize.toPx() * (xZoom * xAxisScale))
                    }

                    // Draw column to make graph look scrollable under Yaxis
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                    pointsData.forEachIndexed { index, point ->
                        if (isTapped && point.isTapped(tapOffset.x, xOffset)) {
                            // Dealing with only one line graph hence tapPointLocks[0]
                            tapPointLocks[0] = wave.dataPoints[index] to point
                        }
                    }

                    val selectedOffset = tapPointLocks.values.firstOrNull()?.second
                    if (selectionTextVisibility && selectedOffset.isNotNull()) {
                        drawHighlightText(
                            identifiedPoint,
                            selectedOffset ?: Offset(0f, 0f),
                            wave.selectionHighlightPopUp
                        )
                    }
                    if (isTapped) {
                        val x = tapPointLocks.values.firstOrNull()?.second?.x
                        if (x != null) identifiedPoint =
                            tapPointLocks.values.map { it.first }.first()
                        drawHighLightOnSelectedPoint(
                            tapPointLocks,
                            columnWidth,
                            paddingRight,
                            yBottom,
                            wave.selectionHighlightPoint
                        )
                    }
                },
                onPointClicked = { offset: Offset, _: Float ->
                    isTapped = true
                    selectionTextVisibility = true
                    tapOffset = offset
                },
                onScroll = {
                    isTapped = false
                    selectionTextVisibility = false
                },
                onZoomInAndOut = {
                    isTapped = false
                    selectionTextVisibility = false
                })
        }
        if (isTalkBackEnabled) {
            with(waveChartData) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Color.White,
                    content = {
                        val linePoints = wavePlotData.lines.firstOrNull()?.dataPoints
                        LazyColumn {
                            items(linePoints?.size ?: 0) { index ->
                                Column {
                                    LinePointInfo(
                                        xAxisData.axisLabelDescription(
                                            xAxisData.labelData(
                                                index
                                            )
                                        ),
                                        linePoints?.get(index)?.description ?: "",
                                        wavePlotData.lines.firstOrNull()?.waveStyle?.color
                                            ?: Color.Transparent
                                    )
                                    ItemDivider(
                                        thickness = accessibilityConfig.dividerThickness,
                                        dividerColor = accessibilityConfig.dividerColor
                                    )
                                }
                            }
                        }
                    },
                    popUpTopRightButtonTitle = accessibilityConfig.popUpTopRightButtonTitle,
                    popUpTopRightButtonDescription = accessibilityConfig.popUpTopRightButtonDescription,
                    sheetState = accessibilitySheetState
                )
            }
        }
    }
}

/**
 *
 * DrawScope.drawShadowUnderLineAndIntersectionPoint extension method used  for drawing a
 * shadow below the line graph points and also drawing intersection points on the line graph.
 * @param cubicPath : Path used to draw the shadow
 * @param pointsData : List of the points on the Line graph.
 * @param yBottom : Offset of X-Axis starting position i.e shade to be drawn until.
 * @param line : line on which shadow & intersectionPoints has to be drawn.
 */
private fun DrawScope.drawShadowUnderLineAndIntersectionPoint(
    cubicPath: Path,
    pointsData: MutableList<Offset>,
    yBottom: Float,
    line: Wave,
    yPointOfOrigin: Float,
    cubicPoints1: MutableList<Offset>, cubicPoints2: MutableList<Offset>
) {
    //sree_ todo color applied here
    Log.i("check_points", "yBottom: $yBottom pointsData: $pointsData")
//    if (line.shadowUnderLine.isNotNull()) {
//        cubicPath.lineTo(pointsData.last().x, yBottom)
//        cubicPath.lineTo(pointsData.first().x, yBottom)
//        line.shadowUnderLine?.draw?.let { it(this, cubicPath, line.shadowUnderLine.color) }
//    }

    if (line.intersectionPoint.isNotNull()) {
        pointsData.forEach { offset ->
            drawPointOnLine(offset, line.intersectionPoint)
        }
    }
}

/**
 * DrawScope.drawAxisLineWithPointers extension method used for drawing the axis line and the
 * axis pointers.
 * @param xPos : X coordinate of the axis line
 * @param axisData : Axis data.
 * @param zoomScale : Zoom scale
 * @param xAxisScale : The scale of increment of X-Axis
 * @param yPos : Y coordinate of the axis line
 * @param canDrawEndLine : Boolean letting us know if it is the end point.
 */
private fun DrawScope.drawAxisLineWithPointers(
    xPos: Float,
    axisData: AxisData,
    zoomScale: Float,
    xAxisScale: Float,
    yPos: Float,
    canDrawEndLine: Boolean, // Added check to avoid drawing an extra line post the last point
    index: Int
) {
    with(axisData) {
        if (axisConfig.isAxisLineRequired) {
            if (canDrawEndLine) {
                val axisStepWidth = (axisStepSize.toPx() * (zoomScale * xAxisScale))
                drawLine(
                    axisLineColor,
                    Offset(xPos, yPos),
                    if (shouldDrawAxisLineTillEnd) {
                        Offset((xPos + (axisStepWidth / 2) + axisStepWidth), yPos)
                    } else {
                        //todo sree_ remove the commented code
                        Log.i(
                            "check_line_x_pos",
                            "index: $index x: ${xPos + axisStepWidth} y : $yPos"
                        )
                        Offset(xPos + axisStepWidth, yPos)
                    }, strokeWidth = axisLineThickness.toPx()
                )
            }
            drawLine(
                axisLineColor,
                Offset(xPos, yPos),
                Offset(xPos, yPos + indicatorLineWidth.toPx()),
                strokeWidth = axisLineThickness.toPx()
            )
        }
    }
}

/**
 * DrawScope.drawXAxisLabel extension method used for drawing the axis line labels.
 * @param xPos : X coordinate of the axis line
 * @param axisData : Axis data.
 * @param index : Index of label data.
 * @param xAxisScale : The scale of increment of X-Axis
 * @param yPos : Y coordinate of the axis line
 */
private fun DrawScope.drawXAxisLabel(
    axisData: AxisData,
    index: Int,
    xAxisScale: Float,
    yPos: Float,
    xPos: Float
) {
    with(axisData) {
        val xAxisTextPaint = TextPaint().apply {
            textSize = axisLabelFontSize.toPx()
            color = axisLabelColor.toArgb()
            textAlign = Paint.Align.LEFT
            typeface = axisData.typeface
        }
        val xLabel = labelData((index * xAxisScale).toInt())
        val labelHeight = xLabel.getTextHeight(xAxisTextPaint)
        val labelWidth = xLabel.getTextWidth(xAxisTextPaint)
        val ellipsizedText = TextUtils.ellipsize(
            xLabel,
            xAxisTextPaint,
            axisStepSize.toPx(),
            axisConfig.ellipsizeAt
        )
        drawContext.canvas.nativeCanvas.apply {
            val x = xPos - (labelWidth / 2)
            val y =
                yPos + labelHeight / 2 + indicatorLineWidth.toPx() + labelAndAxisLinePadding.toPx()
            withRotation(axisLabelAngle, x, y) {
                drawText(
                    if (axisConfig.shouldEllipsizeAxisLabel) ellipsizedText.toString() else xLabel,
                    x,
                    y,
                    xAxisTextPaint
                )
            }
        }
    }
}


fun DrawScope.drawStraightOrCubicLineForWave(
    pointsData: MutableList<Offset>,
    lineStyle: LineStyle,
    yPointOfOrigin: Float = 0f,
    xPos: Float = 0f,
    waveColor: WaveFillColor = WaveFillColor()
): Path {
    val path = Path()
    var startPoint = pointsData[0]
    var endPoint = pointsData[1]

    // slop and y intercept of x axis line
    val (m1, b1) = findSlopeAndYIntercept(
        Offset(xPos, yPointOfOrigin),
        Offset(size.width, yPointOfOrigin)
    )
    val wavePoints = mutableListOf<Offset>()

    wavePoints.add(startPoint)

    for (i in 1 until pointsData.size) {
        when (lineStyle.lineType) {
            is LineType.Straight -> {
                path.lineTo(startPoint.x, endPoint.y)
            }
            is LineType.SmoothCurve -> {
                val wavePointPosition =
                    findPointPosition(startPoint, endPoint, yPointOfOrigin, i)

                //if both points of the curve intersect on x axis
                if (wavePointPosition == INTERSECT) {
                    // slop and y intercept of point 2
                    val (m2, b2) = findSlopeAndYIntercept(startPoint, endPoint)

                    //assume there is 2 line which start from start and end point of the bezier curve
                    //find intersection points of the line
                    val intersectionPoint =
                        findLineIntersectionWithXAxis(m1, b1, m2, b2) ?: Offset(0f, 0f)

                    //add intersection point
                    wavePoints.add(intersectionPoint)

                    //set next start and end point to draw intersection point to next wave point
                    startPoint = intersectionPoint
                    endPoint = pointsData[i]

                    //add next wave point
                    wavePoints.add(endPoint)

                    //set next start and end point
                    if (i < pointsData.size - 1) {
                        startPoint = pointsData[i]
                        endPoint = pointsData[i + 1]
                    }
                } else {
                    // add wave point
                    wavePoints.add(endPoint)

                    //set next start and end point
                    if (i < pointsData.size - 1) {
                        startPoint = pointsData[i]
                        endPoint = pointsData[i + 1]
                    }
                }
            }
        }
    }

    //draw the path using wave points
    return drawWavePath(path, wavePoints, lineStyle, waveColor, yPointOfOrigin)
}

fun DrawScope.drawWavePath(
    path: Path,
    wavePoints: MutableList<Offset>,
    lineStyle: LineStyle,
    waveColor: WaveFillColor,
    yPointOfOrigin: Float
): Path {

    for (i in 1 until wavePoints.size) {
        val (cubicPoints1, cubicPoints2) = findWaveControlPoints(wavePoints[i - 1], wavePoints[i])

        path.moveTo(wavePoints[i - 1].x, wavePoints[i - 1].y)

        path.cubicTo(
            cubicPoints1.x,
            cubicPoints1.y,
            cubicPoints2.x,
            cubicPoints2.y,
            wavePoints[i].x,
            wavePoints[i].y
        )

        with(lineStyle) {
            drawPath(
                path,
                color = color,
                style = getDrawStyleForPath(lineStyle.lineType, lineStyle),
                alpha = alpha,
                colorFilter = colorFilter,
                blendMode = blendMode
            )
        }

        fillWavePath(i, wavePoints, path, yPointOfOrigin, lineStyle, waveColor)
    }

    return path
}

fun DrawScope.fillWavePath(
    index: Int,
    wavePoints: MutableList<Offset>,
    path: Path,
    yPointOfOrigin: Float,
    lineStyle: LineStyle,
    waveColor: WaveFillColor
) {
    val pointPosition =
        findPointPosition(wavePoints[index - 1], wavePoints[index], yPointOfOrigin, index)
    Log.i(
        "check_top_points",
        "index: $index pointPosition: $pointPosition $index p1: ${wavePoints[index - 1]} p2 ${wavePoints[index]}"
    )

    path.lineTo(wavePoints[index - 1].x, wavePoints[index - 1].y)
    path.lineTo(wavePoints[index - 1].x, yPointOfOrigin)
    path.lineTo(wavePoints[index].x, yPointOfOrigin)
    path.lineTo(wavePoints[index].x, wavePoints[index].y)

    var fillColor: Color = Color.Transparent
    if (pointPosition == TOP) {
        fillColor = waveColor.topColor
    } else if (pointPosition == BOTTOM) {
        fillColor = waveColor.bottomColor
    }

    with(lineStyle) {
        drawPath(
            path,
            color = fillColor,
            style = androidx.compose.ui.graphics.drawscope.Fill,
            alpha = 0.2f,
            colorFilter = colorFilter,
            blendMode = blendMode
        )
    }

    path.reset()
}

/**
 * Used to get the control points of the give curve
 * @param startPoint : start point of the curve
 * @param endPoint : endPoint of the curve
 */
fun findWaveControlPoints(startPoint: Offset, endPoint: Offset): Pair<Offset, Offset> {
    // Calculate the difference between the x-coordinates of the two points
    val dx = endPoint.x - startPoint.x
    // Set the value of K to control the shape of the curve
    val k = 0.3f
    // Calculate the control points of the curve
    val control1X = startPoint.x + k * dx
    val control1Y = startPoint.y
    val control2X = endPoint.x - k * dx
    val control2Y = endPoint.y

    return Pair(Offset(control1X, control1Y), Offset(control2X, control2Y))
}

/**
 * Represents the start and end point positions of the bezier curve
 * @property INTERSECT : Means both lines are not in the same region
 */
enum class AxisPosition {
    TOP,
    BOTTOM,
    INTERSECT
}

//todo _sree make it configurable
/**
 * hold the color values
 */
data class WaveFillColor(
    val topColor: Color = Color.Green,
    val bottomColor: Color = Color.Red
)

/**
 * Used to find the position of the start and end point of the bezier curve
 * @param p1 : start point
 * @param p2 : end point
 * @param yPointOfOrigin : point where x axis reside
 */
fun findPointPosition(
    p1: Offset,
    p2: Offset,
    yPointOfOrigin: Float,
    index: Int
): AxisPosition {
    return (if (p1.y > yPointOfOrigin && p2.y > yPointOfOrigin) {
        BOTTOM
    } else if (p1.y < yPointOfOrigin && p2.y < yPointOfOrigin) {
        TOP
    } else if (p1.y == yPointOfOrigin || p2.y == yPointOfOrigin) {
        if (p1.y == yPointOfOrigin) {
            if (p2.y > yPointOfOrigin) {
                BOTTOM
            } else {
                TOP
            }
        } else {
            if (p1.y > yPointOfOrigin) {
                BOTTOM
            } else {
                TOP
            }
        }
    } else {
        INTERSECT
    }).also {
        Log.i(
            "check_intersection_points",
            "index: $index p1: ${p1.y} p2: ${p2.y} yPointOfOrigin: $yPointOfOrigin result: $it"
        )
    }
}

/**
 * Return slop and y intercept of the line
 * @param p1 : points on a line 1
 * @param p2 : points on a line 2
 */
fun findSlopeAndYIntercept(p1: Offset, p2: Offset): Pair<Float, Float> {
    val m = (p2.y - p1.y) / (p2.x - p1.x)
    val b = p1.y - m * p1.x
    return Pair(m, b)
}

/**
 * Used to compute the intersection point of the lines
 * @param m1 : slop of line 1
 * @param b1 : y intercept of line 1
 * @param m2 : slop of line 2
 * @param b2 : y intercept of line 2
 */
fun findLineIntersectionWithXAxis(m1: Float, b1: Float, m2: Float, b2: Float): Offset? {
    if (m1 == m2) {
        return null // Lines are parallel
    }

    val x = (b2 - b1) / (m1 - m2)
    val y = m1 * x + b1

    return Offset(x, y)
}


