@file:OptIn(ExperimentalMaterialApi::class)

package co.yml.charts.ui.barchart

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.XAxis
import co.yml.charts.axis.YAxis
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.GroupBarInfo
import co.yml.charts.common.extensions.RowClip
import co.yml.charts.common.extensions.collectIsTalkbackEnabledAsState
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.extensions.isStackedBarTapped
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.ChartConstants
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.GroupBarChartData
import kotlinx.coroutines.launch

/**
 *
 * [StackedBarChart] compose method for drawing stacked bar chart.
 * @param modifier: All modifier related properties
 * @param groupBarChartData : All data needed to stacked bar chart
 * @see [GroupBarChartData] Data class to save all params related to stacked bar chart
 */
@Composable
fun StackedBarChart(modifier: Modifier, groupBarChartData: GroupBarChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()
    if (accessibilitySheetState.isVisible && isTalkBackEnabled
        && groupBarChartData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        BackHandler {
            scope.launch {
                accessibilitySheetState.hide()
            }
        }
    }
    Surface(modifier.fillMaxSize()) {
        with(groupBarChartData.barPlotData) {
            var visibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
            var xOffset by remember { mutableStateOf(0f) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
            var isTapped by remember { mutableStateOf(false) }
            var columnWidth by remember { mutableStateOf(0f) }
            var horizontalGap by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            val paddingRight = groupBarChartData.paddingEnd
            val valueList = mutableListOf<Float>()
            groupBarList.map { groupBar ->
                var yMax = 0f
                groupBar.barList.forEach {
                    yMax += it.point.y
                }
                valueList.add(yMax)
            }
            val bgColor = MaterialTheme.colors.surface

            val xMax = groupBarList.size
            val yMax = valueList.maxOrNull() ?: 0f
            val xAxisData =
                groupBarChartData.xAxisData.copy(axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars)
            val yAxisData =
                groupBarChartData.yAxisData.copy(axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() })

            val maxElementInYAxis = getMaxElementInYAxis(yMax, yAxisData.steps)
            val paddingBetweenBars =
                LocalDensity.current.run { groupBarChartData.paddingBetweenStackedBars.toPx() }

            if (!groupBarChartData.showXAxis) {
                rowHeight =
                    LocalDensity.current.run { ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
            }
            ScrollableCanvasContainer(
                modifier = modifier
                    .semantics {
                        contentDescription = groupBarChartData.accessibilityConfig.chartDescription
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
                containerBackgroundColor = groupBarChartData.backgroundColor,
                calculateMaxDistance = { xZoom ->
                    horizontalGap = groupBarChartData.horizontalExtraSpace.toPx()
                    val xLeft = (xAxisData.startDrawPadding.toPx() * xZoom) + horizontalGap
                    xOffset =
                        (barStyle.barWidth.toPx() + barStyle.paddingBetweenBars.toPx()) * xZoom
                    getMaxScrollDistance(
                        columnWidth = columnWidth,
                        xMax = xMax.toFloat(),
                        xMin = 0f,
                        xOffset = xOffset,
                        xLeft = xLeft,
                        paddingRight = paddingRight.toPx(),
                        canvasWidth = size.width
                    )
                },
                onDraw = { scrollOffset, xZoom ->
                    val isHighlightFullBar =
                        barStyle.selectionHighlightData?.isHighlightFullBar ?: false
                    val yBottom = size.height - rowHeight

                    val totalPaddingBtwBars =
                        (groupBarList.first().barList.size - 1) * paddingBetweenBars
                    val yOffset =
                        (yBottom - yAxisData.axisTopPadding.toPx() - totalPaddingBtwBars) / maxElementInYAxis
                    xOffset =
                        (barStyle.barWidth.toPx() + barStyle.paddingBetweenBars.toPx()) * xZoom
                    val xLeft = columnWidth
                    val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                    // Draw bar lines
                    groupBarList.forEachIndexed { index, groupBarData ->
                        var insideOffset = 0f
                        val xPointOffset =
                            groupBarData.barList.first().point.x * xOffset + xLeft + (xAxisData.startDrawPadding.toPx() * xZoom) - barStyle.barWidth.toPx() / 2 - scrollOffset
                        val fullBarDetails = getFullBarDetails(
                            groupBarData.barList,
                            totalPaddingBtwBars,
                            yOffset,
                            yBottom,
                            xPointOffset
                        )

                        groupBarData.barList.forEachIndexed { subIndex, individualBar ->
                            val drawOffset = getGroupBarDrawOffset(
                                x = index,
                                y = individualBar.point.y,
                                xOffset = xOffset,
                                xLeft = xLeft,
                                scrollOffset = scrollOffset,
                                yBottom = yBottom,
                                yOffset = yOffset,
                                yMin = 0f,
                                xMin = 0f,
                                startDrawPadding = xAxisData.startDrawPadding.toPx(),
                                zoomScale = xZoom,
                                barWidth = barStyle.barWidth.toPx()
                            )

                            val height = yBottom - drawOffset.y

                            val individualOffset = Offset(drawOffset.x, drawOffset.y - insideOffset)

                            // drawing each individual bars
                            groupBarChartData.drawBar(
                                this,
                                groupBarChartData,
                                barStyle,
                                individualOffset,
                                height,
                                subIndex
                            )

                            insideOffset += height + paddingBetweenBars

                            val middleOffset =
                                Offset(
                                    drawOffset.x + barStyle.barWidth.toPx() / 2,
                                    individualOffset.y
                                )

                            if (isTapped && middleOffset.isStackedBarTapped(
                                    tapOffset = tapOffset,
                                    barWidth = barStyle.barWidth.toPx(),
                                    barHeight = if (isHighlightFullBar) yBottom else individualOffset.y + height,
                                    tapPadding = groupBarChartData.tapPadding.toPx()
                                )
                            ) {
                                if (isHighlightFullBar) {
                                    dragLocks[0] = fullBarDetails.first to fullBarDetails.second
                                } else {
                                    dragLocks[0] = individualBar to individualOffset
                                }
                            }

                            drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                            if (barStyle.selectionHighlightData != null) {
                                // highlighting the selected bar and showing the data points
                                identifiedPoint = highlightGroupBar(
                                    dragLocks = dragLocks,
                                    visibility = visibility,
                                    identifiedPoint = identifiedPoint,
                                    selectionHighlightData = barStyle.selectionHighlightData,
                                    isDragging = isTapped,
                                    columnWidth = columnWidth,
                                    yBottom = yBottom,
                                    paddingRight = paddingRight,
                                    yOffset = yOffset,
                                    barWidth = barStyle.barWidth,
                                    totalPaddingBtwBars,
                                    isHighlightFullBar
                                )
                            }
                        }
                    }
                },
                drawXAndYAxis = { scrollOffset, xZoom ->
                    val points = mutableListOf<Point>()
                    for (index in groupBarList.indices) {
                        points.add(Point(index.toFloat(), 0f))
                    }

                    if (groupBarChartData.showXAxis) {
                        XAxis(
                            xAxisData = xAxisData,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(
                                    RowClip(
                                        columnWidth, paddingRight
                                    )
                                )
                                .onGloballyPositioned {
                                    rowHeight = it.size.height.toFloat()
                                },
                            xStart = columnWidth,
                            scrollOffset = scrollOffset,
                            zoomScale = xZoom,
                            chartData = points,
                            axisStart = columnWidth
                        )
                    }
                    if (groupBarChartData.showYAxis) {
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
        if (isTalkBackEnabled) {
            with(groupBarChartData) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(), backgroundColor = Color.White, content = {
                        LazyColumn {
                            items(barPlotData.groupBarList.size) { index ->
                                Column {
                                    GroupBarInfo(
                                        barPlotData.groupBarList[index],
                                        xAxisData.axisLabelDescription(
                                            xAxisData.labelData(index)
                                        ),
                                        barPlotData.barColorPaletteList
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
 * @param barDataList : List of bar details for selected stacked bar
 * @param totalPaddingBtwBars : Total padding stacked bars
 * @param yOffset : Offset of Y axis point
 * @param yBottom : starting y offset  of y Axis
 * @param xPointOffset :  Offset of X axis point
 */
fun getFullBarDetails(
    barDataList: List<BarData>,
    totalPaddingBtwBars: Float,
    yOffset: Float,
    yBottom: Float,
    xPointOffset: Float
): Pair<BarData, Offset> {
    var yPoint = 0f

    barDataList.forEach {
        yPoint += it.point.y
    }
    val fullBarYOffset = yBottom - totalPaddingBtwBars - (yPoint * yOffset)
    val fullBarOffset = Offset(xPointOffset, fullBarYOffset)
    val fullBarData = BarData(Point(barDataList.first().point.x, yPoint))

    return Pair(fullBarData, fullBarOffset)
}