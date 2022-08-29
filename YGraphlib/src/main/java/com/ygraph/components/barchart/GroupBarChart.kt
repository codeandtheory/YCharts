package com.ygraph.components.barchart


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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.barchart.models.*
import com.ygraph.components.barchart.utils.BarChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import com.ygraph.components.common.extensions.*
import com.ygraph.components.common.model.Point
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer


/**
 *
 * BarChart compose method for drawing bar graph.
 * @param modifier: All modifier related properties
 * @param groupBarChartData : All data needed to Bar Chart
 * @see com.ygraph.components.barchart.models.GroupBarChartData Data class to save all params related to Bar chart
 */
@Composable
fun GroupBarChart(modifier: Modifier, groupBarChartData: GroupBarChartData) {
    Column(modifier) {
        with(groupBarChartData) {
            var visibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(Bar(0f, Color.White, "")) }
            var xOffset by remember { mutableStateOf(0f) }
            var dragOffset by remember { mutableStateOf(0f) }
            var isDragging by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var horizontalGap by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = paddingEnd
            val valueList = groupedBarList.map { it.yMax }
            val bgColor = MaterialTheme.colors.surface

            val xMax = groupedBarList.size
            val yMax = valueList.maxOrNull() ?: 0f

            val maxElementInYAxis = getMaxElementInYAxis(yMax, yStepSize)

            if (!showXAxis) {
                rowHeight = LocalDensity.current.run { DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
            }


            val axisData = AxisData.Builder()
                .yMaxValue(yMax)
                .yStepValue(yStepSize.toFloat())
                .xAxisSteps(groupedBarList.size - 1)
                .xAxisStepSize((barWidth * groupingSize) + paddingBetweenBars)
                .xAxisLabelAngle(xLabelAngle)
                .axisLabelFontSize(axisLabelFontSize)
                .yLabelData(yLabelData)
                .xLabelData(xLabelData)
                .yLabelAndAxisLinePadding(yLabelAndAxisLinePadding)
                .yAxisOffset(yAxisOffset)
                .yTopPadding(yTopPadding)
                .shouldXAxisStartWithPadding(true)
                .yBottomPadding(LocalDensity.current.run { rowHeight.toDp() })
                .xBottomPadding(xBottomPadding)
                .axisConfig(axisConfig)
                .build()



            ScrollableCanvasContainer(modifier = modifier,
                containerBackgroundColor = backgroundColor,
                calculateMaxDistance = { xZoom ->
                    horizontalGap = horizontalExtraSpace.toPx()
                    val xLeft = columnWidth + horizontalGap
                    xOffset =
                        ((barWidth.toPx() * groupingSize) + paddingBetweenBars.toPx()) * xZoom
                    getMaxScrollDistance(
                        columnWidth,
                        xMax.toFloat(),
                        0f,
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
                        ((barWidth.toPx() * groupingSize) + paddingBetweenBars.toPx()) * xZoom
                    val xLeft =
                        columnWidth + horizontalGap
                    val dragLocks = mutableMapOf<Int, Pair<Bar, Offset>>()

                    // Draw bar lines
                    groupedBarList.forEachIndexed { index, groupBarData ->
                        var insideOffset = 0f

                        groupBarData.barList.forEachIndexed { subIndex, individualBar ->
                            val drawOffset = getGroupBarDrawOffset(
                                index, individualBar.value, xOffset, xLeft,
                                scrollOffset, yBottom, yOffset, 0f
                            )
                            val height = yBottom - drawOffset.y

                            val individualOffset = Offset(drawOffset.x + insideOffset, drawOffset.y)

                            // drawing each individual bars
                            drawGroupBarChart(
                                groupBarChartData,
                                individualBar,
                                individualOffset,
                                height,
                                subIndex
                            )

                            insideOffset += barWidth.toPx()

                            val middleOffset =
                                Offset(individualOffset.x + barWidth.toPx() / 2, drawOffset.y)
                            // store the drag points for selection
                            if (isDragging && middleOffset.isDragLocked(
                                    dragOffset,
                                    barWidth.toPx()
                                )
                            ) {
                                dragLocks[0] = individualBar to individualOffset
                            }
                        }

                    }

                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                    if (selectionHighlightData != null) {
                        // highlighting the selected bar and showing the data points
                        identifiedPoint = highlightGroupBar(
                            dragLocks,
                            visibility,
                            identifiedPoint,
                            groupBarChartData,
                            isDragging,
                            columnWidth,
                            yBottom,
                            paddingRight,
                            yOffset
                        )
                    }
                },
                drawXAndYAxis = { scrollOffset, xZoom ->
                    val points = mutableListOf<Point>()
                    for (index in groupedBarList.indices) {
                        points.add(Point(index.toFloat(), 0f))
                    }


                    if (showXAxis) {
                        XAxis(
                            axisData = axisData,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
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
                            xStart = columnWidth + horizontalGap + LocalDensity.current.run { (barWidth.toPx() * groupingSize) / 2 },
                            scrollOffset = scrollOffset,
                            zoomScale = xZoom,
                            chartData = points,
                            xLineStart = columnWidth
                        )
                    }

                    if (showYAxis) {
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
}

/**
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth: Width of single bar
 * @param highlightData: Data for the highlight section
 */
private fun DrawScope.drawGroupHighlightText(
    identifiedPoint: Bar,
    selectedOffset: Offset,
    barWidth: Dp,
    highlightData: SelectionHighlightData
) {
    val centerPointOfBar = selectedOffset.x + barWidth.toPx() / 2
    // Drawing the highlighted background and text
    highlightData.drawGroupBarPopUp(this, selectedOffset, identifiedPoint, centerPointOfBar)
}


/**
 *
 * Used to draw the individual bars
 * @param barChartData : all meta data related to the bar graph
 * @param barData : data related to a single bar
 * @param drawOffset: topleft ffset for the drawing the bar
 * @param height : height of the bar chart
 * @param subIndex : Index of the bar
 */
private fun DrawScope.drawGroupBarChart(
    barChartData: GroupBarChartData, barData: Bar, drawOffset: Offset,
    height: Float,
    subIndex: Int
) {
    val color = barData.color ?: barChartData.colorTemplate[subIndex]
    drawRoundRect(
        color = color,
        topLeft = drawOffset,
        size = Size(barChartData.barWidth.toPx(), height),
        cornerRadius = CornerRadius(
            barChartData.cornerRadius.toPx(),
            barChartData.cornerRadius.toPx()
        ),
        style = barChartData.barDrawStyle,
        blendMode = barChartData.barBlendMode
    )
}


/**
 *
 * returns identified point and displaying the data points and highlighted bar .
 * @param dragLocks : Mutable map of BarData and drawOffset.
 * @param visibility : Flag to control the visibility of highlighted text.
 * @param identifiedPoint: selected bar data.
 * @param barChartData: Data related to the bar chart.
 * @param isDragging : Boolean flag for the dragging status.
 * @param columnWidth : Width of the Y axis.
 * @param yBottom : Bottom padding.
 * @param paddingRight : Right padding.
 * @param yOffset : Distance between two y points.
 */
private fun DrawScope.highlightGroupBar(
    dragLocks: MutableMap<Int, Pair<Bar, Offset>>,
    visibility: Boolean,
    identifiedPoint: Bar,
    barChartData: GroupBarChartData,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
): Bar {
    var mutableIdentifiedPoint: Bar = identifiedPoint
    // Handle the show the selected bar
    if (isDragging) {
        val x = dragLocks.values.firstOrNull()?.second?.x
        if (x != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        // Draw highlight bar on selection
        if (barChartData.selectionHighlightData?.isHighlightBarRequired == true) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                    val y1 = yBottom - ((barData.value - 0) * yOffset)
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
    if (visibility && selectedOffset != null && barChartData.selectionHighlightData != null) {
        drawGroupHighlightText(
            mutableIdentifiedPoint,
            selectedOffset,
            barChartData.barWidth,
            barChartData.selectionHighlightData
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
 * @param yMin: Minimum value on the y axis
 * @param xOffset: Distance between bars
 * @param yOffset: Distance between y axis points
 * @param xLeft: X starting point of bar graph
 * @param scrollOffset: Scroll offset
 * @param yBottom: Y starting point of bar graph
 */
fun getGroupBarDrawOffset(
    x: Int, y: Float, xOffset: Float,
    xLeft: Float, scrollOffset: Float, yBottom: Float, yOffset: Float, yMin: Float
): Offset {
    val x1 = (x * xOffset) + xLeft - scrollOffset
    val y1 = yBottom - ((y - yMin) * yOffset)
    return Offset(x1, y1)
}

