package com.ygraph.components.graph.combinedgraph

import android.widget.GridLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextOverflow
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.common.extensions.*
import com.ygraph.components.common.extensions.getMaxElementInYAxis
import com.ygraph.components.common.model.Point
import com.ygraph.components.graph.bargraph.*
import com.ygraph.components.graph.bargraph.getMaxScrollDistance
import com.ygraph.components.graph.bargraph.models.Bar
import com.ygraph.components.graph.combinedgraph.model.BarPlotData
import com.ygraph.components.graph.combinedgraph.model.CombinedGraphData
import com.ygraph.components.graph.linegraph.*
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

/**
 *
 * CombinedLineAndBarGraph compose method for drawing combined line and bar graph.
 * @param modifier: All modifier related properties
 * @param combineGraphData : All data needed to Bar Graph
 * @see com.ygraph.components.graph.combinedgraph.model.CombinedGraphData Data class to save all params
 * related to Bar Graph
 */
@Composable
fun CombinedGraph(modifier: Modifier, combinedGraphData: CombinedGraphData) {
    Column(modifier) {
        with(combinedGraphData) {
            var xOffset by remember { mutableStateOf(0f) }
            var isTapped by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = paddingEnd
            val linePoints: List<Point> =
                linePlotData.flatMap { line -> line.dataPoints.map { it } }
            val barPoints = barPlotData.groupBarList.flatMap { bar -> bar.barList.map { it } }
            val bgColor = MaterialTheme.colors.surface
            val xMin =
                minOf(linePoints.minOf { it.x }, (barPlotData.groupBarList.size).toFloat())
            val xMax =
                maxOf(linePoints.maxOf { it.x }, (barPlotData.groupBarList.size).toFloat())
            val yMin = minOf(linePoints.minOf { it.y }, barPoints.minOf { it.value })
            val yMax = maxOf(linePoints.maxOf { it.y }, barPoints.maxOf { it.value })
            val requiredSteps =
                maxOf(
                    linePlotData.map { it.dataPoints.size - 1 }.maxOf { it },
                    barPlotData.groupBarList.map { it.barList.size - 1 }.maxOf { it }
                )
            val xAxisData = xAxisData.copy(
                axisStepSize = ((barPlotData.barStyle.barWidth * barPlotData.groupingSize) +
                        barPlotData.barStyle.paddingBetweenBars),
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
            var identifiedBarPoint by remember { mutableStateOf(Bar(0f, "")) }
            var identifiedPoint by remember { mutableStateOf(Point(0f, 0f)) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }

            Column {
                ScrollableCanvasContainer(modifier = modifier,
                    containerBackgroundColor = backgroundColor,
                    calculateMaxDistance = { xZoom ->
                        xOffset =
                            ((barPlotData.barStyle.barWidth.toPx() * barPlotData.groupingSize) +
                                    barPlotData.barStyle.paddingBetweenBars.toPx()) * xZoom
                        getMaxScrollDistance(
                            columnWidth,
                            xMax,
                            xMin,
                            xOffset,
                            0f,
                            paddingRight.toPx(),
                            size.width
                        )
                    },
                    drawXAndYAxis = { scrollOffset, xZoom ->
                        val axisPoints = mutableListOf<Point>()
                        for (index in barPlotData.groupBarList.indices) {
                            axisPoints.add(Point(index.toFloat(), 0f))
                        }
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
                                (barPlotData.barStyle.barWidth.toPx() * barPlotData.groupingSize) / 2 + horizontalExtraSpace.toPx()
                            },
                            scrollOffset = scrollOffset,
                            zoomScale = xZoom,
                            graphData = axisPoints
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
                        val yOffset =
                            ((yBottom - yAxisData.axisTopPadding.toPx()) / maxElementInYAxis)
                        xOffset =
                            ((barPlotData.barStyle.barWidth.toPx() * barPlotData.groupingSize) +
                                    barPlotData.barStyle.paddingBetweenBars.toPx()) * xZoom
                        val xLeft =
                            columnWidth + horizontalExtraSpace.toPx()
                        val barTapLocks = mutableMapOf<Int, Pair<Bar, Offset>>()
                        val linePointLocks = mutableMapOf<Int, Pair<Point, Offset>>()

                        // Draw bar graph
                        barPlotData.groupBarList.forEachIndexed { index, groupBarData ->
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
                                    barPlotData,
                                    individualOffset,
                                    height,
                                    subIndex
                                )
                                insideOffset += barPlotData.barStyle.barWidth.toPx()

                                val middleOffset =
                                    Offset(
                                        individualOffset.x + barPlotData.barStyle.barWidth.toPx() / 2,
                                        drawOffset.y
                                    )
                                // store the tap points for selection
                                if (isTapped && middleOffset.isTapped(
                                        tapOffset,
                                        barPlotData.barStyle.barWidth.toPx(),
                                        yBottom,
                                        tapPadding.toPx()
                                    )
                                ) {
                                    barTapLocks[0] = individualBar to individualOffset
                                }
                            }
                        }

                        // Draw line graph
                        val xStartPosition =
                            columnWidth + horizontalExtraSpace.toPx() +
                                    ((barPlotData.barStyle.barWidth.toPx() * barPlotData.groupingSize) / 2)
                        linePlotData.forEach { line ->
                            val pointsData = getMappingPointsToGraph(
                                line.dataPoints,
                                xMin,
                                xOffset,
                                xStartPosition,
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
                            drawShadowUnderLineAndIntersectionPoint(
                                cubicPath,
                                pointsData,
                                yBottom,
                                line
                            )

                            pointsData.forEachIndexed { index, point ->
                                if (isTapped && point.isPointTapped(
                                        tapOffset,
                                        tapPadding.toPx()
                                    )
                                ) {
                                    // Dealing with only one line graph hence tapPointLocks[0]
                                    linePointLocks[0] = line.dataPoints[index] to point
                                }
                            }
                            if (isTapped && linePointLocks.isNotEmpty()) {
                                drawHighLightOnSelectedPoint(
                                    linePointLocks,
                                    columnWidth,
                                    paddingRight,
                                    yBottom,
                                    line.selectionHighlightPoint?.copy(isHighlightLineRequired = false)
                                )
                                if (line.selectionHighlightPopUp != null) {
                                    val x = linePointLocks.values.firstOrNull()?.second?.x
                                    if (x != null) identifiedPoint =
                                        linePointLocks.values.map { it.first }.first()
                                    val selectedOffset = linePointLocks.values.firstOrNull()?.second
                                    if (selectedOffset.isNotNull()) {
                                        drawHighlightText(
                                            identifiedPoint,
                                            selectedOffset ?: Offset(0f, 0f),
                                            line.selectionHighlightPopUp
                                        )
                                    }
                                }
                            }
                        }

                        drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                        if (isTapped && linePointLocks.isEmpty() && barPlotData.barStyle.selectionHighlightData != null) {
                            // highlighting the selected bar and showing the data points
                            identifiedBarPoint = highlightGroupBar(
                                barTapLocks,
                                true,
                                identifiedBarPoint,
                                barPlotData.barStyle.selectionHighlightData,
                                isTapped,
                                columnWidth,
                                yBottom,
                                paddingRight,
                                yOffset,
                                barPlotData.barStyle.barWidth
                            )
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
                with(barPlotData.stackLabelConfig) {
                    if (showLabel && stackLabelList.size > 1) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxWidth().wrapContentSize().padding(
                                horizontal = gridPaddingHorizontal,
                                vertical = gridPaddingVertical
                            ),
                            columns = GridCells.Fixed(gridColumnCount)
                        ) {
                            items(stackLabelList) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .background(it.color)
                                            .size(colorBoxSize)
                                    )
                                    Spacer(modifier = Modifier.padding(spaceBWLabelAndColorBox))
                                    Text(
                                        text = it.name,
                                        style = textStyle,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                   }
                }
            }
        }
    }
}

/**
 *
 * Used to draw the individual bars
 * @param barPlotData : all meta data related to the bar graph
 * @param drawOffset: topLeft offset for the drawing the bar
 * @param height : height of the bar graph
 * @param subIndex : Index of the bar
 */
private fun DrawScope.drawGroupBarGraph(
    barPlotData: BarPlotData, drawOffset: Offset,
    height: Float,
    subIndex: Int
) {
    val stackLabelList = barPlotData.stackLabelConfig.stackLabelList
    val color =
        if (subIndex < stackLabelList.size) stackLabelList[subIndex].color else Color.Transparent
    with(barPlotData.barStyle) {
        drawRoundRect(
            color = color,
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
