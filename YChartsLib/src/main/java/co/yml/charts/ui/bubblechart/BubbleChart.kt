@file:OptIn(ExperimentalMaterialApi::class)

package co.yml.charts.ui.bubblechart

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
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
import co.yml.charts.axis.XAxis
import co.yml.charts.axis.YAxis
import co.yml.charts.axis.getXAxisScale
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.BubblePointInfo
import co.yml.charts.common.extensions.RowClip
import co.yml.charts.common.extensions.collectIsTalkbackEnabledAsState
import co.yml.charts.common.extensions.drawGridLines
import co.yml.charts.common.extensions.isNotNull
import co.yml.charts.common.model.Point
import co.yml.charts.ui.bubblechart.model.Bubble
import co.yml.charts.ui.bubblechart.model.BubbleChartData
import co.yml.charts.ui.bubblechart.model.BubbleStyle
import co.yml.charts.ui.linechart.drawHighLightOnSelectedPoint
import co.yml.charts.ui.linechart.drawHighlightText
import co.yml.charts.ui.linechart.getMappingPointsToGraph
import co.yml.charts.ui.linechart.getMaxElementInYAxis
import co.yml.charts.ui.linechart.getYAxisScale
import co.yml.charts.ui.linechart.isTapped
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import kotlinx.coroutines.launch

/**
 * Bubble chart
 *
 * @param modifier
 * @param bubbleChartData
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BubbleChart(modifier: Modifier, bubbleChartData: BubbleChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()
    if (accessibilitySheetState.isVisible && isTalkBackEnabled
        && bubbleChartData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        BackHandler {
            scope.launch {
                accessibilitySheetState.hide()
            }
        }
    }
    Surface(modifier = modifier) {
        with(bubbleChartData) {
            var columnWidth by remember { mutableStateOf(0f) }
            var rowHeight by remember { mutableStateOf(0f) }
            var xOffset by remember { mutableStateOf(0f) }
            val bgColor = MaterialTheme.colorScheme.surface
            var isTapped by remember { mutableStateOf(false) }
            var tapOffset by remember { mutableStateOf(Offset(0f, 0f)) }
            var selectionTextVisibility by remember { mutableStateOf(false) }
            var identifiedPoint by remember { mutableStateOf(Point(0f, 0f)) }

            val bubblePoints: List<Point> = bubbles.map { bubble -> bubble.center }

            val (xMin, xMax, xAxisScale) = getXAxisScale(bubblePoints, xAxisData.steps)
            val (yMin, _, yAxisScale) = getYAxisScale(bubblePoints, yAxisData.steps)
            val maxElementInYAxis = getMaxElementInYAxis(yAxisScale, yAxisData.steps)
            val xAxisData = xAxisData.copy(axisBottomPadding = bottomPadding)
            val yAxisData = yAxisData.copy(
                axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() },
                axisTopPadding = paddingTop
            )

            ScrollableCanvasContainer(modifier = modifier
                .semantics {
                    contentDescription = bubbleChartData.accessibilityConfig.chartDescription
                }
                .clickable {
                    if (isTalkBackEnabled) {
                        scope.launch {
                            accessibilitySheetState.show()
                        }
                    }
                },
                calculateMaxDistance = { xZoom ->
                    xOffset = xAxisData.axisStepSize.toPx() * xZoom
                    getMaxScrollDistance(
                        columnWidth,
                        xMax,
                        xMin,
                        xOffset,
                        paddingRight.toPx(),
                        size.width,
                        containerPaddingEnd.toPx()
                    )
                },
                containerBackgroundColor = backgroundColor,
                isPinchZoomEnabled = isZoomAllowed,
                drawXAndYAxis = { scrollOffset, xZoom ->
                    XAxis(xAxisData = xAxisData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.BottomStart)
                            .onGloballyPositioned {
                                rowHeight = it.size.height.toFloat()
                            }
                            .clip(
                                RowClip(
                                    columnWidth, paddingRight
                                )
                            ),
                        xStart = columnWidth,
                        scrollOffset = scrollOffset,
                        zoomScale = xZoom,
                        chartData = bubblePoints,
                        axisStart = columnWidth)
                    YAxis(
                        modifier = Modifier
                            .fillMaxHeight()
                            .onGloballyPositioned {
                                columnWidth = it.size.width.toFloat()
                            }, yAxisData = yAxisData
                    )

                },
                onDraw = { scrollOffset, xZoom ->
                    val yBottom = size.height - rowHeight
                    val yOffset = ((yBottom - paddingTop.toPx()) / maxElementInYAxis)
                    xOffset = xAxisData.axisStepSize.toPx() * xZoom
                    val xLeft = columnWidth // To add extra space if needed
                    val pointsData = getMappingPointsToGraph(
                        bubblePoints, xMin, xOffset, xLeft, scrollOffset, yBottom, yMin, yOffset
                    )
                    val (cubicPoints1, cubicPoints2) = getCubicPoints(pointsData)
                    val tapPointLocks = mutableMapOf<Int, Pair<Point, Offset>>()
                    // Draw guide lines
                    gridLines?.let {
                        drawGridLines(
                            yBottom,
                            yAxisData.axisTopPadding.toPx(),
                            xLeft,
                            paddingRight,
                            scrollOffset,
                            bubbles.size,
                            xZoom,
                            xAxisScale,
                            yAxisData.steps,
                            xAxisData.axisStepSize,
                            it
                        )
                    }
                    pointsData.forEachIndexed { index, offset ->

                        bubbles[index].draw(this, offset, bubbleChartData.maximumBubbleRadius)

                        pointsData.forEachIndexed { index, point ->
                            if (isTapped && point.isTapped(tapOffset.x, xOffset)) {
                                // Dealing with only one line graph hence tapPointLocks[0]
                                tapPointLocks[0] = bubbles[index].center to point
                            }
                        }

                        val selectedOffset = tapPointLocks.values.firstOrNull()?.second

                        if (selectionTextVisibility && selectedOffset.isNotNull()) {
                            drawHighlightText(
                                identifiedPoint,
                                selectedOffset ?: Offset(0f, 0f),
                                bubbles[index].selectionHighlightPopUp
                            )
                        }
                        if (isTapped) {
                            val x = tapPointLocks.values.firstOrNull()?.second?.x
                            if (x != null) identifiedPoint =
                                tapPointLocks.values.map { it.first }.first()
                            drawHighLightOnSelectedPoint(
                                tapPointLocks,
                                columnWidth,
                                paddingRight,
                                yBottom,
                                bubbles[index].selectionHighlightPoint
                            )
                        }

                    }
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)


                },
                onPointClicked = { offset: Offset, _: Float ->
                    isTapped = true
                    selectionTextVisibility = true
                    tapOffset = offset
                },
                onScroll = {
                    isTapped = false
                    selectionTextVisibility = false
                },
                onZoomInAndOut = {
                    isTapped = false
                    selectionTextVisibility = false
                })

        }
        if (isTalkBackEnabled) {
            with(bubbleChartData) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Color.White,
                    content = {
                        LazyColumn {
                            items(bubbles?.size ?: 0) { index ->
                                Column {
                                    BubblePointInfo(
                                        xAxisData.axisLabelDescription(
                                            xAxisData.labelData(
                                                index
                                            )
                                        ),
                                        bubbles[index].center.description,
                                        bubbles[index].bubbleStyle.solidColor
                                            ?: bubbles[index].bubbleStyle.gradientColors.first()
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
 * Get max scroll distance
 *
 * @param columnWidth
 * @param xMax
 * @param xMin
 * @param xOffset
 * @param paddingRight
 * @param canvasWidth
 * @param containerPaddingEnd
 * @return
 */
fun getMaxScrollDistance(
    columnWidth: Float,
    xMax: Float,
    xMin: Float,
    xOffset: Float,
    paddingRight: Float,
    canvasWidth: Float,
    containerPaddingEnd: Float = 0f
): Float {
    val xLastPoint = (xMax - xMin) * xOffset + columnWidth + paddingRight + containerPaddingEnd
    return if (xLastPoint > canvasWidth) {
        xLastPoint - canvasWidth
    } else 0f
}

/**
 *
 * DrawScope.drawUnderScrollMask extension method used  for drawing a rectangular mask to make graph scrollable under the YAxis.
 * @param columnWidth : Width of the rectangular mask here width of Y Axis is used.
 * @param paddingRight : Padding given at the end of the graph.
 * @param bgColor : Background of the rectangular mask.
 */
private fun DrawScope.drawUnderScrollMask(columnWidth: Float, paddingRight: Dp, bgColor: Color) {
    drawRect(
        bgColor, Offset(0f, 0f), Size(columnWidth, size.height)
    )
    drawRect(
        bgColor,
        Offset(size.width - paddingRight.toPx(), 0f),
        Size(paddingRight.toPx(), size.height)
    )
}


/**
 * Get cubic points
 *
 * @param pointsData
 * @return
 */
fun getCubicPoints(pointsData: List<Offset>): Pair<MutableList<Offset>, MutableList<Offset>> {
    val cubicPoints1 = mutableListOf<Offset>()
    val cubicPoints2 = mutableListOf<Offset>()

    for (i in 1 until pointsData.size) {
        cubicPoints1.add(
            Offset(
                (pointsData[i].x + pointsData[i - 1].x) / 2, pointsData[i - 1].y
            )
        )
        cubicPoints2.add(
            Offset(
                (pointsData[i].x + pointsData[i - 1].x) / 2, pointsData[i].y
            )
        )
    }
    return Pair(cubicPoints1, cubicPoints2)
}

/**
 * Draw highlight text
 *
 * @param identifiedPoint
 * @param selectedOffset
 * @param selectionHighlightPopUp
 */
fun DrawScope.drawHighlightText(
    identifiedPoint: Point,
    selectedOffset: Offset,
    selectionHighlightPopUp: SelectionHighlightPopUp?
) {
    selectionHighlightPopUp?.run {
        draw(selectedOffset, identifiedPoint)
    }
}

/**
 * Draw high light on selected point
 *
 * @param dragLocks
 * @param columnWidth
 * @param paddingRight
 * @param yBottom
 * @param selectionHighlightPoint
 */
fun DrawScope.drawHighLightOnSelectedPoint(
    dragLocks: MutableMap<Int, Pair<Point, Offset>>,
    columnWidth: Float,
    paddingRight: Dp,
    yBottom: Float,
    selectionHighlightPoint: SelectionHighlightPoint?
) {
    if (selectionHighlightPoint.isNotNull()) {
        dragLocks.values.firstOrNull()?.let { (_, location) ->
            val (x, y) = location
            if (x >= columnWidth && x <= size.width - paddingRight.toPx()) {
                selectionHighlightPoint?.draw?.let { it(this, Offset(x, y)) }
                if (selectionHighlightPoint?.isHighlightLineRequired == true) {
                    selectionHighlightPoint.drawHighlightLine(
                        this, Offset(x, yBottom), Offset(x, y)
                    )
                }
            }
        }
    }
}
