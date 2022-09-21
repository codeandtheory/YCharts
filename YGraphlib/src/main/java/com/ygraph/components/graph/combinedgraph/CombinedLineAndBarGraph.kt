package com.ygraph.components.graph.combinedgraph

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.common.extensions.*
import com.ygraph.components.common.extensions.getMaxElementInYAxis
import com.ygraph.components.common.model.Point
import com.ygraph.components.graph.bargraph.drawBarGraph
import com.ygraph.components.graph.bargraph.drawUnderScrollMask
import com.ygraph.components.graph.bargraph.getDrawOffset
import com.ygraph.components.graph.bargraph.getMaxScrollDistance
import com.ygraph.components.graph.bargraph.highlightBar
import com.ygraph.components.graph.bargraph.models.BarData
import com.ygraph.components.graph.combinedgraph.model.CombinedLineAndBarGraphData
import com.ygraph.components.graph.linegraph.*
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

/**
 *
 * CombinedLineAndBarGraph compose method for drawing combined line and bar graph.
 * @param modifier: All modifier related properties
 * @param combineGraphData : All data needed to Bar Graph
 * @see com.ygraph.components.graph.combinedgraph.model.CombinedLineAndBarGraphData Data class to save all params
 * related to Bar Graph
 */
@Composable
fun CombinedLineAndBarGraph(modifier: Modifier, combineGraphData: CombinedLineAndBarGraphData) {
    Column(modifier) {
        with(combineGraphData) {
            var xOffset by remember { mutableStateOf(0f) }
            var isTapped by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = paddingEnd
            val points = barPlotData.barData.map { it.point } + line.dataPoints
            val bgColor = MaterialTheme.colors.surface

            val (xMin, xMax) = getXMaxAndMinPoints(points)
            val (yMin, yMax) = getYMaxAndMinPoints(points)
            val requiredSteps = maxOf(line.dataPoints.size - 1, barPlotData.barData.size - 1)
            val xAxisData = xAxisData.copy(
                axisStepSize = barPlotData.barStyle.barWidth + barPlotData.barStyle.paddingBetweenBars,
                steps = requiredSteps,
                startDrawPadding = LocalDensity.current.run { columnWidth.toDp() },
                shouldDrawAxisLineTillEnd = true
            )
            val yAxisData =
                yAxisData.copy(
                    axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() },
                    axisTopPadding = paddingTop
                )
            val maxElementInYAxis = getMaxElementInYAxis(yMax, yAxisData.steps)
            var identifiedBarPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
            var identifiedPoint by remember { mutableStateOf(Point(0f, 0f)) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }


            ScrollableCanvasContainer(
                modifier = modifier,
                containerBackgroundColor = backgroundColor,
                calculateMaxDistance = { xZoom ->
                    val xLeft = columnWidth + horizontalExtraSpace.toPx()
                    xOffset =
                        (barPlotData.barStyle.barWidth.toPx() + barPlotData.barStyle.paddingBetweenBars.toPx()) * xZoom
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
                drawXAndYAxis = { scrollOffset, xZoom ->
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
                        xStart = columnWidth + LocalDensity.current.run {
                            (barPlotData.barStyle.barWidth.toPx()) + horizontalExtraSpace.toPx()
                        },
                        scrollOffset = scrollOffset,
                        zoomScale = xZoom,
                        graphData = points
                    )
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
                },
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - yAxisData.axisTopPadding.toPx()) / maxElementInYAxis)
                    xOffset =
                        ((barPlotData.barStyle.barWidth).toPx() + barPlotData.barStyle.paddingBetweenBars.toPx()) * xZoom
                    val xLeft =
                        columnWidth + horizontalExtraSpace.toPx() + barPlotData.barStyle.barWidth.toPx() / 2
                    val barTapLocks = mutableMapOf<Int, Pair<BarData, Offset>>()
                    val linePointLocks = mutableMapOf<Int, Pair<Point, Offset>>()

                    // Draw bar graph
                    barPlotData.barData.forEachIndexed { index, barData ->
                        val drawOffset = getDrawOffset(
                            barData.point, xMin, xOffset, xLeft,
                            scrollOffset, yBottom, yOffset, 0f
                        )
                        val height = yBottom - drawOffset.y

                        // drawing each individual bars
                        drawBarGraph(barPlotData.barStyle, barData, drawOffset, height)
                        val middleOffset =
                            Offset(
                                drawOffset.x + barPlotData.barStyle.barWidth.toPx() / 2,
                                drawOffset.y
                            )
                        // store the tap points for selection
                        val canSelectBar = if (canSupportTapOnIndividualPointOrBar) {
                            middleOffset.isTapped(
                                tapOffset,
                                barPlotData.barStyle.barWidth.toPx(),
                                yBottom,
                                tapPadding.toPx()
                            )
                        } else {
                            middleOffset.isDragLocked(
                                tapOffset.x,
                                xOffset
                            )
                        }
                        if (isTapped && canSelectBar) {
                            barTapLocks[0] = barData to drawOffset
                        }
                    }

                    // Draw line graph
                    val pointsData = getMappingPointsToGraph(
                        line.dataPoints,
                        xMin,
                        xOffset,
                        columnWidth + horizontalExtraSpace.toPx() + barPlotData.barStyle.barWidth.toPx(),
                        scrollOffset,
                        yBottom,
                        yMin,
                        yOffset
                    )
                    val (cubicPoints1, cubicPoints2) = getCubicPoints(pointsData)

                    // Draw cubic line using the points and form a line graph
                    val cubicPath =
                        drawStraightOrCubicLine(
                            pointsData,
                            cubicPoints1,
                            cubicPoints2,
                            line.lineStyle
                        )

                    // Draw area under curve
                    drawShadowUnderLineAndIntersectionPoint(cubicPath, pointsData, yBottom, line)

                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)


                    pointsData.forEachIndexed { index, point ->
                        val isPointTapped = if (canSupportTapOnIndividualPointOrBar) {
                            point.isPointTapped(tapOffset, xOffset, tapPadding.toPx())
                        } else {
                            point.isTapped(tapOffset.x, xOffset)
                        }
                        if (isTapped && isPointTapped) {
                            // Dealing with only one line graph hence tapPointLocks[0]
                            linePointLocks[0] = line.dataPoints[index] to point
                        }
                    }

                    if (isTapped) {
                        drawHighLightOnSelectedPoint(
                            linePointLocks,
                            columnWidth,
                            paddingRight,
                            yBottom,
                            line.selectionHighlightPoint?.copy(isHighlightLineRequired = false)
                        )
                    }

                    if (barPlotData.barStyle.selectionHighlightData != null) {
                        // highlighting the selected bar and showing the data points
                        identifiedBarPoint = highlightBar(
                            barTapLocks,
                            isTapped,
                            identifiedBarPoint,
                            barPlotData.barStyle,
                            isTapped,
                            columnWidth,
                            yBottom,
                            paddingRight,
                            yOffset,
                            canSupportTapOnIndividualPointOrBar
                        )
                    }
                    if (!canSupportTapOnIndividualPointOrBar) {
                        drawSelectionHighlightPopUp(
                            combineGraphData,
                            barTapLocks,
                            linePointLocks,
                            isTapped
                        )
                    }
                    if (canSupportTapOnIndividualPointOrBar) {
                        val x = linePointLocks.values.firstOrNull()?.second?.x
                        if (x != null) identifiedPoint =
                            linePointLocks.values.map { it.first }.first()
                        val selectedOffset = linePointLocks.values.firstOrNull()?.second
                        if (isTapped && selectedOffset.isNotNull()) {
                            drawHighlightText(
                                identifiedPoint,
                                selectedOffset ?: Offset(0f, 0f),
                                line.selectionHighlightPopUp
                            )
                        }
                    }
                },
                onPointClicked = { offset: Offset, _: Float ->
                    isTapped = true
                    tapOffset = offset
                },
                onScroll = {
                    isTapped = false
                }
            )
        }
    }
}

/**
 * DrawScope.drawSelectionHighlightPopUp is an extension method used to draw the highlight pop up
 * @param combinedLineAndBarGraphData : Data related to the graph to be drawn.
 * @param barTapLocks: Selected bar details list
 * @param linePointLocks: Selected points in line list
 * @param isTapped: True if point is selected else false.
 */
private fun DrawScope.drawSelectionHighlightPopUp(
    combinedLineAndBarGraphData: CombinedLineAndBarGraphData,
    barTapLocks: MutableMap<Int, Pair<BarData, Offset>>,
    linePointLocks: MutableMap<Int, Pair<Point, Offset>>,
    isTapped: Boolean
) {
    combinedLineAndBarGraphData.selectionHighLightPopUp?.let {
        val barOffset = barTapLocks.values.firstOrNull()?.second ?: Offset(0f, 0f)
        val pointOffset =
            linePointLocks.values.firstOrNull()?.second ?: Offset(0f, 0f)
        val selectedOffset = Offset(barOffset.x, minOf(barOffset.y, pointOffset.y))
        val barSelectedData =
            barTapLocks.values.firstOrNull()?.first ?: BarData(
                point = Point(
                    0f,
                    0f
                )
            )
        val pointSelectedData =
            linePointLocks.values.firstOrNull()?.first ?: Point(0f, 0f)
        if (isTapped) {
            val centerPointOfBar =
                selectedOffset.x + combinedLineAndBarGraphData.barPlotData.barStyle.barWidth.toPx() / 2
            // Drawing the highlighted background and text
            it.drawPopUp(
                this,
                selectedOffset,
                pointSelectedData,
                barSelectedData,
                centerPointOfBar
            )
        }
    }
}
