package com.ygraph.components.graph.bargraph


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.common.extensions.RowClip
import com.ygraph.components.common.extensions.getMaxElementInYAxis
import com.ygraph.components.common.extensions.isTapped
import com.ygraph.components.common.model.Point
import com.ygraph.components.common.utils.GraphConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import com.ygraph.components.graph.bargraph.models.Bar
import com.ygraph.components.graph.bargraph.models.GroupBarGraphData
import com.ygraph.components.graph.bargraph.models.SelectionHighlightData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer


/**
 *
 * GroupBarGraph compose method for drawing group bar graph.
 * @param modifier: All modifier related properties
 * @param groupBarGraphData : All data needed to group bar graph
 * @see com.ygraph.components.graph.bargraph.models.GroupBarGraphData Data class to save all
 * params related to Bar Graph
 */
@Composable
fun GroupBarGraph(modifier: Modifier, groupBarGraphData: GroupBarGraphData) {
    Column(modifier) {
        with(groupBarGraphData) {
            var visibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(Bar(0f, "")) }
            var xOffset by remember { mutableStateOf(0f) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
            var isTapped by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var horizontalGap by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = paddingEnd
            val valueList = groupedBarList.map { it.yMax }
            val bgColor = MaterialTheme.colors.surface

            val xMax = groupedBarList.size
            val yMax = valueList.maxOrNull() ?: 0f
            val xAxisData =
                xAxisData.copy(
                    axisStepSize = ((barWidth * groupingSize) + paddingBetweenBars),
                    shouldDrawAxisLineTillEnd = true,
                    steps = groupedBarList.size - 1,
                    startDrawPadding = LocalDensity.current.run { columnWidth.toDp() }
                )
            val yAxisData = yAxisData.copy(
                axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() }
            )

            val maxElementInYAxis = getMaxElementInYAxis(yMax, yAxisData.steps)

            if (!showXAxis) {
                rowHeight = LocalDensity.current.run { DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
            }

            ScrollableCanvasContainer(
                modifier = modifier,
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
                        val yOffset =
                            ((yBottom - yAxisData.axisTopPadding.toPx()) / maxElementInYAxis)
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

                                val individualOffset =
                                    Offset(drawOffset.x + insideOffset, drawOffset.y)

                                // drawing each individual bars
                                drawGroupBarGraph(
                                    groupBarGraphData,
                                    individualOffset,
                                    height,
                                    subIndex
                                )
                                insideOffset += barWidth.toPx()

                                val middleOffset =
                                    Offset(individualOffset.x + barWidth.toPx() / 2, drawOffset.y)
                                // store the tap points for selection
                                if (isTapped && middleOffset.isTapped(
                                        tapOffset,
                                        barWidth.toPx(),
                                        yBottom,
                                        tapPadding.toPx()
                                    )
                                ) {
                                    dragLocks[0] = individualBar to individualOffset
                                }
                            }

                            if (groupSeparatorConfig.showSeparator && index != groupedBarList.size - 1) {
                                // drawing each Group Separator bars
                                val yOffset2 = (yBottom - yAxisData.axisTopPadding.toPx())
                                val height = yBottom - yAxisData.axisTopPadding.toPx()
                                val drawOffset2 = getGroupBarDrawOffset(
                                    index,
                                    rowHeight,
                                    xOffset,
                                    xLeft,
                                    scrollOffset,
                                    yBottom,
                                    yOffset2,
                                    0f
                                )
                                val xOffset2 = (drawOffset2.x
                                        + insideOffset + (paddingBetweenBars.toPx() / 2) - groupSeparatorConfig.separatorWidth.toPx() / 2)
                                val individualOffset =
                                    Offset(xOffset2, yAxisData.axisTopPadding.toPx())

                                drawGroupSeparator(
                                    individualOffset,
                                    height,
                                    groupSeparatorConfig.separatorWidth.toPx(),
                                    groupSeparatorConfig.separatorColor,
                                    groupBarGraphData
                                )
                            }
                        }
                        drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                        if (selectionHighlightData != null) {
                            // highlighting the selected bar and showing the data points
                            identifiedPoint = highlightGroupBar(
                                dragLocks,
                                visibility,
                                identifiedPoint,
                                groupBarGraphData.selectionHighlightData,
                                isTapped,
                                columnWidth,
                                yBottom,
                                paddingRight,
                                yOffset,
                                groupBarGraphData.barWidth
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
                                xAxisData = xAxisData,
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
                                graphData = points,
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
                                yAxisData = yAxisData,
                            )
                        }
                    },
                    onPointClicked = { offset: Offset, _: Float ->
                        isTapped = true
                        visibility = true
                        tapOffset = offset
                    },
                    onScroll = {
                        isTapped = false
                        visibility = false
                    }
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
 * @param barGraphData : all meta data related to the bar graph
 * @param drawOffset: topLeft offset for the drawing the bar
 * @param height : height of the bar graph
 * @param subIndex : Index of the bar
 */
private fun DrawScope.drawGroupBarGraph(
    barGraphData: GroupBarGraphData, drawOffset: Offset,
    height: Float,
    subIndex: Int
) {
    val color = barGraphData.legendsConfig.legendLabelList[subIndex].color
    drawRoundRect(
        color = color,
        topLeft = drawOffset,
        size = Size(barGraphData.barWidth.toPx(), height),
        cornerRadius = CornerRadius(
            barGraphData.cornerRadius.toPx(),
            barGraphData.cornerRadius.toPx()
        ),
        style = barGraphData.barDrawStyle,
        blendMode = barGraphData.barBlendMode
    )
}


/**
 *
 * returns identified point and displaying the data points and highlighted bar .
 * @param dragLocks : Mutable map of BarData and drawOffset.
 * @param visibility : Flag to control the visibility of highlighted text.
 * @param identifiedPoint: selected bar data.
 * @param selectionHighlightData: Data related to the bar graph highlight.
 * @param isDragging : Boolean flag for the dragging status.
 * @param columnWidth : Width of the Y axis.
 * @param yBottom : Bottom padding.
 * @param paddingRight : Right padding.
 * @param yOffset : Distance between two y points.
 * @param barWidth : Width of each bar.
 */
fun DrawScope.highlightGroupBar(
    dragLocks: MutableMap<Int, Pair<Bar, Offset>>,
    visibility: Boolean,
    identifiedPoint: Bar,
    selectionHighlightData: SelectionHighlightData?,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
    barWidth: Dp,
): Bar {
    var mutableIdentifiedPoint: Bar = identifiedPoint
    // Handle the show the selected bar
    if (isDragging) {
        val x = dragLocks.values.firstOrNull()?.second?.x
        if (x != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        // Draw highlight bar on selection
        if (selectionHighlightData?.isHighlightBarRequired == true) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                    val y1 = yBottom - ((barData.value - 0) * yOffset)
                    selectionHighlightData.drawHighlightBar(
                        this,
                        xPoint,
                        yPoint,
                        barWidth.toPx(),
                        yBottom - y1
                    )
                }
            }
        }
    }

    val selectedOffset = dragLocks.values.firstOrNull()?.second
    if (visibility && selectedOffset != null && selectionHighlightData != null) {
        drawGroupHighlightText(
            mutableIdentifiedPoint,
            selectedOffset,
            barWidth,
            selectionHighlightData
        )
    }
    return mutableIdentifiedPoint
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

/**
 *
 * Used to draw the group separator
 * @param barGraphData: [GroupBarGraphData]
 * @param drawOffset: topLeft offset for the drawing the separator
 * @param height : height of the separator
 * @param width : width of the separator
 * @param color : color of the separator
 */
private fun DrawScope.drawGroupSeparator(
    drawOffset: Offset,
    height: Float,
    width: Float,
    color: Color,
    barGraphData: GroupBarGraphData,
) {
    drawRoundRect(
        color = color,
        topLeft = drawOffset,
        size = Size(width, height),
        blendMode = barGraphData.groupSeparatorConfig.separatorBlendMode
    )
}
