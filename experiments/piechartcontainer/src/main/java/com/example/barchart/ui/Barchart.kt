@file:OptIn(ExperimentalMaterialApi::class)

package com.example.barchart.ui


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.BarInfo
import co.yml.charts.common.extensions.*
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import co.yml.charts.ui.barchart.models.BarData
import com.example.barchart.model.BarChartData
import com.example.barchart.model.BarChartType
import com.example.barchart.model.BarStyle
import com.example.barchart.model.SelectionHighlightData
import com.example.chart.axis.DataCategoryOptions
import com.example.chart.axis.XAxis
import com.example.chart.axis.YAxis
import com.example.chartcontainer.container.ScrollableCanvasContainer
import com.example.common.extensions.*
import com.example.common.extensions.collectIsTalkbackEnabledAsState
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
        val bgColor = MaterialTheme.colors.surface

        val (xMin, xMax) = getXMaxAndMinPoints(points)
        val (_, yMax) = getYMaxAndMinPoints(points)

        val xAxisData =
            xAxisData.copy(axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars,
                steps = chartData.size - 1,
                startDrawPadding = LocalDensity.current.run { columnWidth.toDp() })
        val yAxisData =
            yAxisData.copy(
                axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() },
                startDrawPadding = LocalDensity.current.run { rowHeight.toDp() },
                axisTopPadding = 30.dp
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
                    drawBarGraph(barChartData.barStyle, barData, drawOffset, height, barChartType)

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
                                RowClipṣ(
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
                        chartData = points
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
        val paddingRight = paddingEnd
        val points = chartData.map { it.point }
        val bgColor = MaterialTheme.colors.surface

        val (_, xMax) = getXMaxAndMinPoints(points)
        val (yMin, yMax) = getYMaxAndMinPoints(points)

        val xAxisData = xAxisData.copy(
            dataCategoryOptions = DataCategoryOptions(isDataCategoryInYAxis = true),
            axisEndPadding = 80.dp
        )
        val yAxisData = yAxisData.copy(
            axisStepSize = barStyle.barWidth + barStyle.paddingBetweenBars,
            steps = chartData.size - 1,
            axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() },
            startDrawPadding = LocalDensity.current.run { rowHeight.toDp() })
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
                        accessibilitySheetState.animateTo(
                            ModalBottomSheetValue.Expanded
                        )
                    }
                }
            },
            scrollOrientation = Orientation.Vertical,
            containerBackgroundColor = backgroundColor,
            calculateMaxDistance = { yZoom ->
                horizontalGap = horizontalExtraSpace.toPx()
                val yStart = horizontalGap + barStyle.barWidth.toPx() + barStyle.barWidth.toPx() / 2

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
                val yStart =
                    if (yAxisData.dataCategoryOptions.isDataCategoryStartFromBottom) horizontalGap + barStyle.barWidth.toPx() + barStyle.barWidth.toPx() / 2 else yAxisData.axisTopPadding.toPx()
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
                        yStart,
                        yAxisData.dataCategoryOptions
                    )
                    val width = (barData.point.x) * xOffset

                    // drawing each individual bars
                    drawBarGraph(barChartData.barStyle, barData, drawOffset, width, barChartType)

                    val middleOffset =
                        Offset(drawOffset.x, drawOffset.y + barStyle.barWidth.toPx() / 2)

                    // store the tap points for selection
                    if (isTapped && middleOffset.isYAxisTapped(
                            tapOffset, barStyle.barWidth.toPx(), xLeft, tapPadding.toPx()
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
                        columnWidth,
                        yBottom,
                        paddingTop,
                        yOffset,
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
                        dataValueWidth = xOffset
                    )
                }

                if (showYAxis) {
                    YAxis(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxHeight()
                            .wrapContentWidth()
                            .clip(
                                ColumnClip(
                                    columnWidth,
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
                        if (yAxisData.dataCategoryOptions.isDataCategoryStartFromBottom)
                            horizontalGap + LocalDensity.current.run {
                                (barStyle.barWidth.toPx())
                            }
                        else
                            LocalDensity.current.run {
                                (yAxisData.axisTopPadding.toPx() + barStyle.barWidth.toPx() / 2)
                            })
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

/**
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth: Width of single bar
 * @param highlightData: Data for the highlight section
 * @param selectedXAxisWidth: when data value is present in xAxis [e.g. Horizontal barchart]
 */
private fun DrawScope.drawHighlightText(
    identifiedPoint: BarData,
    selectedOffset: Offset,
    barWidth: Dp,
    highlightData: SelectionHighlightData,
    selectedXAxisWidth: Float = 0f,
    barChartType: BarChartType
) {

    val centerPointOfBar =
        if (barChartType == BarChartType.VERTICAL) selectedOffset.x + barWidth.toPx() / 2 else selectedOffset.y + barWidth.toPx() / 2
    // Drawing the highlighted background and text
    highlightData.drawPopUp(
        this,
        selectedOffset,
        identifiedPoint,
        centerPointOfBar,
        selectedXAxisWidth,
        barChartType
    )
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
    barStyle: BarStyle,
    barData: BarData,
    drawOffset: Offset,
    height: Float,
    barChartType: BarChartType
) = with(barStyle) {
    // Draw bar lines
    if (isGradientEnabled) {
        val brush = Brush.verticalGradient(
            colors = barData.gradientColorList
        )
        //todo sree_ made changes here for horizontal chart
        drawRoundRect(
            brush = brush,
            topLeft = drawOffset,
            size = Size(barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                cornerRadius.toPx(), cornerRadius.toPx()
            ),
            style = barDrawStyle,
            blendMode = barBlendMode
        )
    } else {
        //todo sree_ made changes here for horizontal chart like height and width
        drawRoundRect(
            color = barData.color,
            topLeft = drawOffset,
            size = if (barChartType == BarChartType.VERTICAL) Size(
                barWidth.toPx(),
                height
            ) else Size(height, barWidth.toPx()),
            cornerRadius = CornerRadius(
                cornerRadius.toPx(), cornerRadius.toPx()
            ),
            style = barDrawStyle,
            blendMode = barBlendMode
        )
    }
}

//todo sree_ reusing the function for Y axis update the documentation or param names
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
fun DrawScope.highlightVerticalBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    barHighlightVisibility: Boolean,
    identifiedPoint: BarData,
    barStyle: BarStyle,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
    shouldShowHighlightPopUp: Boolean = true,
    selectedXAxisWidth: Float = 0f,
    barChartType: BarChartType
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
                        this,
                        xPoint,
                        yPoint,
                        barStyle.barWidth.toPx(),
                        (yBottom - y1),
                        barStyle.selectionHighlightData.barChartType
                    )
                }
            }
        }
    }
    if (shouldShowHighlightPopUp) {
        val selectedOffset = dragLocks.values.firstOrNull()?.second
        if (barHighlightVisibility && selectedOffset != null && barStyle.selectionHighlightData != null) {
            drawHighlightText(
                mutableIdentifiedPoint,
                selectedOffset,
                barStyle.barWidth,
                barStyle.selectionHighlightData,
                selectedXAxisWidth,
                barChartType
            )
        }
    }
    return mutableIdentifiedPoint
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
fun DrawScope.highlightHorizontalBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    barHighlightVisibility: Boolean,
    identifiedPoint: BarData,
    barStyle: BarStyle,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
    xStart: Float,
    xOffset: Float,
    shouldShowHighlightPopUp: Boolean = true
): BarData {
    var mutableIdentifiedPoint: BarData = identifiedPoint
    // Handle the show the selected bar
    var selectedXAxisWidth = 0f

    if (isDragging) {
        val y = dragLocks.values.firstOrNull()?.second?.y
        if (y != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        if (barStyle.selectionHighlightData?.isHighlightBarRequired == true) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (yPoint <= yBottom && yPoint >= paddingRight.toPx()) {
                    val x1 = xStart + ((barData.point.x - 0) * xOffset)
                    selectedXAxisWidth =
                        xStart + ((barData.point.x - 0) * xOffset) + barStyle.barWidth.toPx()
                    barStyle.selectionHighlightData?.drawHighlightBar?.invoke(
                        this,
                        xPoint,
                        yPoint,
                        barStyle.barWidth.toPx(),
                        (x1 - xStart),
                        barStyle.selectionHighlightData.barChartType
                    )
                }
            }
        }
    }
    if (shouldShowHighlightPopUp) {
        val selectedOffset = dragLocks.values.firstOrNull()?.second
        if (barHighlightVisibility && selectedOffset != null && barStyle.selectionHighlightData != null) {
            drawHighlightText(
                mutableIdentifiedPoint,
                selectedOffset,
                barStyle.barWidth,
                barStyle.selectionHighlightData ?: SelectionHighlightData(),
                selectedXAxisWidth,
                barStyle.selectionHighlightData.barChartType
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
        bgColor,
        Offset(size.width - paddingRight.toPx(), 0f),
        Size(paddingRight.toPx(), size.height)
    )
}

/**
 *
 * DrawScope.drawUnderXAxisScrollMask extension method used  for drawing a rectangular mask to make graph scrollable under the XAxis.
 * @param columnWidth : Width of the rectangular mask here width of Y Axis is used.
 * @param paddingRight : Padding given at the end of the graph.
 * @param bgColor : Background of the rectangular mask.
 */
fun DrawScope.drawUnderXAxisScrollMask(columnWidth: Float, paddingTop: Dp, bgColor: Color) {
    // Draw column to make graph look scrollable under Xaxis
    drawRect(
        bgColor, Offset(0f, size.height - columnWidth), Size(size.width, columnWidth)
    )
    // Draw top padding
    drawRect(
        bgColor,
        Offset(0f, 0f),
        Size(size.width, paddingTop.toPx())
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

fun getDrawHorizontalOffset(
    point: Point,
    xLeft: Float,
    scrollOffset: Float,
    yBottom: Float,
    yOffset: Float,
    yMin: Float,
    yStart: Float,
    dataCategoryOptions: DataCategoryOptions
): Offset {
    val (x, y) = point
    val x1 = xLeft
    val y1 =
        if (dataCategoryOptions.isDataCategoryStartFromBottom) yBottom - yStart - ((y - yMin) * yOffset) + scrollOffset else yStart + ((y - yMin) * yOffset) - scrollOffset
    return Offset(x1, y1)
}