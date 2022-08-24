package com.ygraph.components.barchart

import android.graphics.Paint
import android.graphics.Rect
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.barchart.utils.BarChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import com.ygraph.components.common.extensions.*
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
        val highlightData = barChartData.selectionHighlightData

        val (xMin, xMax) = getXMaxAndMinPoints(points)
        val (_, yMax) = getYMaxAndMinPoints(points)

        val maxElementInYAxis = getMaxElementInYAxis(yMax, barChartData.yStepSize)

        if (!barChartData.showXAxis) {
            rowHeight = LocalDensity.current.run { DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
        }

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
            .yBottomPadding(LocalDensity.current.run { rowHeight.toDp() })
            .axisConfig(barChartData.axisConfig)
            .build()

        val highlightTextPaint = TextPaint().apply {
            textSize = LocalDensity.current.run { highlightData.highlightTextSize.toPx() }
            color = highlightData.highlightTextColor.toArgb()
            textAlign = Paint.Align.CENTER
            typeface = highlightData.highlightTextTypeface
        }

        ScrollableCanvasContainer(modifier = modifier,
            containerBackgroundColor = barChartData.backgroundColor,
            calculateMaxDistance = { xZoom ->
                horizontalGap = barChartData.horizontalExtraSpace.toPx()
                val xLeft = columnWidth + horizontalGap
                xOffset =
                    (barChartData.barWidth.toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
                getMaxScrollDistance(
                    columnWidth,
                    xMax,
                    xMin,
                    xOffset,
                    xLeft,
                    paddingRight.toPx(),
                    size.width
                )
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

                    // drawing each individual bars
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

                drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                // highlighting the selected bar and showing the data points
                identifiedPoint = highlightBar(
                    dragLocks,
                    visibility,
                    identifiedPoint,
                    barChartData,
                    highlightTextPaint,
                    isDragging,
                    columnWidth,
                    yBottom,
                    paddingRight,
                    yOffset,
                    maxElementInYAxis
                )


            },
            drawXAndYAxis = { scrollOffset, xZoom ->

                if (barChartData.showXAxis) {
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
                                    paddingRight
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
                }

                if (barChartData.showYAxis) {
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
                }
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
 * @param barChartData: Data related to the bar chart
 * @param highlightTextPaint : Text paint for the highlighted text
 * @param maximumElementInYAxis : Maximum value on the Y axis
 */
private fun DrawScope.drawHighlightText(
    identifiedPoint: BarData,
    selectedOffset: Offset,
    barChartData: BarChartData,
    highlightTextPaint: TextPaint,
    yBottom: Float,
    yOffset: Float,
    maximumElementInYAxis: Int
) {

    val highlightData = barChartData.selectionHighlightData
    val yMaximumDrawOffset = yBottom - ((maximumElementInYAxis - 1) * yOffset)
    // if the selected offset is very close to top then changing the highlight text position
    val highlightYValue = if (selectedOffset.y - yMaximumDrawOffset > yOffset) {
        // above the bar
        selectedOffset.y - highlightData.highlightTextOffset.toPx()
    } else {
        // on the bar
        selectedOffset.y + highlightData.highlightTextOffset.toPx()
    }
    drawContext.canvas.nativeCanvas.apply {

        val highlightText = "x : ${identifiedPoint.point.x}"
        val centerPointOfBar = selectedOffset.x + barChartData.barWidth.toPx() / 2

        val background = getTextBackgroundSize(
            centerPointOfBar,
            highlightYValue,
            highlightText,
            highlightTextPaint
        )

        // drawing the background rect for the text
        drawRect(
            highlightData.highlightTextBackgroundColor,
            Offset(background.left.toFloat(), background.top.toFloat()),
            Size(background.width().toFloat(), background.height().toFloat()),
            highlightData.highlightTextBackgroundAlpha
        )

        drawText(
            highlightText,
            centerPointOfBar,
            highlightYValue
                    - (highlightTextPaint.descent() - highlightTextPaint.ascent()),
            highlightTextPaint
        )
        drawText(
            "y : ${identifiedPoint.point.y}",
            centerPointOfBar,
            highlightYValue,
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
private fun DrawScope.drawBarChart(
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


/**
 *
 * returns the max scrollable distance based on the points to be drawn along with padding etc.
 * @param columnWidth : Width of the Y-Axis.
 * @param xMax : Max X-Axis value.
 * @param xMin: Min X-Axis value.
 * @param xOffset: Total distance between two X-Axis points.
 * @param xLeft: Total Left padding.
 * @param paddingRight : Padding at the end of the canvas.
 * @param canvasWidth : Total available canvas width.
 */
fun getMaxScrollDistance(
    columnWidth: Float,
    xMax: Float,
    xMin: Float,
    xOffset: Float,
    xLeft: Float,
    paddingRight: Float,
    canvasWidth: Float
): Float {
    val xLastPoint =
        (xMax - xMin) * xOffset + xLeft + columnWidth + paddingRight //+ horizontalGap.value
    return if (xLastPoint > canvasWidth) {
        xLastPoint - canvasWidth
    } else 0f
}


/**
 *
 * returns identified point and displaying the data points and highlighted bar .
 * @param dragLocks : Mutable map of BarData and drawOffset.
 * @param visibility : Flag to control the visibility of highlighted text.
 * @param identifiedPoint: selected bar data.
 * @param barChartData: Data related to the bar chart.
 * @param highlightTextPaint: Text paint for the highlighted text.
 * @param isDragging : Boolean flag for the dragging status.
 * @param columnWidth : Width of the Y axis.
 * @param yBottom : Bottom padding.
 * @param paddingRight : Right padding.
 * @param yOffset : Distance between two y points.
 * @param yMaximumElement : Maximum value on the Y axis
 */
private fun DrawScope.highlightBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    visibility: Boolean,
    identifiedPoint: BarData,
    barChartData: BarChartData,
    highlightTextPaint: TextPaint,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
    yMaximumElement: Int
): BarData {
    var mutableIdentifiedPoint: BarData = identifiedPoint
    // Handle the show the selected bar
    if (isDragging) {
        val x = dragLocks.values.firstOrNull()?.second?.x
        if (x != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        // Draw highlight bar on selection
        if (barChartData.selectionHighlightData.isHighlightBarRequired) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                    val y1 = yBottom - ((barData.point.y - 0) * yOffset)
                    barChartData.selectionHighlightData.drawHighlightBar(
                        this,
                        xPoint,
                        yPoint,
                        barChartData.barWidth.toPx(),
                        yBottom - y1
                    )
                }
            }
        }
    }

    val selectedOffset = dragLocks.values.firstOrNull()?.second
    if (visibility && selectedOffset != null) {
        drawHighlightText(
            mutableIdentifiedPoint,
            selectedOffset,
            barChartData,
            highlightTextPaint,
            yBottom,
            yOffset,
            yMaximumElement
        )
    }

    return mutableIdentifiedPoint
}

/**
 *
 * DrawScope.drawUnderScrollMask extension method used  for drawing a rectangular mask to make graph scrollable under the YAxis.
 * @param columnWidth : Width of the rectangular mask here width of Y Axis is used.
 * @param paddingRight : Padding given at the end of the graph.
 * @param bgColor : Background of the rectangular mask.
 */
private fun DrawScope.drawUnderScrollMask(columnWidth: Float, paddingRight: Dp, bgColor: Color) {
    // Draw column to make graph look scrollable under Yaxis
    drawRect(
        bgColor,
        Offset(0f, 0f),
        Size(columnWidth, size.height)
    )
    // Draw right padding
    drawRect(
        bgColor,
        Offset(size.width - paddingRight.toPx(), 0f),
        Size(paddingRight.toPx(), size.height)
    )
}


/**
 * returns the draw offset for bar graph.
 * @param point : chartData point
 * @param xMin: Minimum value on the x axis
 * @param yMin: Minimum value on the y axis
 * @param xOffset: Distance between bars
 * @param yOffset: Distance between y axis points
 * @param xLeft: X starting point of bar graph
 * @param scrollOffset: Scroll offset
 * @param yBottom: Y starting point of bar graph
 */
fun getDrawOffset(
    point: Point, xMin: Float, xOffset: Float,
    xLeft: Float, scrollOffset: Float, yBottom: Float, yOffset: Float, yMin: Float
): Offset {
    val (x, y) = point
    val x1 = ((x - xMin) * xOffset) + xLeft - scrollOffset
    val y1 = yBottom - ((y - yMin) * yOffset)
    return Offset(x1, y1)
}


/**
 * returns the background rect for the highlighted text.
 * @param x : X point.
 * @param y: Y point.
 * @param text: Text to be drawn inside the background.
 * @param paint: Background paint.
 */
private fun getTextBackgroundSize(
    x: Float,
    y: Float,
    text: String,
    paint: TextPaint
): Rect {
    val fontMetrics = paint.fontMetrics
    val textLength = paint.measureText(text)
    return Rect(
        (x - textLength).toInt(),
        (y + fontMetrics.top * 2).toInt(),
        (x + textLength).toInt(),
        (y + fontMetrics.bottom * 2).toInt()
    )
}
