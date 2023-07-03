@file:OptIn(ExperimentalMaterialApi::class)

package co.yml.kmm.charts.ui.barchart


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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.kmm.charts.ui.barchart.models.BarChartData
import co.yml.kmm.charts.ui.barchart.models.BarData
import co.yml.kmm.charts.ui.barchart.models.BarStyle
import co.yml.kmm.charts.ui.barchart.models.SelectionHighlightData
import co.yml.kmm.charts.common.components.ItemDivider
import co.yml.kmm.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.kmm.charts.common.components.accessibility.BarInfo
import co.yml.kmm.charts.common.extensions.RowClip
import co.yml.kmm.charts.common.extensions.getMaxElementInYAxis
import co.yml.kmm.charts.common.extensions.getXMaxAndMinPoints
import co.yml.kmm.charts.common.extensions.getYMaxAndMinPoints
import co.yml.kmm.charts.common.extensions.isTapped
import co.yml.kmm.charts.common.model.Point
import co.yml.kmm.charts.common.utils.ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import co.yml.kmm.charts.axis.XAxis
import co.yml.kmm.charts.axis.YAxis
import co.yml.kmm.charts.chartcontainer.container.ScrollableCanvasContainer
import kotlinx.coroutines.launch


/**
 *
 * [BarChart] compose method for drawing bar chart.
 * @param modifier: All modifier related properties
 * @param barChartData : All data needed to Bar Chart
 * @see [BarChartData] Data class to save all params related to Bar Chart
 */
@OptIn(ExperimentalMaterialApi::class, ExperimentalTextApi::class)
@Composable
internal fun BarChart(modifier: Modifier, barChartData: BarChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled = false

    // todo find a better solution for back handling
//    if (accessibilitySheetState.isVisible && isTalkBackEnabled
//        && barChartData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
//    ) {
//        BackHandler {
//            scope.launch {
//                accessibilitySheetState.hide()
//            }
//        }
//    }

    Surface(modifier) {
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
            val bgColor = MaterialTheme.colors.surface
            val textMeasurer = rememberTextMeasurer()

            val (xMin, xMax) = getXMaxAndMinPoints(points)
            val (_, yMax) = getYMaxAndMinPoints(points)

            val xAxisData = xAxisData.copy(axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars,
                steps = chartData.size - 1,
                startDrawPadding = LocalDensity.current.run { columnWidth.toDp() })
            val yAxisData = yAxisData.copy(axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() })
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
                            accessibilitySheetState.animateTo(
                                ModalBottomSheetValue.Expanded
                            )
                        }
                    }
                },
                containerBackgroundColor = backgroundColor,
                calculateMaxDistance = { xZoom ->
                    horizontalGap = horizontalExtraSpace.toPx()
                    val xLeft = columnWidth + horizontalGap
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
                    val xLeft = columnWidth + horizontalGap + barStyle.barWidth.toPx() / 2
                    val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                    // Draw bar lines
                    chartData.forEachIndexed { _, barData ->
                        val drawOffset = getDrawOffset(
                            barData.point, xMin, xOffset, xLeft, scrollOffset, yBottom, yOffset, 0f
                        )
                        val height = yBottom - drawOffset.y

                        // drawing each individual bars
                        drawBarGraph(barChartData.barStyle, barData, drawOffset, height)

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
                    identifiedPoint = highlightBar(
                        dragLocks,
                        barHighlightVisibility,
                        identifiedPoint,
                        barChartData.barStyle,
                        isTapped,
                        columnWidth,
                        yBottom,
                        paddingRight,
                        yOffset,
                        textMeasurer
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
                            xStart = columnWidth + horizontalGap + LocalDensity.current.run {
                                (barStyle.barWidth.toPx())
                            },
                            scrollOffset = scrollOffset,
                            zoomScale = xZoom,
                            chartData = points,
                            0f
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
                            }, yAxisData = yAxisData
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
                                        xAxisData.axisLabelDescription(
                                            xAxisData.labelData(
                                                index
                                            )
                                        ),
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

/**
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth: Width of single bar
 * @param highlightData: Data for the highlight section
 */
@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawHighlightText(
    identifiedPoint: BarData, selectedOffset: Offset, barWidth: Dp, highlightData: SelectionHighlightData, textMeasurer: TextMeasurer
) {
    val centerPointOfBar = selectedOffset.x - (barWidth.toPx() / 2)
    // Drawing the highlighted background and text
    highlightData.drawPopUp(this, selectedOffset, identifiedPoint, centerPointOfBar, textMeasurer)
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
    barStyle: BarStyle, barData: BarData, drawOffset: Offset, height: Float
) = with(barStyle) {
    // Draw bar lines
    if (isGradientEnabled) {
        val brush = Brush.verticalGradient(
            colors = barData.gradientColorList
        )
        drawRoundRect(
            brush = brush, topLeft = drawOffset, size = Size(barWidth.toPx(), height), cornerRadius = CornerRadius(
                cornerRadius.toPx(), cornerRadius.toPx()
            ), style = barDrawStyle, blendMode = barBlendMode
        )
    } else {
        drawRoundRect(
            color = barData.color,
            topLeft = drawOffset,
            size = Size(barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                cornerRadius.toPx(), cornerRadius.toPx()
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
    val xLastPoint = (xMax - xMin) * xOffset + xLeft + columnWidth + paddingRight
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
 * @param barStyle: Data related to the bar graph styling.
 * @param isDragging : Boolean flag for the dragging status.
 * @param columnWidth : Width of the Y axis.
 * @param yBottom : Bottom padding.
 * @param paddingRight : Right padding.
 * @param yOffset : Distance between two y points.
 * @param shouldShowHighlightPopUp : Default true but if highlight popup not required make it false
 */
@OptIn(ExperimentalTextApi::class)
fun DrawScope.highlightBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    barHighlightVisibility: Boolean,
    identifiedPoint: BarData,
    barStyle: BarStyle,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
    textMeasurer: TextMeasurer,
    shouldShowHighlightPopUp: Boolean = true
): BarData {
    var mutableIdentifiedPoint: BarData = identifiedPoint
    // Handle the show the selected bar
    if (isDragging) {
        val x = dragLocks.values.firstOrNull()?.second?.x
        if (x != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        // Draw highlight bar on selection
        if (barStyle.selectionHighlightData?.isHighlightBarRequired == true) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                    val y1 = yBottom - ((barData.point.y - 0) * yOffset)
                    barStyle.selectionHighlightData.drawHighlightBar(
                        this, xPoint, yPoint, barStyle.barWidth.toPx(), (yBottom - y1)
                    )
                }
            }
        }
    }
    if (shouldShowHighlightPopUp) {
        val selectedOffset = dragLocks.values.firstOrNull()?.second
        if (barHighlightVisibility && selectedOffset != null && barStyle.selectionHighlightData != null) {
            drawHighlightText(
                mutableIdentifiedPoint, selectedOffset, barStyle.barWidth, barStyle.selectionHighlightData,  textMeasurer
            )
        }
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
        bgColor, Offset(0f, 0f), Size(columnWidth, size.height)
    )
    // Draw right padding
    drawRect(
        bgColor, Offset(size.width - paddingRight.toPx(), 0f), Size(paddingRight.toPx(), size.height)
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

