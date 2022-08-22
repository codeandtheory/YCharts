package com.ygraph.components.graph.linegraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.getXAxisScale
import com.ygraph.components.common.extensions.RowClip
import com.ygraph.components.common.model.Point
import com.ygraph.components.graph.linegraph.model.LineGraphData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

/**
 *
 * LineGraph compose method used for drawing a Line Graph.
 * @param modifier :All modifier related property.
 * @see com.ygraph.components.graph.linegraph.model.LineGraphData Data class to save all params needed to draw the line graph.
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

            val axisData = AxisData.Builder()
                .yMaxValue(dataPoints.maxOf { it.y })
                .yStepValue(yStepValue)
                .xAxisStepSize(xStepSize)
                .xAxisPos(xAxisPos)
                .yAxisPos(yAxisPos)
                .yBottomPadding(LocalDensity.current.run { rowHeight.toDp() })
                .axisLabelFontSize(axisLabelFontSize)
                .yLabelData(yAxisLabelData)
                .xLabelData(xAxisLabelData)
                .yLabelAndAxisLinePadding(yLabelAndAxisLinePadding)
                .yAxisOffset(yAxisOffset)
                .xAxisSteps(xAxisSteps - 1)
                .build()

            val (xMin, xMax, _) = getXAxisScale(dataPoints, axisData.xAxisSteps)
            val yAxisSteps = (axisData.yMaxValue / axisData.yStepValue).toInt()
            val (yMin, _, yAxisScale) = getYAxisScale(
                dataPoints,
                (axisData.yMaxValue / axisData.yStepValue).toInt()
            )
            val maxElementInYAxis =
                getMaxElementInYAxis(yAxisScale, yAxisSteps)

            ScrollableCanvasContainer(
                modifier = modifier,
                calculateMaxDistance = { xZoom ->
                    // horizontalGap.value = lineGraphData.horizontalExtraSpace.toPx()
                    xOffset = axisData.xAxisStepSize.toPx() * xZoom
                    getMaxScrollDistance(
                        columnWidth,
                        xMax,
                        xMin,
                        xOffset,
                        paddingRight.toPx(),
                        size.width
                    )
                },
                isPinchZoomEnabled = isZoomAllowed,
                drawXAndYAxis = { scrollOffset, xZoom ->
                    YAxis(
                        modifier = Modifier
                            .height(250.dp)
                            .onGloballyPositioned {
                                columnWidth = it.size.width.toFloat()
                            },
                        axisData = axisData
                    )
                    XAxis(
                        axisData = axisData,
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
                        chartData = dataPoints
                    )
                },
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - paddingTop.toPx()) / maxElementInYAxis)
                    xOffset = axisData.xAxisStepSize.toPx() * xZoom
                    val xLeft = columnWidth // To add extra space if needed
                    val pointsData = getMappingPointsToGraph(
                        lineGraphData.dataPoints,
                        xMin,
                        xOffset,
                        xLeft,
                        scrollOffset,
                        yBottom,
                        yMin,
                        yOffset
                    )
                    val (cubicPoints1, cubicPoints2) = getCubicPoints(pointsData)

                    // Draw cubic line using the points and form a line graph
                    drawCubicLine(pointsData, cubicPoints1, cubicPoints2)

                    // Draw Lines and Points and AreaUnderLine
                    // Draw area under curve
                    drawAreaShadowUnderLine(pointsData, yBottom)

                    // Draw column to make graph look scrollable under Yaxis
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)
                })
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
): MutableList<Point> {
    val pointsData = mutableListOf<Point>()
    lineGraphPoints.forEachIndexed { _, point ->
        val (x, y) = point
        val x1 = ((x - xMin) * xOffset) + xLeft - scrollOffset
        val y1 = yBottom - ((y - yMin) * yOffset)
        pointsData.add(Point(x1, y1))
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
 */
fun getMaxScrollDistance(
    columnWidth: Float,
    xMax: Float,
    xMin: Float,
    xOffset: Float,
    paddingRight: Float,
    canvasWidth: Float
): Float {
    val xLastPoint =
        (xMax - xMin) * xOffset + columnWidth + paddingRight //+ horizontalGap.value
    return if (xLastPoint > canvasWidth) {
        xLastPoint - canvasWidth
    } else 0f
}

/**
 *
 * DrawScope.drawCubicLine extension method used for drawing a cubic line for a given Point(x,y).
 * @param pointsData : List of points to be drawn on the canvas
 * @param cubicPoints1 : List of average left side values for a given Point(x,y).
 * @param cubicPoints2 : List of average right side values for a given Point(x,y).
 */
private fun DrawScope.drawCubicLine(
    pointsData: MutableList<Point>,
    cubicPoints1: MutableList<Point>,
    cubicPoints2: MutableList<Point>
) {
    val path = Path()
    path.moveTo(pointsData.first().x, pointsData.first().y)

    for (i in 1 until pointsData.size) {
        path.cubicTo(
            cubicPoints1[i - 1].x,
            cubicPoints1[i - 1].y,
            cubicPoints2[i - 1].x,
            cubicPoints2[i - 1].y,
            pointsData[i].x,
            pointsData[i].y
        )
    }

    drawPath(path, color = Color.Blue, style = Stroke(width = 8f))
}

/**
 *
 * DrawScope.drawPointOnLine extension method  used for drawing a circle/mark on a line for a given Point(x,y).
 * @param offset : Point at which circle/mark has to be drawn.
 */
private fun DrawScope.drawPointOnLine(offset: Point) {
    // Move all params to builder class for customization
    drawCircle(
        Color.Blue,
        5.dp.toPx(),
        Offset(offset.x, offset.y),
        1.0f,
        Fill,
        null,
        DrawScope.DefaultBlendMode
    )
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
 * DrawScope.drawAreaShadowUnderLine extension method used  for drawing a shades below the line graph points.
 * @param pointsData : List of the points on the Line graph.
 * @param yBottom : Offset of X-Axis starting position i.e shade to be drawn until.
 */
private fun DrawScope.drawAreaShadowUnderLine(
    pointsData: MutableList<Point>,
    yBottom: Float
) {
    val pointUnderAreaPath = Path()
    pointsData.forEachIndexed { index, offset ->
        if (index == 0) {
            pointUnderAreaPath.moveTo(offset.x, yBottom)
        }
        pointUnderAreaPath.lineTo(offset.x, offset.y)
        drawPointOnLine(offset)
    }
    val last = pointsData.last()
    val first = pointsData.first()
    pointUnderAreaPath.lineTo(last.x, yBottom)
    pointUnderAreaPath.lineTo(first.x, yBottom)
    drawPath(
        pointUnderAreaPath,
        Color.Blue,
        0.1f,
        Fill,
        null,
        DrawScope.DefaultBlendMode
    )
}

/**
 *
 * getCubicPoints method provides left and right average value for a given point to get a smooth curve.
 * @param pointsData : List of the points on the Line graph.
 */
fun getCubicPoints(pointsData: List<Point>):
        Pair<MutableList<Point>, MutableList<Point>> {
    val cubicPoints1 = mutableListOf<Point>()
    val cubicPoints2 = mutableListOf<Point>()

    for (i in 1 until pointsData.size) {
        cubicPoints1.add(
            Point(
                (pointsData[i].x + pointsData[i - 1].x) / 2,
                pointsData[i - 1].y
            )
        )
        cubicPoints2.add(
            Point(
                (pointsData[i].x + pointsData[i - 1].x) / 2,
                pointsData[i].y
            )
        )
    }
    return Pair(cubicPoints1, cubicPoints2)
}
