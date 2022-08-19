package com.ygraph.components.graph.linegraph

import android.graphics.PointF
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
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.getXAxisScale
import com.ygraph.components.common.extensions.RowClip
import com.ygraph.components.graph.linegraph.model.LineGraphData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

@Composable
fun LineGraph(lineGraphData: LineGraphData) {
    Column {
        with(lineGraphData) {
            var columnWidth by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val bgColor = MaterialTheme.colors.surface

            val axisData = AxisData.Builder()
                .yMaxValue(100f)
                .yStepValue(20f)
                .yBottomPadding(LocalDensity.current.run { rowHeight.toDp() })
                .axisLabelFontSize(14.sp)
                .yLabelData(yAxisLabelData)
                .xLabelData(xAxisLabelData)
                .yLabelAndAxisLinePadding(20.dp)
                .yAxisOffset(20.dp)
                .xAxisSteps(lineChartData.size - 1)
                .build()
            val (xMin, xMax, xAxisScale) = getXAxisScale(lineChartData, axisData.xAxisSteps)
            val yAxisSteps = (axisData.yMaxValue / axisData.yStepValue).toInt()
            val (yMin, yMax, yAxisScale) = getYAxisScale(
                lineChartData,
                (axisData.yMaxValue / axisData.yStepValue).toInt()
            )
            val maxElementInYAxis =
                getMaxElementInYAxis(yAxisScale, yAxisSteps)
            var xOffset by remember { mutableStateOf(0f) }
            ScrollableCanvasContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                calculateMaxDistance = { xZoom ->
                    // horizontalGap.value = lineGraphData.horizontalExtraSpace.toPx()
                    val xLeft = columnWidth //+ horizontalGap.value
                    xOffset = 30.dp.toPx() * xZoom
                    val xLastPoint =
                        (xMax - xMin) * xOffset + xLeft + paddingRight.toPx() //+ horizontalGap.value
                    if (xLastPoint > size.width) {
                        xLastPoint - size.width
                    } else 0f
                },
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
                        chartData = lineChartData
                    )
                },
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - paddingTop.toPx()) / maxElementInYAxis)
                    xOffset = axisData.xAxisStepSize.toPx() * xZoom
                    val xLeft = columnWidth // To add extra space if needed
                    val path = Path()
                    val pointsData = mutableListOf<PointF>()
                    lineGraphData.lineChartData.forEachIndexed { i, point ->
                        val (x, y) = point
                        val x1 = ((x - xMin) * xOffset) + xLeft - scrollOffset
                        val y1 = yBottom - ((y - yMin) * yOffset)
                        pointsData.add(PointF(x1, y1))
                    }
                    val (cubicPoints1, cubicPoints2) = getCubicPoints(pointsData)

                    // Draw cubic line using the points and form a line graph
                    drawCubicLine(path, pointsData, cubicPoints1, cubicPoints2)

                    // Draw Lines and Points and AreaUnderLine
                    // Draw area under curve
                    drawAreaShadowUnderLine(pointsData, yBottom)

                    // Draw column to make graph look scrollable under Yaxis
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)
                })
        }
    }
}


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

private fun getCubicPoints(pointsData: List<PointF>):
        Pair<MutableList<PointF>, MutableList<PointF>> {
    val cubicPoints1 = mutableListOf<PointF>()
    val cubicPoints2 = mutableListOf<PointF>()

    for (i in 1 until pointsData.size) {
        cubicPoints1.add(
            PointF(
                (pointsData[i].x + pointsData[i - 1].x) / 2,
                pointsData[i - 1].y
            )
        )
        cubicPoints2.add(
            PointF(
                (pointsData[i].x + pointsData[i - 1].x) / 2,
                pointsData[i].y
            )
        )
    }
    return Pair(cubicPoints1, cubicPoints2)
}


private fun DrawScope.drawAreaShadowUnderLine(
    pointsData: MutableList<PointF>,
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

private fun DrawScope.drawPointOnLine(offset: PointF) {
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

private fun DrawScope.drawCubicLine(
    path: Path,
    pointsData: MutableList<PointF>,
    cubicPoints1: MutableList<PointF>,
    cubicPoints2: MutableList<PointF>
) {
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
