package com.ygraph.components.barchart

import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.barchart.utils.RowClip
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
        val visibility = remember { mutableStateOf(false) }
        val identifiedPoint = remember { mutableStateOf(BarData(Point(0f, 0f))) }
        val xOffset = remember { mutableStateOf(0f) }
        val dragOffset = remember { mutableStateOf(0f) }
        val isDragging = remember { mutableStateOf(false) }
        val columnWidth = remember { mutableStateOf(0f) }
        val horizontalGap = remember { mutableStateOf(0f) }
        val paddingRight = barChartData.paddingEnd
        val points = barChartData.chartData.map { it.point }
        val rowHeight = remember { mutableStateOf(0f) }
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
            .yBottomPadding(LocalDensity.current.run { (rowHeight.value).toDp() })
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
                horizontalGap.value = barChartData.horizontalExtraSpace.toPx()
                val xLeft = columnWidth.value + horizontalGap.value
                xOffset.value =
                    (barChartData.barWidth.toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
                val xLastPoint =
                    (xMax - xMin) * xOffset.value + xLeft + paddingRight.toPx()
                if (xLastPoint > size.width) {
                    xLastPoint - size.width
                } else 0f
            },
            onDraw = { scrollOffset, xZoom ->

                val yBottom = size.height - rowHeight.value
                val yOffset = ((yBottom - axisData.yTopPadding.toPx()) / maxElementInYAxis)
                xOffset.value =
                    ((barChartData.barWidth).toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
                val xLeft =
                    columnWidth.value + horizontalGap.value + barChartData.barWidth.toPx() / 2
                val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                // Draw bar lines
                barChartData.chartData.forEachIndexed { _, barData ->
                    val (x, y) = (barData.point)
                    val x1 = ((x - xMin) * xOffset.value) + xLeft - scrollOffset
                    val y1 = yBottom - ((y - 0) * yOffset)
                    val drawOffset = Offset(x1, y1)

                    if (barChartData.isGradientEnabled) {
                        val brush = Brush.verticalGradient(
                            colors = barData.gradientColorList
                        )
                        drawRoundRect(
                            brush = brush,
                            topLeft = drawOffset,
                            size = Size(barChartData.barWidth.toPx(), yBottom - y1),
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
                            size = Size(barChartData.barWidth.toPx(), yBottom - y1),
                            cornerRadius = CornerRadius(
                                barChartData.cornerRadius.toPx(),
                                barChartData.cornerRadius.toPx()
                            ),
                            style = Fill
                        )
                    }

                    // store the drag points for selection
                    if (isDragging.value && drawOffset.isDragLocked(
                            dragOffset.value,
                            xOffset.value
                        )
                    ) {
                        dragLocks[0] = barData to drawOffset
                    }
                }
                // Draw column to make graph look scrollable under Yaxis
                drawRect(
                    bgColor,
                    Offset(0f, 0f),
                    Size(columnWidth.value, size.height)
                )

                val selectedOffset = dragLocks.values.firstOrNull()?.second

                if (visibility.value && selectedOffset != null) {
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "x : ${identifiedPoint.value.point.x}",
                            selectedOffset.x + barChartData.barWidth.toPx() / 2,
                            selectedOffset.y - (barChartData.highlightTextOffset.toPx()
                                    + (highlightTextPaint.descent() - highlightTextPaint.ascent())),
                            highlightTextPaint
                        )
                        drawText(
                            "y : ${identifiedPoint.value.point.y}",
                            selectedOffset.x + barChartData.barWidth.toPx() / 2,
                            selectedOffset.y - barChartData.highlightTextOffset.toPx(),
                            highlightTextPaint
                        )
                    }
                }


                // Handle the show the selected bar
                if (isDragging.value) {
                    val x = dragLocks.values.firstOrNull()?.second?.x
                    if (x != null) {
                        identifiedPoint.value = dragLocks.values.map { it.first }.first()
                    }

                    // Draw highlight bar on selection
                    dragLocks.values.firstOrNull()?.let { (barData, location) ->
                        val (xPoint, yPoint) = location
                        if (xPoint >= columnWidth.value && xPoint <= size.width - paddingRight.toPx()) {
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
                                columnWidth.value,
                                0.dp
                            )
                        )
                        .onGloballyPositioned {
                            rowHeight.value = it.size.height.toFloat()
                        },
                    xStart = columnWidth.value + horizontalGap.value + LocalDensity.current.run { (barChartData.barWidth.toPx()) },
                    scrollOffset = scrollOffset,
                    zoomScale = xZoom,
                    chartData = points,
                    xLineStart = columnWidth.value
                )

                YAxis(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .onGloballyPositioned {
                            columnWidth.value = it.size.width.toFloat()
                        },
                    axisData = axisData,
                )
            },
            onDragStart = { offset ->
                dragOffset.value = offset.x
                isDragging.value = true
                visibility.value = true
            },
            onDragEnd = {
                isDragging.value = false
                visibility.value = false
            },
            onDragging = { change, _ -> dragOffset.value = change.position.x }
        )
    }
}

