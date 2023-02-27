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
import androidx.compose.ui.graphics.drawscope.Fill
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
import co.yml.charts.ui.wavechart.model.AxisPosition
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import co.yml.charts.ui.wavechart.model.WaveFillColor
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


/**
 *
 * DrawScope.drawStraightOrCubicLine extension method used for drawing a straight/cubic line for a given Point(x,y).
 * @param pointsData : List of points to be drawn on the canvas
 * @param lineStyle : All styles related to the path are included in [LineStyle].
 * @param yPointOfOrigin : y position of x Axis
 * @param xPos :  start position of axis
 * @param waveColor : fill color
 */
fun DrawScope.drawStraightOrCubicLineForWave(
    pointsData: MutableList<Offset>,
    lineStyle: LineStyle,
    yPointOfOrigin: Float = 0f,
    xPos: Float = 0f,
    waveColor: WaveFillColor = WaveFillColor()
): Path {
    val path = Path()

    when (lineStyle.lineType) {
        is LineType.Straight -> {
            for (i in 1 until pointsData.size) {
                val startPoint = pointsData[i - 1]
                val endPoint = pointsData[i]
                path.lineTo(startPoint.x, endPoint.y)
            }
        }
        is LineType.SmoothCurve -> {

            //draw the path using wave points
            drawWavePath(
                path,
                pointsData,
                lineStyle,
                waveColor,
                yPointOfOrigin,
                xPos
            )
        }
    }

    return path
}

/**
 * Used to draw the wave
 * @param cubicPath : Represent the single cubic bezier curve path using the given points
 * @param  pointsData : curve points
 * @param lineStyle : styling for the path drawn in the line graph
 * @param waveColor: fill color
 * @param yPointOfOrigin : y position of x Axis
 * @param xPos : start position of axis
 */
fun DrawScope.drawWavePath(
    cubicPath: Path,
    pointsData: MutableList<Offset>,
    lineStyle: LineStyle,
    waveColor: WaveFillColor,
    yPointOfOrigin: Float,
    xPos: Float = 0f
): Path {
    // slop and y intercept of x axis line
    val (m1, b1) = findSlopeAndYIntercept(
        Offset(xPos, yPointOfOrigin),
        Offset(size.width, yPointOfOrigin)
    )

    for (i in 1 until pointsData.size) {
        val (m2, b2) = findSlopeAndYIntercept(pointsData[i - 1], pointsData[i])

        //assume there is 2 line which start from start and end point of the bezier curve
        //find intersection points of the line
        val intersectionPoint =
            findLineIntersectionWithXAxis(m1, b1, m2, b2) ?: Offset(0f, 0f)

        //find control points to draw the cubic curve
        val (cubicPoints1, cubicPoints2) = findWaveControlPoints(
            pointsData[i - 1],
            pointsData[i]
        )

        cubicPath.moveTo(
            pointsData[i - 1].x,
            pointsData[i - 1].y
        )
        //draw cubic bezier curve
        cubicPath.cubicTo(
            cubicPoints1.x,
            cubicPoints1.y,
            cubicPoints2.x,
            cubicPoints2.y,
            pointsData[i].x,
            pointsData[i].y
        )

        with(lineStyle) {
            drawPath(
                cubicPath,
                color = color,
                style = getDrawStyleForPath(lineStyle.lineType, lineStyle),
                alpha = alpha,
                colorFilter = colorFilter,
                blendMode = blendMode
            )
        }

        //Color Filling part//
        val pointPosition = findPointPosition(
            pointsData[i - 1],
            pointsData[i],
            yPointOfOrigin
        )

        val curvePoints =
            getCurvePoints(pointsData[i - 1], cubicPoints1, cubicPoints2, pointsData[i])

        if (pointPosition == AxisPosition.INTERSECT) {
            fillWavePath(
                curvePoints,
                yPointOfOrigin,
                intersectionPoint,
                waveColor
            )
        } else {
            //TODO sree_ not not accurate
            fillRegularWavePath(
                curvePoints,
                yPointOfOrigin,
                pointsData[i],
                pointPosition,
                waveColor
            )
        }
    }
    return cubicPath
}

/**
 * Used to get the points which helps to construct the bezier curve
 * @param startPoint : startPoint of the curve
 * @param cubicPoint1 : cubicPoint1 of the curve
 * @param cubicPoint2 : cubicPoint2 of the curve
 * @param endPoint : endPoint of the curve
 */
fun getCurvePoints(
    startPoint: Offset,
    cubicPoint1: Offset,
    cubicPoint2: Offset,
    endPoint: Offset
): List<Offset> {
    val start = 0.0
    val end = 1.0
    val step = 0.05

    //generate t values for constructing the cubic curve
    val values = generateSequence(start) { (it + step).roundTwoDecimal() }
        .takeWhile { it <= end }

    val curvePoints = mutableListOf<Offset>()
    for (t in values) {
        val point =
            findCubicCurvePoints(
                t,
                startPoint,
                cubicPoint1,
                cubicPoint2,
                endPoint
            )
        curvePoints.add(point)
    }

    return curvePoints.toList()
}

//TODO sree_ need to fix the border color issue
fun DrawScope.fillRegularWavePath(
    curvePoints: List<Offset>,
    yPointOfOrigin: Float,
    intersectionPoint: Offset,
    pointPosition: AxisPosition,
    waveColor: WaveFillColor
) {
    val curvePath = Path()
    val startPoint = curvePoints.first()
    curvePath.moveTo(startPoint.x, startPoint.y)

    for (i in 1 until curvePoints.size) {
        curvePath.lineTo(curvePoints[i - 1].x, curvePoints[i - 1].y)
        curvePath.lineTo(curvePoints[i].x, curvePoints[i].y)
    }

    val fillColor: Color = if (pointPosition == AxisPosition.BOTTOM) {
        waveColor.bottomColor
    } else {
        waveColor.topColor
    }
    drawPath(curvePath, color = fillColor, 0.5f, Fill)


    curvePath.moveTo(startPoint.x, startPoint.y)
    curvePath.lineTo(intersectionPoint.x, intersectionPoint.y)
    curvePath.lineTo(intersectionPoint.x, yPointOfOrigin)
    curvePath.lineTo(startPoint.x, yPointOfOrigin)

    drawPath(curvePath, color = fillColor, 0.3f, Fill)
}

/**
 * Used to fill the wave path using given color
 * @param curvePoints : points which create the bezier curve
 * @param yPointOfOrigin : y position of x Axis
 * @param intersectionPoint: where curve intersect with x axis
 * @param waveColor :fill color
 */
fun DrawScope.fillWavePath(
    curvePoints: List<Offset>,
    yPointOfOrigin: Float,
    intersectionPoint: Offset,
    waveColor: WaveFillColor
) {
    val bottomPath = Path()
    val topPath = Path()
    val startPoint = curvePoints.first()
    val endPoint = curvePoints.last()

    val isStartPointBottom =
        findPointPosition(startPoint, intersectionPoint, yPointOfOrigin) == AxisPosition.BOTTOM

    if (isStartPointBottom) {
        bottomPath.moveTo(startPoint.x, startPoint.y)
        topPath.moveTo(intersectionPoint.x, intersectionPoint.y)
    } else {
        bottomPath.moveTo(intersectionPoint.x, intersectionPoint.y)
        topPath.moveTo(startPoint.x, startPoint.y)
    }

    for (i in 1 until curvePoints.size) {
        if (curvePoints[i - 1].y > yPointOfOrigin) {
            //BOTTOM
            bottomPath.lineTo(curvePoints[i - 1].x, curvePoints[i - 1].y)
            bottomPath.lineTo(curvePoints[i].x, curvePoints[i].y)
        } else {
            //TOP
            topPath.lineTo(curvePoints[i - 1].x, curvePoints[i - 1].y)
            topPath.lineTo(curvePoints[i].x, curvePoints[i].y)
        }
    }

    // Fill rest of the bottom path
    if (isStartPointBottom) {
        bottomPath.moveTo(startPoint.x, startPoint.y)
        bottomPath.lineTo(intersectionPoint.x, intersectionPoint.y)
        bottomPath.lineTo(startPoint.x, yPointOfOrigin)
    } else {
        bottomPath.moveTo(intersectionPoint.x, intersectionPoint.y)
        bottomPath.lineTo(endPoint.x, endPoint.y)
        bottomPath.lineTo(endPoint.x, yPointOfOrigin)
    }

    drawPath(bottomPath, color = waveColor.bottomColor, 0.3f, Fill)

    // Fill rest of the top path
    if (isStartPointBottom) {
        topPath.moveTo(intersectionPoint.x, intersectionPoint.y)
        topPath.lineTo(endPoint.x, endPoint.y)
        topPath.lineTo(endPoint.x, yPointOfOrigin)
    } else {
        topPath.moveTo(startPoint.x, startPoint.y)
        topPath.lineTo(intersectionPoint.x, intersectionPoint.y)
        topPath.lineTo(startPoint.x, yPointOfOrigin)
    }

    drawPath(topPath, color = waveColor.topColor, 0.3f, Fill)
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
 * Used to find the position of the start and end point of the bezier curve
 * @param p1 : start point
 * @param p2 : end point
 * @param yPointOfOrigin : point where x axis reside
 */
fun findPointPosition(
    p1: Offset,
    p2: Offset,
    yPointOfOrigin: Float
): AxisPosition {
    return if (p1.y > yPointOfOrigin && p2.y > yPointOfOrigin) {
        AxisPosition.BOTTOM
    } else if (p1.y < yPointOfOrigin && p2.y < yPointOfOrigin) {
        AxisPosition.TOP
    } else if (p1.y == yPointOfOrigin) {
        if (p2.y > yPointOfOrigin) {
            AxisPosition.BOTTOM
        } else {
            AxisPosition.TOP
        }
    } else if (p2.y == yPointOfOrigin) {
        if (p1.y > yPointOfOrigin) {
            AxisPosition.BOTTOM
        } else {
            AxisPosition.TOP
        }
    } else {
        AxisPosition.INTERSECT
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

/**
 * Used to get the cubic points for given t value
 */
fun findCubicCurvePoints(
    t: Double,
    p0: Offset,
    p1: Offset,
    p2: Offset,
    p3: Offset
): Offset {
    // First level of interpolation
    val p01 = interpolate(t, p0, p1)
    val p12 = interpolate(t, p1, p2)
    val p23 = interpolate(t, p2, p3)

    // Second level of interpolation
    val p012 = interpolate(t, p01, p12)
    val p123 = interpolate(t, p12, p23)

    // Third level of interpolation
    val p0123 = interpolate(t, p012, p123)

    return p0123
}

fun interpolate(t: Double, p0: Offset, p1: Offset): Offset {
    val x = (1 - t) * p0.x + t * p1.x
    val y = (1 - t) * p0.y + t * p1.y
    return Offset(x.toFloat(), y.toFloat())
}

