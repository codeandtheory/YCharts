@file:OptIn(ExperimentalMaterialApi::class)

package co.yml.charts.ui.barchart


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import co.yml.charts.chartcontainer.container.ScrollableCanvasContainer
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.GroupBarInfo
import co.yml.charts.common.extensions.RowClip
import co.yml.charts.common.extensions.collectIsTalkbackEnabledAsState
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.extensions.isTapped
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.ChartConstants.DEFAULT_YAXIS_BOTTOM_PADDING
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.GroupBarChartData
import co.yml.charts.ui.barchart.models.SelectionHighlightData
import kotlinx.coroutines.launch


/**
 *
 * [GroupBarChart] compose method for drawing group bar chart.
 * @param modifier: All modifier related properties
 * @param groupBarChartData : All data needed to group bar chart
 * @see [GroupBarChartData] Data class to save all params related to Bar Chart
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupBarChart(modifier: Modifier, groupBarChartData: GroupBarChartData) {
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
            val valueList = groupBarList.map { it.yMax }
            val bgColor = MaterialTheme.colorScheme.surface

            val xMax = groupBarList.size
            val yMax = valueList.maxOrNull() ?: 0f
            val xAxisData =
                groupBarChartData.xAxisData.copy(
                    axisStepSize = ((barStyle.barWidth * groupingSize) + barStyle.paddingBetweenBars),
                    shouldDrawAxisLineTillEnd = true,
                    steps = groupBarList.size - 1
                )
            val yAxisData =
                groupBarChartData.yAxisData.copy(axisBottomPadding = LocalDensity.current.run { rowHeight.toDp() })

            val maxElementInYAxis = getMaxElementInYAxis(yMax, yAxisData.steps)

            if (!groupBarChartData.showXAxis) {
                rowHeight = LocalDensity.current.run { DEFAULT_YAXIS_BOTTOM_PADDING.dp.toPx() }
            }
            ScrollableCanvasContainer(modifier = modifier
                .semantics {
                    contentDescription = groupBarChartData.accessibilityConfig.chartDescription
                }
                .clickable {
                    if (isTalkBackEnabled) {
                        scope.launch {
                            accessibilitySheetState.show()
                        }
                    }
                },
                containerBackgroundColor = groupBarChartData.backgroundColor,
                calculateMaxDistance = { xZoom ->
                    horizontalGap = groupBarChartData.horizontalExtraSpace.toPx()
                    val xLeft = columnWidth + horizontalGap
                    xOffset =
                        ((barStyle.barWidth.toPx() * groupingSize) + barStyle.paddingBetweenBars.toPx()) * xZoom
                    getMaxScrollDistance(
                        columnWidth,
                        xMax.toFloat(),
                        0f,
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
                        ((barStyle.barWidth.toPx() * groupingSize) + barStyle.paddingBetweenBars.toPx()) * xZoom
                    val xLeft = columnWidth + horizontalGap
                    val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                    // Draw bar lines
                    groupBarList.forEachIndexed { index, groupBarData ->
                        var insideOffset = 0f

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

                            val individualOffset = Offset(drawOffset.x + insideOffset, drawOffset.y)

                            // drawing each individual bars
                            groupBarChartData.drawBar(
                                this,
                                groupBarChartData,
                                barStyle,
                                individualOffset,
                                height,
                                subIndex
                            )

                            insideOffset += barStyle.barWidth.toPx()

                            val middleOffset = Offset(
                                individualOffset.x + barStyle.barWidth.toPx() / 2, drawOffset.y
                            )
                            // store the tap points for selection
                            if (isTapped && middleOffset.isTapped(
                                    tapOffset,
                                    barStyle.barWidth.toPx(),
                                    yBottom,
                                    groupBarChartData.tapPadding.toPx()
                                )
                            ) {
                                dragLocks[0] = individualBar to individualOffset
                            }
                        }

                        if (groupBarChartData.groupSeparatorConfig.showSeparator && index != groupBarList.size - 1) {
                            // drawing each Group Separator bars
                            val yOffset2 = (yBottom - yAxisData.axisTopPadding.toPx())
                            val height = yBottom - yAxisData.axisTopPadding.toPx()
                            val drawOffset2 = getGroupBarDrawOffset(
                                x = index,
                                y = rowHeight,
                                xOffset = xOffset,
                                xLeft = xLeft,
                                scrollOffset = scrollOffset,
                                yBottom = yBottom,
                                yOffset = yOffset2,
                                yMin = 0f,
                                xMin = 0f,
                                startDrawPadding = xAxisData.startDrawPadding.toPx(),
                                zoomScale = xZoom,
                                barStyle.barWidth.toPx()
                            )
                            val xOffset2 =
                                (drawOffset2.x + insideOffset + (barStyle.paddingBetweenBars.toPx() / 2) - groupBarChartData.groupSeparatorConfig.separatorWidth.toPx() / 2)
                            val individualOffset = Offset(xOffset2, yAxisData.axisTopPadding.toPx())

                            drawGroupSeparator(
                                individualOffset,
                                height,
                                groupBarChartData.groupSeparatorConfig.separatorWidth.toPx(),
                                groupBarChartData.groupSeparatorConfig.separatorColor,
                                groupBarChartData
                            )
                        }
                    }
                    drawUnderScrollMask(columnWidth, paddingRight, bgColor)

                    if (barStyle.selectionHighlightData != null) {
                        // highlighting the selected bar and showing the data points
                        identifiedPoint = highlightGroupBar(
                            dragLocks,
                            visibility,
                            identifiedPoint,
                            barStyle.selectionHighlightData,
                            isTapped,
                            columnWidth,
                            yBottom,
                            paddingRight,
                            yOffset,
                            barStyle.barWidth
                        )
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
                            xStart = columnWidth + horizontalGap + LocalDensity.current.run {
                                (barStyle.barWidth.toPx() * groupingSize) / 2
                            },
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
                })
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
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth: Width of single bar
 * @param highlightData: Data for the highlight section
 */
private fun DrawScope.drawGroupHighlightText(
    identifiedPoint: BarData,
    selectedOffset: Offset,
    barWidth: Dp,
    highlightData: SelectionHighlightData
) {
    val centerPointOfBar = selectedOffset.x + barWidth.toPx() / 2
    // Drawing the highlighted background and text
    highlightData.drawGroupBarPopUp(this, selectedOffset, identifiedPoint, centerPointOfBar)
}

/**
 *
 * returns identified point and displaying the data points and highlighted bar .
 * @param dragLocks : Mutable map of BarData and drawOffset.
 * @param visibility : Flag to control the visibility of highlighted text.
 * @param identifiedPoint: selected bar data.
 * @param selectionHighlightData: Data related to the bar graph highlight.
 * @param isDragging : Boolean flag for the dragging status.
 * @param columnWidth : Width of the Y axis.
 * @param yBottom : Bottom padding.
 * @param paddingRight : Right padding.
 * @param yOffset : Distance between two y points.
 * @param barWidth : Width of each bar.
 * @param totalPaddingBtwBars : total padding between stacked bars. For group chart it will be 0.
 * @param isHighlightFullBar : User configured value for highlighting the entire bar in case of stacked bar chart
 */
fun DrawScope.highlightGroupBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    visibility: Boolean,
    identifiedPoint: BarData,
    selectionHighlightData: SelectionHighlightData?,
    isDragging: Boolean,
    columnWidth: Float,
    yBottom: Float,
    paddingRight: Dp,
    yOffset: Float,
    barWidth: Dp,
    totalPaddingBtwBars: Float = 0f,
    isHighlightFullBar: Boolean = false
): BarData {
    var mutableIdentifiedPoint: BarData = identifiedPoint
    // Handle the show the selected bar
    if (isDragging) {
        val x = dragLocks.values.firstOrNull()?.second?.x
        if (x != null) {
            mutableIdentifiedPoint = dragLocks.values.map { it.first }.first()
        }

        // Draw highlight bar on selection
        if (selectionHighlightData?.isHighlightBarRequired == true) {
            dragLocks.values.firstOrNull()?.let { (barData, location) ->
                val (xPoint, yPoint) = location
                if (xPoint >= columnWidth && xPoint <= size.width - paddingRight.toPx()) {
                    val y1 =
                        yBottom - ((barData.point.y - 0) * yOffset) - if (isHighlightFullBar) totalPaddingBtwBars else 0f
                    selectionHighlightData.drawHighlightBar(
                        this, xPoint, yPoint, barWidth.toPx(), yBottom - y1, BarChartType.VERTICAL
                    )
                }
            }
        }
    }

    val selectedOffset = dragLocks.values.firstOrNull()?.second
    if (visibility && selectedOffset != null && selectionHighlightData != null) {
        drawGroupHighlightText(
            mutableIdentifiedPoint, selectedOffset, barWidth, selectionHighlightData
        )
    }
    return mutableIdentifiedPoint
}

/**
 * returns the draw offset for bar graph.
 * @param yMin: Minimum value on the y axis
 * @param xOffset: Distance between bars
 * @param yOffset: Distance between y axis points
 * @param xLeft: X starting point of bar graph
 * @param scrollOffset: Scroll offset
 * @param yBottom: Y starting point of bar graph
 */
fun getGroupBarDrawOffset(
    x: Int,
    y: Float,
    xOffset: Float,
    xLeft: Float,
    scrollOffset: Float,
    yBottom: Float,
    yOffset: Float,
    yMin: Float,
    xMin: Float,
    startDrawPadding: Float,
    zoomScale: Float,
    barWidth: Float
): Offset {
    val x1 =
        ((x - xMin) * xOffset) + xLeft + (startDrawPadding * zoomScale) - barWidth / 2 - scrollOffset

    val y1 = yBottom - ((y - yMin) * yOffset)
    return Offset(x1, y1)
}

/**
 *
 * Used to draw the group separator
 * @param barGraphData: [GroupBarChartData]
 * @param drawOffset: topLeft offset for the drawing the separator
 * @param height : height of the separator
 * @param width : width of the separator
 * @param color : color of the separator
 */
private fun DrawScope.drawGroupSeparator(
    drawOffset: Offset,
    height: Float,
    width: Float,
    color: Color,
    barGraphData: GroupBarChartData,
) {
    drawRoundRect(
        color = color,
        topLeft = drawOffset,
        size = Size(width, height),
        blendMode = barGraphData.groupSeparatorConfig.separatorBlendMode
    )
}
