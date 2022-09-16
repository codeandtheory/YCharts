package com.ygraph.components.graph.combinedgraph

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.common.extensions.RowClip
import com.ygraph.components.common.extensions.getMaxElementInYAxis
import com.ygraph.components.common.extensions.getXMaxAndMinPoints
import com.ygraph.components.common.extensions.getYMaxAndMinPoints
import com.ygraph.components.graph.bargraph.drawBarGraph
import com.ygraph.components.graph.bargraph.drawUnderScrollMask
import com.ygraph.components.graph.bargraph.getDrawOffset
import com.ygraph.components.graph.bargraph.getMaxScrollDistance
import com.ygraph.components.graph.combinedgraph.model.CombinedLineAndBarGraphData
import com.ygraph.components.graph.linegraph.drawShadowUnderLineAndIntersectionPoint
import com.ygraph.components.graph.linegraph.drawStraightOrCubicLine
import com.ygraph.components.graph.linegraph.getCubicPoints
import com.ygraph.components.graph.linegraph.getMappingPointsToGraph
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
            var barHighlightVisibility by remember { mutableStateOf(false) }
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
                axisTopPadding = paddingTop,
                shouldDrawAxisLineTillEnd = true
            )
            val yAxisData =
                yAxisData.copy(axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() })
            val maxElementInYAxis = getMaxElementInYAxis(yMax, yAxisData.steps)


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
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - yAxisData.axisTopPadding.toPx()) / maxElementInYAxis)
                    xOffset =
                        ((barPlotData.barStyle.barWidth).toPx() + barPlotData.barStyle.paddingBetweenBars.toPx()) * xZoom
                    val xLeft =
                        columnWidth + horizontalExtraSpace.toPx() + barPlotData.barStyle.barWidth.toPx() / 2

                    // Draw bar graph
                    barPlotData.barData.forEachIndexed { _, barData ->
                        val drawOffset = getDrawOffset(
                            barData.point, xMin, xOffset, xLeft,
                            scrollOffset, yBottom, yOffset, 0f
                        )
                        val height = yBottom - drawOffset.y

                        // drawing each individual bars
                        drawBarGraph(barPlotData.barStyle, barData, drawOffset, height)

                        /*   val middleOffset =
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
                           }*/
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

                    // Draw Lines and Points and AreaUnderLine
                    // Draw area under curve
                    drawShadowUnderLineAndIntersectionPoint(cubicPath, pointsData, yBottom, line)

                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)
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
                onPointClicked = { offset: Offset, _: Float ->
                    isTapped = true
                    barHighlightVisibility = true

                },
                onScroll = {
                    isTapped = false
                    barHighlightVisibility = false
                }
            )
        }
    }
}
