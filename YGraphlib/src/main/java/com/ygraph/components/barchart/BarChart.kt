package com.ygraph.components.barchart

import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
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
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.barchart.utils.RowClip
import com.ygraph.components.barchart.utils.getDrawOffset
import com.ygraph.components.common.extensions.getMaxElementInYAxis
import com.ygraph.components.common.extensions.getXMaxAndMinPoints
import com.ygraph.components.common.extensions.getYMaxAndMinPoints
import com.ygraph.components.common.extensions.isDragLocked
import com.ygraph.components.common.model.Point
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer


/**
 *
 * BarChart compose method for drawing bar graph.
 * @param modifier: All modifier related properties
 * @param barChartData : All data needed to Bar Chart
 * @see com.ygraph.components.barchart.models.BarChartData Data class to save all params related to Bar chart
 */
@Composable
fun BarChart(modifier: Modifier, barChartData: BarChartData) {
    val totalWidth = remember { mutableStateOf(0) }
    Column(modifier.onGloballyPositioned {
        totalWidth.value = it.size.width
    }) {
        var visibility by remember { mutableStateOf(false) }
        var identifiedPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
        var xOffset by remember { mutableStateOf(0f) }
        var dragOffset by remember { mutableStateOf(0f) }
        var isDragging by remember { mutableStateOf(false) }
        var columnWidth by remember { mutableStateOf(0f) }
        var horizontalGap by remember { mutableStateOf(0f) }
        var rowHeight by remember { mutableStateOf(0f) }
        val paddingRight = barChartData.paddingEnd
        val points = barChartData.chartData.map { it.point }
        val bgColor = MaterialTheme.colors.surface

        val (xMin, xMax) = getXMaxAndMinPoints(points)
        val (_, yMax) = getYMaxAndMinPoints(points)

        val maxElementInYAxis = getMaxElementInYAxis(yMax, barChartData.yStepSize)

        val axisData = AxisData.Builder()
            .yMaxValue(yMax)
            .yStepValue(barChartData.yStepSize.toFloat())
            .xAxisSteps(barChartData.chartData.size - 1)
            .xAxisStepSize(barChartData.barWidth + barChartData.paddingBetweenBars)
            .axisLabelFontSize(barChartData.axisLabelFontSize)
            .yLabelData(barChartData.yLabelData)
            .xLabelData(barChartData.xLabelData)
            .textLabelPadding(barChartData.yLabelAndAxisLinePadding)
            .yAxisOffset(barChartData.yAxisOffset)
            .yTopPadding(barChartData.yTopPadding)
            .yBottomPadding(LocalDensity.current.run { (rowHeight).toDp() })
            .build()

        val highlightTextPaint = TextPaint().apply {
            textSize = LocalDensity.current.run { barChartData.highlightTextSize.toPx() }
            color = barChartData.highlightTextColor.toArgb()
            textAlign = Paint.Align.CENTER
            typeface = barChartData.highlightTextTypeface
        }

        ScrollableCanvasContainer(modifier = modifier,
            containerBackgroundColor = barChartData.backgroundColor,
            calculateMaxDistance = { xZoom ->
                horizontalGap = barChartData.horizontalExtraSpace.toPx()
                val xLeft = columnWidth + horizontalGap
                xOffset =
                    (barChartData.barWidth.toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
                val xLastPoint =
                    (xMax - xMin) * xOffset + xLeft + paddingRight.toPx()
                if (xLastPoint > size.width) {
                    xLastPoint - size.width
                } else 0f
            },
            onDraw = { scrollOffset, xZoom ->
                val yBottom = size.height - rowHeight
                val yOffset = ((yBottom - axisData.yTopPadding.toPx()) / maxElementInYAxis)
                xOffset =
                    ((barChartData.barWidth).toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
                val xLeft =
                    columnWidth + horizontalGap + barChartData.barWidth.toPx() / 2
                val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                // Draw bar lines
                barChartData.chartData.forEachIndexed { _, barData ->
                    val drawOffset = getDrawOffset(
                        barData.point, xMin, xOffset, xLeft,
                        scrollOffset, yBottom, yOffset, 0f
                    )
                    val height = yBottom - drawOffset.y

                    drawBarChart(barChartData, barData, drawOffset, height)

                    // store the drag points for selection
                    if (isDragging && drawOffset.isDragLocked(
                            dragOffset,
                            xOffset
                        )
                    ) {
                        dragLocks[0] = barData to drawOffset
                    }
                }
                // Draw column to make graph look scrollable under Yaxis
                drawRect(
                    bgColor,
                    Offset(0f, 0f),
                    Size(columnWidth, size.height)
                )

                val selectedOffset = dragLocks.values.firstOrNull()?.second

                if (visibility && selectedOffset != null) {
                    drawHighlightText(
                        identifiedPoint,
                        selectedOffset,
                        barChartData.barWidth,
                        barChartData.highlightTextOffset,
                        highlightTextPaint
                    )
                }


                // Handle the show the selected bar
                if (isDragging) {
                    val x = dragLocks.values.firstOrNull()?.second?.x
                    if (x != null) {
                        identifiedPoint = dragLocks.values.map { it.first }.first()
                    }

                    // Draw highlight bar on selection
                    dragLocks.values.firstOrNull()?.let { (barData, location) ->
                        val (xPoint, yPoint) = location
                        if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                            val y1 = yBottom - ((barData.point.y - 0) * yOffset)
                            drawRoundRect(
                                color = Color.Black,
                                topLeft = Offset(xPoint, yPoint),
                                size = Size(barChartData.barWidth.toPx(), yBottom - y1),
                                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                                style = Stroke(width = 2.dp.toPx())
                            )
                        }
                    }
                }


            },
            drawXAndYAxis = { scrollOffset, xZoom ->
                XAxis(
                    axisData = axisData,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(
                            bottom = axisData.xBottomPadding,
                        )
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            RowClip(
                                columnWidth,
                                0.dp
                            )
                        )
                        .onGloballyPositioned {
                            rowHeight = it.size.height.toFloat()
                        },
                    xStart = columnWidth + horizontalGap + LocalDensity.current.run { (barChartData.barWidth.toPx()) },
                    scrollOffset = scrollOffset,
                    zoomScale = xZoom,
                    chartData = points,
                    xLineStart = columnWidth
                )

                YAxis(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .onGloballyPositioned {
                            columnWidth = it.size.width.toFloat()
                        },
                    axisData = axisData,
                )
            },
            onDragStart = { offset ->
                dragOffset = offset.x
                isDragging = true
                visibility = true
            },
            onDragEnd = {
                isDragging = false
                visibility = false
            },
            onDragging = { change, _ -> dragOffset = change.position.x }
        )
    }
}

/**
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth : Width of single bar
 * @param highlightTextOffset : Distance between bar and the highlighted text
 * @param highlightTextPaint : Text paint for the highlighted text
 */
fun DrawScope.drawHighlightText(
    identifiedPoint: BarData, selectedOffset: Offset,
    barWidth: Dp, highlightTextOffset: Dp, highlightTextPaint: TextPaint
) {
    drawContext.canvas.nativeCanvas.apply {
        drawText(
            "x : ${identifiedPoint.point.x}",
            selectedOffset.x + barWidth.toPx() / 2,
            selectedOffset.y - (highlightTextOffset.toPx()
                    + (highlightTextPaint.descent() - highlightTextPaint.ascent())),
            highlightTextPaint
        )
        drawText(
            "y : ${identifiedPoint.point.y}",
            selectedOffset.x + barWidth.toPx() / 2,
            selectedOffset.y - highlightTextOffset.toPx(),
            highlightTextPaint
        )
    }
}


/**
 *
 * Used to draw the individual bars
 * @param barChartData : all meta data related to the bar graph
 * @param barData : data related to a single bar
 * @param drawOffset: topleft ffset for the drawing the bar
 * @param height : height of the bar chart
 */
fun DrawScope.drawBarChart(
    barChartData: BarChartData, barData: BarData, drawOffset: Offset,
    height: Float
) {
    // Draw bar lines
    if (barChartData.isGradientEnabled) {
        val brush = Brush.verticalGradient(
            colors = barData.gradientColorList
        )
        drawRoundRect(
            brush = brush,
            topLeft = drawOffset,
            size = Size(barChartData.barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                barChartData.cornerRadius.toPx(),
                barChartData.cornerRadius.toPx()
            ),
            style = Fill
        )
    } else {
        drawRoundRect(
            color = barData.color,
            topLeft = drawOffset,
            size = Size(barChartData.barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                barChartData.cornerRadius.toPx(),
                barChartData.cornerRadius.toPx()
            ),
            style = Fill
        )
    }
}
