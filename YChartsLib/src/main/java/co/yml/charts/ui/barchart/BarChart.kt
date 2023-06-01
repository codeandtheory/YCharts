@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package co.yml.charts.ui.barchart


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.axis.XAxis
import co.yml.charts.axis.YAxis
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.BarInfo
import co.yml.charts.common.extensions.ColumnClip
import co.yml.charts.common.extensions.RowClip
import co.yml.charts.common.extensions.collectIsTalkbackEnabledAsState
import co.yml.charts.common.extensions.getMaxElementInXAxis
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.extensions.getXMaxAndMinPoints
import co.yml.charts.common.extensions.getYMaxAndMinPoints
import co.yml.charts.common.extensions.isTapped
import co.yml.charts.common.extensions.isYAxisTapped
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.SelectionHighlightData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 *
 * [BarChart] compose method for drawing bar chart.
 * @param modifier: All modifier related properties
 * @param barChartData : All data needed to Bar Chart
 * @see [BarChartData] Data class to save all params related to Bar Chart
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BarChart(modifier: Modifier, barChartData: BarChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()

    if (accessibilitySheetState.isVisible && isTalkBackEnabled
        && barChartData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        BackHandler {
            scope.launch {
                accessibilitySheetState.hide()
            }
        }
    }
    Surface(modifier) {
        when (barChartData.barChartType) {
            BarChartType.HORIZONTAL -> HorizontalBarChart(
                barChartData = barChartData,
                modifier = modifier,
                scope = scope,
                accessibilitySheetState = accessibilitySheetState,
                isTalkBackEnabled = isTalkBackEnabled
            )

            BarChartType.VERTICAL -> VerticalBarChart(
                barChartData = barChartData,
                modifier = modifier,
                scope = scope,
                accessibilitySheetState = accessibilitySheetState,
                isTalkBackEnabled = isTalkBackEnabled
            )
        }

        if (isTalkBackEnabled) {
            with(barChartData) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Color.White,
                    content = {
                        LazyColumn {
                            items(chartData.size) { index ->
                                Column {
                                    BarInfo(
                                        if (barChartType == BarChartType.VERTICAL) {
                                            xAxisData.axisLabelDescription(
                                                xAxisData.labelData(
                                                    index
                                                )
                                            )
                                        } else {
                                            yAxisData.axisLabelDescription(
                                                yAxisData.labelData(
                                                    index
                                                )
                                            )
                                        },
                                        chartData[index].description,
                                        chartData[index].color
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VerticalBarChart(
    barChartData: BarChartData,
    modifier: Modifier,
    scope: CoroutineScope,
    accessibilitySheetState: ModalBottomSheetState,
    isTalkBackEnabled: Boolean
) {
    with(barChartData) {
        var barHighlightVisibility by remember { mutableStateOf(false) }
        var identifiedPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
        var xOffset by remember { mutableStateOf(0f) }
        var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
        var isTapped by remember { mutableStateOf(false) }
        var columnWidth by remember { mutableStateOf(0f) }
        var horizontalGap by remember { mutableStateOf(0f) }
        var rowHeight by remember { mutableStateOf(0f) }
        val paddingRight = paddingEnd
        val points = chartData.map { it.point }
        val bgColor = MaterialTheme.colorScheme.surface

        val (xMin, xMax) = getXMaxAndMinPoints(points)
        val (_, yMax) = getYMaxAndMinPoints(points)

        val xAxisData =
            xAxisData.copy(
                axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars,
                steps = chartData.size - 1
            )
        val yAxisData =
            yAxisData.copy(
                axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() }
            )
        val maxElementInYAxis = getMaxElementInYAxis(yMax, yAxisData.steps)

        if (!showXAxis) {
            rowHeight = LocalDensity.current.run { DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
        }

        ScrollableCanvasContainer(modifier = modifier
            .semantics {
                contentDescription = barChartData.accessibilityConfig.chartDescription
            }
            .clickable {
                if (isTalkBackEnabled) {
                    scope.launch {
                        accessibilitySheetState.show()
                    }
                }
            },
            containerBackgroundColor = backgroundColor,
            calculateMaxDistance = { xZoom ->
                horizontalGap = horizontalExtraSpace.toPx()
                val xLeft = (xAxisData.startDrawPadding.toPx() * xZoom) + horizontalGap
                xOffset =
                    (barStyle.barWidth.toPx() + barStyle.paddingBetweenBars.toPx()) * xZoom
                getMaxScrollDistance(
                    columnWidth, xMax, xMin, xOffset, xLeft, paddingRight.toPx(), size.width
                )
            },
            onDraw = { scrollOffset, xZoom ->
                val yBottom = size.height - rowHeight
                val yOffset = ((yBottom - yAxisData.axisTopPadding.toPx()) / maxElementInYAxis)
                xOffset =
                    ((barStyle.barWidth).toPx() + barStyle.paddingBetweenBars.toPx()) * xZoom
                val xLeft = columnWidth
                val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                // Draw bar lines
                chartData.forEachIndexed { _, barData ->
                    val drawOffset = getDrawOffset(
                        barData.point,
                        xMin,
                        xOffset,
                        xLeft,
                        scrollOffset,
                        yBottom,
                        yOffset,
                        0f,
                        xAxisData.startDrawPadding.toPx(),
                        xZoom,
                        barStyle.barWidth.toPx()
                    )
                    val height = yBottom - drawOffset.y

                    // drawing each individual bars
                    barChartData.drawBar(this, barData, drawOffset, height, barChartType, barStyle)

                    val middleOffset =
                        Offset(drawOffset.x + barStyle.barWidth.toPx() / 2, drawOffset.y)
                    // store the tap points for selection
                    if (isTapped && middleOffset.isTapped(
                            tapOffset, barStyle.barWidth.toPx(), yBottom, tapPadding.toPx()
                        )
                    ) {
                        dragLocks[0] = barData to drawOffset
                    }
                }

                drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                if (barStyle.selectionHighlightData != null) {
                    // highlighting the selected bar and showing the data points
                    identifiedPoint = highlightVerticalBar(
                        dragLocks,
                        barHighlightVisibility,
                        identifiedPoint,
                        barChartData.barStyle,
                        isTapped,
                        columnWidth,
                        yBottom,
                        paddingRight,
                        yOffset,
                        true,
                        0f,
                        barChartType
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
                        scrollOffset = scrollOffset,
                        zoomScale = xZoom,
                        chartData = points
                    )
                }
            }, onPointClicked = { offset: Offset, _: Float ->
                isTapped = true
                barHighlightVisibility = true
                tapOffset = offset
            }, onScroll = {
                isTapped = false
                barHighlightVisibility = false
            })
    }

}

@Composable
fun HorizontalBarChart(
    barChartData: BarChartData,
    modifier: Modifier,
    scope: CoroutineScope,
    accessibilitySheetState: ModalBottomSheetState,
    isTalkBackEnabled: Boolean
) {
    with(barChartData) {
        var barHighlightVisibility by remember { mutableStateOf(false) }
        var identifiedPoint by remember { mutableStateOf(BarData(Point(0f, 0f))) }
        var xOffset by remember { mutableStateOf(0f) }
        var yOffset by remember { mutableStateOf(0f) }
        var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
        var isTapped by remember { mutableStateOf(false) }
        var columnWidth by remember { mutableStateOf(0f) }
        var horizontalGap by remember { mutableStateOf(0f) }
        var rowHeight by remember { mutableStateOf(0f) }
        val points = chartData.map { it.point }
        val bgColor = MaterialTheme.colorScheme.surface

        val (_, xMax) = getXMaxAndMinPoints(points)
        val (yMin, yMax) = getYMaxAndMinPoints(points)

        val xAxisData = xAxisData.copy(
            dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true)
        )
        val yAxisData = yAxisData.copy(
            axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars,
            steps = chartData.size - 1,
            axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() })
        val maxElementInXAxis = getMaxElementInXAxis(xMax, xAxisData.steps)

        if (!showXAxis) {
            rowHeight =
                LocalDensity.current.run { DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
        }

        ScrollableCanvasContainer(modifier = modifier
            .semantics {
                contentDescription = barChartData.accessibilityConfig.chartDescription
            }
            .clickable {
                if (isTalkBackEnabled) {
                    scope.launch {
                        accessibilitySheetState.show()
                    }
                }
            },
            scrollOrientation = Orientation.Vertical,
            containerBackgroundColor = backgroundColor,
            calculateMaxDistance = { yZoom ->
                horizontalGap = horizontalExtraSpace.toPx()
                val yStart = horizontalGap + (yAxisData.startDrawPadding.toPx() * yZoom)
                yOffset =
                    (barStyle.barWidth.toPx() + barStyle.paddingBetweenBars.toPx()) * yZoom

                getMaxScrollDistance(
                    rowHeight,
                    yMax,
                    yMin,
                    yOffset,
                    yStart,
                    yAxisData.axisTopPadding.toPx(),
                    size.height
                )
            },
            onDraw = { scrollOffset, yZoom ->
                val xLeft = columnWidth
                val startPaddingFromBottom =
                    (yAxisData.startDrawPadding.toPx() * yZoom) + barStyle.barWidth.toPx() / 2
                val yStart =
                    if (yAxisData.dataCategoryOptions.isDataCategoryStartFromBottom) startPaddingFromBottom else {
                        if (yZoom < 1) startPaddingFromBottom else yAxisData.axisTopPadding.toPx()
                    }
                val yBottom = size.height - rowHeight
                yOffset = (barStyle.barWidth.toPx() + barStyle.paddingBetweenBars.toPx()) * yZoom

                xOffset = (size.width - xLeft - xAxisData.axisEndPadding.toPx()) / maxElementInXAxis
                val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                // Draw bar lines
                chartData.forEachIndexed { index, barData ->
                    val drawOffset = getDrawHorizontalOffset(
                        barData.point,
                        xLeft,
                        scrollOffset,
                        yBottom,
                        yOffset,
                        yMin,
                        yMax,
                        yStart,
                        yAxisData.dataCategoryOptions,
                        yZoom
                    )
                    val width = (barData.point.x) * xOffset

                    // drawing each individual bars
                    barChartData.drawBar(this, barData, drawOffset, width, barChartType, barStyle)

                    val middleOffset =
                        Offset(drawOffset.x, drawOffset.y + barStyle.barWidth.toPx() / 2)

                    val xAxisWidth =
                        xLeft + ((barData.point.x - 0) * xOffset) + barStyle.barWidth.toPx()
                    // store the tap points for selection
                    if (isTapped && middleOffset.isYAxisTapped(
                            tapOffset,
                            barStyle.barWidth.toPx(),
                            xLeft,
                            tapPadding.toPx(),
                            xAxisWidth
                        )
                    ) {
                        dragLocks[0] = barData to drawOffset
                    }
                }

                drawUnderXAxisScrollMask(rowHeight, yAxisData.axisTopPadding, bgColor)

                if (barStyle.selectionHighlightData != null) {
                    // highlighting the selected bar and showing the data points
                    identifiedPoint = highlightHorizontalBar(
                        dragLocks,
                        barHighlightVisibility,
                        identifiedPoint,
                        barChartData.barStyle,
                        isTapped,
                        yBottom,
                        yAxisData.axisTopPadding,
                        xLeft,
                        xOffset
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
                            .onGloballyPositioned {
                                rowHeight = it.size.height.toFloat()
                            },
                        xStart = columnWidth,
                        scrollOffset = 0f,
                        zoomScale = xZoom,
                        chartData = points,
                        axisStart = columnWidth
                    )
                }

                if (showYAxis) {
                    val startPaddingFromBottom = LocalDensity.current.run {
                        (yAxisData.startDrawPadding.toPx() * xZoom)
                    }
                    YAxis(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxHeight()
                            .wrapContentWidth()
                            .clip(
                                ColumnClip(
                                    0f,
                                    LocalDensity.current.run { yAxisData.axisTopPadding.toPx() },
                                    columnWidth,
                                    rowHeight
                                )
                            )
                            .onGloballyPositioned {
                                columnWidth = it.size.width.toFloat()
                            },
                        yAxisData = yAxisData,
                        scrollOffset = scrollOffset,
                        zoomScale = xZoom,
                        chartData = points,
                        dataCategoryWidth = LocalDensity.current.run { yAxisData.axisStepSize.toPx() },
                        yStart =
                        if (yAxisData.dataCategoryOptions.isDataCategoryStartFromBottom) {
                            startPaddingFromBottom
                        } else {
                            if (xZoom < 1) {
                                startPaddingFromBottom
                            } else {
                                LocalDensity.current.run {
                                    (yAxisData.axisTopPadding.toPx() + barStyle.barWidth.toPx() / 2)
                                }
                            }
                        }, LocalDensity.current.run { barStyle.barWidth.toPx() }
                    )
                }
            }, onPointClicked = { offset: Offset, _: Float ->
                isTapped = true
                barHighlightVisibility = true
                tapOffset = offset
            }, onScroll = {
                isTapped = false
                barHighlightVisibility = false
            })
    }
}

