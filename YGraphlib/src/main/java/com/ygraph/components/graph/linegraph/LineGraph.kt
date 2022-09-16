package com.ygraph.components.graph.linegraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.getXAxisScale
import com.ygraph.components.common.extensions.RowClip
import com.ygraph.components.common.extensions.drawGridLines
import com.ygraph.components.common.extensions.isNotNull
import com.ygraph.components.common.model.Point
import com.ygraph.components.graph.linegraph.model.*
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

/**
 *
 * [LineGraph] compose method used for drawing a Line Graph.
 * @param modifier :All modifier related property.
 * Data class [LineGraphData] to save all params needed to draw the line graph.
 * @param lineGraphData : Add data related to line graph.
 */
@Composable
fun LineGraph(modifier: Modifier, lineGraphData: LineGraphData) {
    Column {
        with(lineGraphData) {
            var columnWidth by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            var xOffset by remember { mutableStateOf(0f) }
            val bgColor = MaterialTheme.colors.surface
            var isTapped by remember { mutableStateOf(false) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
            var selectionTextVisibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(Point(0f, 0f)) }

            // Update must required values
            val xAxisData = xAxisData.copy(axisBottomPadding = bottomPadding)
            val yAxisData = yAxisData.copy(
                axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() },
                axisTopPadding = paddingTop
            )

            val (xMin, xMax, xAxisScale) = getXAxisScale(line.dataPoints, xAxisData.steps)
            val (yMin, _, yAxisScale) = getYAxisScale(line.dataPoints, yAxisData.steps)
            val maxElementInYAxis =
                getMaxElementInYAxis(yAxisScale, yAxisData.steps)

            ScrollableCanvasContainer(
                modifier = modifier,
                calculateMaxDistance = { xZoom ->
                    // horizontalGap.value = lineGraphData.horizontalExtraSpace.toPx()
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
                drawXAndYAxis = { scrollOffset, xZoom ->
                    YAxis(
                        modifier = Modifier
                            .fillMaxHeight()
                            .onGloballyPositioned {
                                columnWidth = it.size.width.toFloat()
                            },
                        yAxisData = yAxisData
                    )
                    XAxis(
                        xAxisData = xAxisData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.BottomStart)
                            .onGloballyPositioned {
                                rowHeight = it.size.height.toFloat()
                            }
                            .clip(
                                RowClip(
                                    columnWidth,
                                    paddingRight
                                )
                            ),
                        xStart = columnWidth,
                        scrollOffset = scrollOffset,
                        zoomScale = xZoom,
                        graphData = line.dataPoints
                    )
                },
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - paddingTop.toPx()) / maxElementInYAxis)
                    xOffset = xAxisData.axisStepSize.toPx() * xZoom
                    val xLeft = columnWidth // To add extra space if needed
                    val pointsData = getMappingPointsToGraph(
                        lineGraphData.line.dataPoints,
                        xMin,
                        xOffset,
                        xLeft,
                        scrollOffset,
                        yBottom,
                        yMin,
                        yOffset
                    )
                    val (cubicPoints1, cubicPoints2) = getCubicPoints(pointsData)
                    val tapPointLocks = mutableMapOf<Int, Pair<Point, Offset>>()

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
                    val cubicPath =
                        drawStraightOrCubicLine(
                            pointsData,
                            cubicPoints1,
                            cubicPoints2,
                            line.lineStyle
                        )

                    // Draw Lines and Points and AreaUnderLine
                    // Draw area under curve
                    drawShadowUnderLineAndIntersectionPoint(cubicPath, pointsData, yBottom, line)

                    // Draw column to make graph look scrollable under Yaxis
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                    pointsData.forEachIndexed { index, point ->
                        if (isTapped && point.isTapped(tapOffset.x, xOffset)) {
                            // Dealing with only one line graph hence tapPointLocks[0]
                            tapPointLocks[0] = lineGraphData.line.dataPoints[index] to point
                        }
                    }

                    val selectedOffset = tapPointLocks.values.firstOrNull()?.second
                    if (selectionTextVisibility && selectedOffset.isNotNull()) {
                        drawHighlightText(
                            identifiedPoint,
                            selectedOffset ?: Offset(0f, 0f),
                            line.selectionHighlightPopUp
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
                            line.selectionHighlightPoint
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
                }
            )
        }
    }
}

/**
 *
 * returns the list of transformed points supported to be drawn on the container using the input points .
 * @param lineGraphPoints :Input data points
 * @param xMin: Min X-Axis value.
 * @param xOffset : Total distance between two X-Axis points.
 * @param xLeft: Total left padding in X-Axis.
 * @param scrollOffset : Total scrolled offset.
 * @param yBottom : Bottom start offset for X-Axis.
 * @param yMin : Min Y-Axis value.
 * @param yOffset : Distance between two Y-Axis points.
 */
fun getMappingPointsToGraph(
    lineGraphPoints: List<Point>,
    xMin: Float,
    xOffset: Float,
    xLeft: Float,
    scrollOffset: Float,
    yBottom: Float,
    yMin: Float,
    yOffset: Float,
): MutableList<Offset> {
    val pointsData = mutableListOf<Offset>()
    lineGraphPoints.forEachIndexed { _, point ->
        val (x, y) = point
        val x1 = ((x - xMin) * xOffset) + xLeft - scrollOffset
        val y1 = yBottom - ((y - yMin) * yOffset)
        pointsData.add(Offset(x1, y1))
    }
    return pointsData
}

/**
 *
 * returns the max scrollable distance based on the points to be drawn along with padding etc.
 * @param columnWidth : Width of the Y-Axis.
 * @param xMax : Max X-Axis value.
 * @param xMin: Min X-Axis value.
 * @param xOffset: Total distance between two X-Axis points.
 * @param paddingRight : Padding at the end of the canvas.
 * @param canvasWidth : Total available canvas width.
 * @param containerPaddingEnd : Container inside padding end after the last point of the graph.
 */
fun getMaxScrollDistance(
    columnWidth: Float,
    xMax: Float,
    xMin: Float,
    xOffset: Float,
    paddingRight: Float,
    canvasWidth: Float,
    containerPaddingEnd: Float = 0f
): Float {
    val xLastPoint =
        (xMax - xMin) * xOffset + columnWidth + paddingRight + containerPaddingEnd
    return if (xLastPoint > canvasWidth) {
        xLastPoint - canvasWidth
    } else 0f
}

/**
 *
 * DrawScope.drawStraightOrCubicLine extension method used for drawing a straight/cubic line for a given Point(x,y).
 * @param pointsData : List of points to be drawn on the canvas
 * @param cubicPoints1 : List of average left side values for a given Point(x,y).
 * @param cubicPoints2 : List of average right side values for a given Point(x,y).
 * @param lineStyle : All styles related to the path are included in [LineStyle].
 */
fun DrawScope.drawStraightOrCubicLine(
    pointsData: MutableList<Offset>,
    cubicPoints1: MutableList<Offset>,
    cubicPoints2: MutableList<Offset>,
    lineStyle: LineStyle
): Path {
    val path = Path()
    path.moveTo(pointsData.first().x, pointsData.first().y)
    for (i in 1 until pointsData.size) {
        when (lineStyle.lineType) {
            is LineType.Straight -> {
                path.lineTo(pointsData[i].x, pointsData[i].y)
            }
            is LineType.SmoothCurve -> {
                path.cubicTo(
                    cubicPoints1[i - 1].x,
                    cubicPoints1[i - 1].y,
                    cubicPoints2[i - 1].x,
                    cubicPoints2[i - 1].y,
                    pointsData[i].x,
                    pointsData[i].y
                )
            }
        }
    }
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
    return path
}

/**
 *
 * Returns the Drawstyle for the path.
 * @param lineType : Type of the line [LineType]
 * @param lineStyle : The style for the path [lineStyle]
 */
private fun getDrawStyleForPath(
    lineType: LineType,
    lineStyle: LineStyle
): DrawStyle = if (lineType.isDotted) Stroke(
    width = lineStyle.width, pathEffect = PathEffect.dashPathEffect(lineType.intervals)
) else lineStyle.style


/**
 *
 * DrawScope.drawPointOnLine extension method  used for drawing a circle/mark on a line for a given Point(x,y).
 * @param offset : Point at which circle/mark has to be drawn.
 */
private fun DrawScope.drawPointOnLine(offset: Offset, intersectionPoint: IntersectionPoint?) {
    intersectionPoint?.draw?.let { it(this, offset) }
}

/**
 *
 * DrawScope.drawUnderScrollMask extension method used  for drawing a rectangular mask to make graph scrollable under the YAxis.
 * @param columnWidth : Width of the rectangular mask here width of Y Axis is used.
 * @param paddingRight : Padding given at the end of the graph.
 * @param bgColor : Background of the rectangular mask.
 */
private fun DrawScope.drawUnderScrollMask(columnWidth: Float, paddingRight: Dp, bgColor: Color) {
    drawRect(
        bgColor,
        Offset(0f, 0f),
        Size(columnWidth, size.height)
    )
    drawRect(
        bgColor,
        Offset(size.width - paddingRight.toPx(), 0f),
        Size(paddingRight.toPx(), size.height)
    )
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
fun DrawScope.drawShadowUnderLineAndIntersectionPoint(
    cubicPath: Path,
    pointsData: MutableList<Offset>,
    yBottom: Float,
    line: Line
) {
    if (line.shadowUnderLine.isNotNull()) {
        cubicPath.lineTo(pointsData.last().x, yBottom)
        cubicPath.lineTo(pointsData.first().x, yBottom)
        line.shadowUnderLine?.draw?.let { it(this, cubicPath) }
    }
    if (line.intersectionPoint.isNotNull()) {
        pointsData.forEach { offset ->
            drawPointOnLine(offset, line.intersectionPoint)
        }
    }
}


/**
 *
 * getCubicPoints method provides left and right average value for a given point to get a smooth curve.
 * @param pointsData : List of the points on the Line graph.
 */
fun getCubicPoints(pointsData: List<Offset>):
        Pair<MutableList<Offset>, MutableList<Offset>> {
    val cubicPoints1 = mutableListOf<Offset>()
    val cubicPoints2 = mutableListOf<Offset>()

    for (i in 1 until pointsData.size) {
        cubicPoints1.add(
            Offset(
                (pointsData[i].x + pointsData[i - 1].x) / 2,
                pointsData[i - 1].y
            )
        )
        cubicPoints2.add(
            Offset(
                (pointsData[i].x + pointsData[i - 1].x) / 2,
                pointsData[i].y
            )
        )
    }
    return Pair(cubicPoints1, cubicPoints2)
}

/**
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param selectionHighlightPopUp : Data class with all styling related info [SelectionHighlightPopUp]
 */
fun DrawScope.drawHighlightText(
    identifiedPoint: Point,
    selectedOffset: Offset,
    selectionHighlightPopUp: SelectionHighlightPopUp?
) {
    selectionHighlightPopUp?.run {
        draw(selectedOffset, identifiedPoint)
    }
}

/**
 *
 * DrawScope.drawHighLightOnSelectedPoint extension method used for drawing and  highlight the selected
 * point when dragging.
 * @param dragLocks : List of points to be drawn on the canvas and that can be selected.
 * @param columnWidth : Width of the Axis in the left or right.
 * @param paddingRight : Padding given to the right side of the canvas.
 * @param yBottom : Start position from below of the canvas.
 * @param selectionHighlightPoint : Data class to define all the styles to be drawn in [SelectionHighlightPoint]
 */
private fun DrawScope.drawHighLightOnSelectedPoint(
    dragLocks: MutableMap<Int, Pair<Point, Offset>>,
    columnWidth: Float,
    paddingRight: Dp,
    yBottom: Float,
    selectionHighlightPoint: SelectionHighlightPoint?
) {
    if (selectionHighlightPoint.isNotNull()) {
        dragLocks.values.firstOrNull()?.let { (_, location) ->
            val (x, y) = location
            if (x >= columnWidth && x <= size.width - paddingRight.toPx()) {
                selectionHighlightPoint?.draw?.let { it(this, Offset(x, y)) }
                if (selectionHighlightPoint?.isHighlightLineRequired == true) {
                    selectionHighlightPoint.drawHighlightLine(
                        this,
                        Offset(x, yBottom),
                        Offset(x, y)
                    )
                }
            }
        }
    }
}
