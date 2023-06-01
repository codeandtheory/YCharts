package co.yml.charts.ui.barchart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.axis.XAxis
import co.yml.charts.axis.YAxis
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.extensions.ColumnClip
import co.yml.charts.common.extensions.getMaxElementInXAxis
import co.yml.charts.common.extensions.getXMaxAndMinPoints
import co.yml.charts.common.extensions.getYMaxAndMinPoints
import co.yml.charts.common.extensions.isYAxisTapped
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.ChartConstants
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Horizontal bar chart
 *
 * @param barChartData
 * @param modifier
 * @param scope
 * @param accessibilitySheetState
 * @param isTalkBackEnabled
 */
@OptIn(ExperimentalMaterialApi::class)
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
                LocalDensity.current.run { ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
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
                chartData.forEachIndexed { _, barData ->
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