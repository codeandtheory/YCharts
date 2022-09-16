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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.common.extensions.*
import com.ygraph.components.common.model.Point
import com.ygraph.components.common.utils.GraphConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import com.ygraph.components.graph.bargraph.models.BarData
import com.ygraph.components.graph.bargraph.models.BarGraphData
import com.ygraph.components.graph.bargraph.models.BarStyle
import com.ygraph.components.graph.bargraph.models.SelectionHighlightData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer


/**
 *
 * BarGraph compose method for drawing bar graph.
 * @param modifier: All modifier related properties
 * @param barGraphData : All data needed to Bar Graph
 * @see  com.ygraph.components.graph.bargraph.models.BarGraphData Data class to save all params
 * related to Bar Graph
 */
@Composable
fun BarGraph(modifier: Modifier, barGraphData: BarGraphData) {
    Column(modifier) {
        with(barGraphData) {
            var barHighlightVisibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
            var xOffset by remember { mutableStateOf(0f) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
            var isTapped by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var horizontalGap by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = paddingEnd
            val points = graphData.map { it.point }
            val bgColor = MaterialTheme.colors.surface

            val (xMin, xMax) = getXMaxAndMinPoints(points)
            val (_, yMax) = getYMaxAndMinPoints(points)

            val xAxisData = xAxisData.copy(
                axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars,
                steps = graphData.size - 1,
                startDrawPadding = LocalDensity.current.run { columnWidth.toDp() }
            )
            val yAxisData =
                yAxisData.copy(axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() })
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
                        (barStyle.barWidth.toPx() + barStyle.paddingBetweenBars.toPx()) * xZoom
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
                    val yOffset = ((yBottom - yAxisData.axisTopPadding.toPx()) / maxElementInYAxis)
                    xOffset =
                        ((barStyle.barWidth).toPx() + barStyle.paddingBetweenBars.toPx()) * xZoom
                    val xLeft =
                        columnWidth + horizontalGap + barStyle.barWidth.toPx() / 2
                    val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                    // Draw bar lines
                    graphData.forEachIndexed { _, barData ->
                        val drawOffset = getDrawOffset(
                            barData.point, xMin, xOffset, xLeft,
                            scrollOffset, yBottom, yOffset, 0f
                        )
                        val height = yBottom - drawOffset.y

                        // drawing each individual bars
                        drawBarGraph(barGraphData.barStyle, barData, drawOffset, height)

                        val middleOffset =
                            Offset(drawOffset.x + barStyle.barWidth.toPx() / 2, drawOffset.y)
                        // store the tap points for selection
                        if (isTapped && middleOffset.isTapped(
                                tapOffset,
                                barStyle.barWidth.toPx(),
                                yBottom,
                                tapPadding.toPx()
                            )
                        ) {
                            dragLocks[0] = barData to drawOffset
                        }
                    }

                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                    if (barStyle.selectionHighlightData != null) {
                        // highlighting the selected bar and showing the data points
                        identifiedPoint = highlightBar(
                            dragLocks,
                            barHighlightVisibility,
                            identifiedPoint,
                            barGraphData,
                            isTapped,
                            columnWidth,
                            yBottom,
                            paddingRight,
                            yOffset
                        )
                    }
                },
                drawXAndYAxis = { scrollOffset, xZoom ->
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
                            xStart = columnWidth + horizontalGap + LocalDensity.current.run {
                                (barStyle.barWidth.toPx())
                            },
                            scrollOffset = scrollOffset,
                            zoomScale = xZoom,
                            graphData = points
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
                            yAxisData = yAxisData
                        )
                    }
                },
                onPointClicked = { offset: Offset, _: Float ->
                    isTapped = true
                    barHighlightVisibility = true
                    tapOffset = offset
                },
                onScroll = {
                    isTapped = false
                    barHighlightVisibility = false
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
private fun DrawScope.drawHighlightText(
    identifiedPoint: BarData,
    selectedOffset: Offset,
    barWidth: Dp,
    highlightData: SelectionHighlightData
) {
    val centerPointOfBar = selectedOffset.x + barWidth.toPx() / 2
    // Drawing the highlighted background and text
    highlightData.drawPopUp(this, selectedOffset, identifiedPoint, centerPointOfBar)
}

/**
 *
 * Used to draw the individual bars
 * @param barStyle : all meta data related to the bar styling
 * @param barData : data related to a single bar
 * @param drawOffset: top left offset for the drawing the bar
 * @param height : height of the bar graph
 */
fun DrawScope.drawBarGraph(
    barStyle: BarStyle, barData: BarData, drawOffset: Offset,
    height: Float
) = with(barStyle) {
    // Draw bar lines
    if (isGradientEnabled) {
        val brush = Brush.verticalGradient(
            colors = barData.gradientColorList
        )
        drawRoundRect(
            brush = brush,
            topLeft = drawOffset,
            size = Size(barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                cornerRadius.toPx(),
                cornerRadius.toPx()
            ),
            style = barDrawStyle,
            blendMode = barBlendMode
        )
    } else {
        drawRoundRect(
            color = barData.color,
            topLeft = drawOffset,
            size = Size(barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                cornerRadius.toPx(),
                cornerRadius.toPx()
            ),
            style = barDrawStyle,
            blendMode = barBlendMode
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
        (xMax - xMin) * xOffset + xLeft + columnWidth + paddingRight
    return if (xLastPoint > canvasWidth) {
        xLastPoint - canvasWidth
    } else 0f
}

/**
 *
 * returns identified point and displaying the data points and highlighted bar .
 * @param dragLocks : Mutable map of BarData and drawOffset.
 * @param barHighlightVisibility : Flag to control the visibility of highlighted text.
 * @param identifiedPoint: selected bar data.
 * @param barGraphData: Data related to the bar graph.
 * @param isDragging : Boolean flag for the dragging status.
 * @param columnWidth : Width of the Y axis.
 * @param yBottom : Bottom padding.
 * @param paddingRight : Right padding.
 * @param yOffset : Distance between two y points.
 */
private fun DrawScope.highlightBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    barHighlightVisibility: Boolean,
    identifiedPoint: BarData,
    barGraphData: BarGraphData,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
): BarData {
    var mutableIdentifiedPoint: BarData = identifiedPoint
    // Handle the show the selected bar
    if (isDragging) {
        val x = dragLocks.values.firstOrNull()?.second?.x
        if (x != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        // Draw highlight bar on selection
        if (barGraphData.barStyle.selectionHighlightData?.isHighlightBarRequired == true) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                    val y1 = yBottom - ((barData.point.y - 0) * yOffset)
                    barGraphData.barStyle.selectionHighlightData.drawHighlightBar(
                        this,
                        xPoint,
                        yPoint,
                        barGraphData.barStyle.barWidth.toPx(),
                        yBottom - y1
                    )
                }
            }
        }
    }

    val selectedOffset = dragLocks.values.firstOrNull()?.second
    if (barHighlightVisibility && selectedOffset != null &&
        barGraphData.barStyle.selectionHighlightData != null
    ) {
        drawHighlightText(
            mutableIdentifiedPoint,
            selectedOffset,
            barGraphData.barStyle.barWidth,
            barGraphData.barStyle.selectionHighlightData
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
fun DrawScope.drawUnderScrollMask(columnWidth: Float, paddingRight: Dp, bgColor: Color) {
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
 * @param point : bar point
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

