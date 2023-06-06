package co.yml.charts.ui.barchart

import androidx.compose.foundation.clickable
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
import co.yml.charts.axis.XAxis
import co.yml.charts.axis.YAxis
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.extensions.RowClip
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.extensions.getXMaxAndMinPoints
import co.yml.charts.common.extensions.getYMaxAndMinPoints
import co.yml.charts.common.extensions.isTapped
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.ChartConstants
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * composable for drawing Vertical bar chart
 *
 * @param barChartData
 * @param modifier
 * @param scope
 * @param accessibilitySheetState
 * @param isTalkBackEnabled
 */
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
            rowHeight = LocalDensity.current.run { ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
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