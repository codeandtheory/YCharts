@file:OptIn(ExperimentalMaterialApi::class)

package com.ygraph.components.charts.combinedgraph

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.charts.barchart.drawUnderScrollMask
import com.ygraph.components.charts.barchart.getGroupBarDrawOffset
import com.ygraph.components.common.components.ItemDivider
import com.ygraph.components.common.components.accessibility.AccessibilityBottomSheetDialog
import com.ygraph.components.common.components.accessibility.CombinedGraphInfo
import com.ygraph.components.common.extensions.*
import com.ygraph.components.common.extensions.getMaxElementInYAxis
import com.ygraph.components.common.model.PlotData
import com.ygraph.components.common.model.PlotType
import com.ygraph.components.common.model.Point
import com.ygraph.components.graph.bargraph.*
import com.ygraph.components.charts.barchart.getMaxScrollDistance
import com.ygraph.components.charts.barchart.highlightGroupBar
import com.ygraph.components.charts.barchart.models.BarData
import com.ygraph.components.charts.barchart.models.BarPlotData
import com.ygraph.components.charts.barchart.models.GroupBar
import com.ygraph.components.charts.combinedgraph.model.CombinedGraphData
import com.ygraph.components.charts.linechart.*
import com.ygraph.components.graph.linegraph.*
import com.ygraph.components.charts.linechart.model.LinePlotData
import com.ygraph.components.chartcontainer.container.ScrollableCanvasContainer
import kotlinx.coroutines.launch

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
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()
    if (accessibilitySheetState.isVisible && isTalkBackEnabled
        && combinedGraphData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        BackHandler {
            scope.launch {
                accessibilitySheetState.hide()
            }
        }
    }
    Surface(modifier) {
        with(combinedGraphData) {
            var xOffset by remember { mutableStateOf(0f) }
            var isTapped by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = paddingEnd
            val linePlotData: LinePlotData =
                getDataFromType(combinedPlotDataList, PlotType.Line) as? LinePlotData
                    ?: LinePlotData.default()
            val barPlotData: BarPlotData =
                getDataFromType(combinedPlotDataList, PlotType.Bar) as? BarPlotData
                    ?: BarPlotData.default()
            val linePoints: List<Point> =
                linePlotData.lines.flatMap { line -> line.dataPoints.map { it } }
            val barPoints = barPlotData.groupBarList.flatMap { bar -> bar.barList.map { it } }
            val bgColor = MaterialTheme.colors.surface
            val xMin =
                minOf(linePoints.minOf { it.x }, (barPlotData.groupBarList.size).toFloat())
            val xMax =
                maxOf(linePoints.maxOf { it.x }, (barPlotData.groupBarList.size).toFloat())
            val yMin = minOf(linePoints.minOf { it.y }, barPoints.minOf { it.point.y })
            val yMax = maxOf(linePoints.maxOf { it.y }, barPoints.maxOf { it.point.y })
            val requiredSteps =
                maxOf(
                    linePlotData.lines.map { it.dataPoints.size - 1 }.maxOf { it },
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
            var identifiedBarPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
            var identifiedPoint by remember { mutableStateOf(Point(0f, 0f)) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }

            ScrollableCanvasContainer(
                modifier = modifier
                    .semantics {
                        contentDescription = accessibilityConfig.chartDescription
                    }
                    .clickable {
                        if (isTalkBackEnabled) {
                            scope.launch {
                                accessibilitySheetState.animateTo(
                                    ModalBottomSheetValue.Expanded
                                )
                            }
                        }
                    },
                containerBackgroundColor = backgroundColor,
                isPinchZoomEnabled = isZoomAllowed,
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
                        chartData = axisPoints
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
                    val barTapLocks = mutableMapOf<Int, Pair<BarData, Offset>>()
                    val linePointLocks = mutableMapOf<Int, Pair<Point, Offset>>()

                    for (plotData in combinedPlotDataList) {
                        when (plotData) {
                            is LinePlotData -> {
                                // Draw line graph
                                val xStartPosition =
                                    columnWidth + horizontalExtraSpace.toPx() +
                                            ((barPlotData.barStyle.barWidth.toPx() * barPlotData.groupingSize) / 2)
                                plotData.lines.forEach { line ->
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
                                            line.selectionHighlightPoint?.copy(
                                                isHighlightLineRequired = false
                                            )
                                        )
                                        if (line.selectionHighlightPopUp != null) {
                                            val x =
                                                linePointLocks.values.firstOrNull()?.second?.x
                                            if (x != null) identifiedPoint =
                                                linePointLocks.values.map { it.first }.first()
                                            val selectedOffset =
                                                linePointLocks.values.firstOrNull()?.second
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
                            }
                            is BarPlotData -> {
                                // Draw bar graph
                                plotData.groupBarList.forEachIndexed { index, groupBarData ->
                                    var insideOffset = 0f
                                    groupBarData.barList.forEachIndexed { subIndex, individualBar ->
                                        val drawOffset = getGroupBarDrawOffset(
                                            index, individualBar.point.y, xOffset, xLeft,
                                            scrollOffset, yBottom, yOffset, 0f
                                        )
                                        val height = yBottom - drawOffset.y

                                        val individualOffset =
                                            Offset(drawOffset.x + insideOffset, drawOffset.y)

                                        // drawing each individual bars
                                        drawGroupBarGraph(
                                            plotData,
                                            individualOffset,
                                            height,
                                            subIndex
                                        )
                                        insideOffset += plotData.barStyle.barWidth.toPx()

                                        val middleOffset =
                                            Offset(
                                                individualOffset.x + plotData.barStyle.barWidth.toPx() / 2,
                                                drawOffset.y
                                            )
                                        // store the tap points for selection
                                        if (isTapped && middleOffset.isTapped(
                                                tapOffset,
                                                plotData.barStyle.barWidth.toPx(),
                                                yBottom,
                                                tapPadding.toPx()
                                            )
                                        ) {
                                            barTapLocks[0] = individualBar to individualOffset
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (isTapped && linePointLocks.isEmpty() &&
                        barPlotData.barStyle.selectionHighlightData != null
                    ) {
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
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)
                },
                onPointClicked = { offset: Offset, _: Float ->
                    isTapped = true
                    tapOffset = offset
                },
                onScroll = {
                    isTapped = false
                }
            )
            if (isTalkBackEnabled) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(), backgroundColor = Color.White, content = {
                        LazyColumn {
                            items(xMax.toInt()) { index ->
                                Column {
                                    val lineData: MutableList<Point> = mutableListOf()
                                    val lineColors: MutableList<Color> = mutableListOf()
                                    linePlotData.lines.forEachIndexed { _, line ->
                                        lineColors.add(line.lineStyle.color)
                                        line.dataPoints.forEachIndexed { pointIndex, point ->
                                            if (pointIndex == index) {
                                                lineData.add(point)
                                            }
                                        }
                                    }
                                    val groupBarData: GroupBar? =
                                        barPlotData.groupBarList.filterIndexed { barIndex, _ -> barIndex == index }
                                            .firstOrNull()
                                    CombinedGraphInfo(
                                        pointsList = lineData,
                                        lineColor = lineColors,
                                        groupBar = groupBarData,
                                        axisLabelDescription = xAxisData.axisLabelDescription(
                                            xAxisData.labelData(index)
                                        ),
                                        barColorPaletteList = barPlotData.barColorPaletteList,
                                        dividerColor = accessibilityConfig.dividerColor
                                    )
                                    ItemDivider(
                                        thickness = accessibilityConfig.dividerThickness,
                                        dividerColor = accessibilityConfig.dividerColor
                                    )
                                }
                            }
                        }
                    },
                    popUpTopRightButtonTitle = accessibilityConfig.popUpTopRightButtonTitle,
                    popUpTopRightButtonDescription = accessibilityConfig.popUpTopRightButtonDescription,
                    sheetState = accessibilitySheetState
                )
            }
        }
    }
}

/**
 * Returns data for given plot type from the combinedPlotDataList
 * @param combinedPlotDataList : List of combined plot data
 * @param type: Type of plot of with data is to be returned.
 */
private fun getDataFromType(combinedPlotDataList: List<PlotData>, type: PlotType): PlotData? {
    return when (type) {
        is PlotType.Line -> combinedPlotDataList.filterIsInstance<LinePlotData>().firstOrNull()
        is PlotType.Bar -> combinedPlotDataList.filterIsInstance<BarPlotData>().firstOrNull()
        else -> null // Handle if required in future.
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
    val color = if (subIndex < barPlotData.barColorPaletteList.size) {
        barPlotData.barColorPaletteList[subIndex]
    } else Color.Transparent
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
