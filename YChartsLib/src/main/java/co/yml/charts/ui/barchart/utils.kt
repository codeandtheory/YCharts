package co.yml.charts.ui.barchart

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.GroupBarChartData
import co.yml.charts.ui.barchart.models.SelectionHighlightData

/**
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth: Width of single bar
 * @param highlightData: Data for the highlight section
 * @param selectedXAxisWidth: when data value is present in xAxis [e.g. Horizontal barchart]
 */
internal fun DrawScope.drawHighlightText(
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
internal fun DrawScope.highlightVerticalBar(
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
internal fun DrawScope.highlightHorizontalBar(
    dragLocks: MutableMap<Int, Pair<BarData, Offset>>,
    barHighlightVisibility: Boolean,
    identifiedPoint: BarData,
    barStyle: BarStyle,
    isDragging: Boolean,
    yBottom: Float,
    paddingTop: Dp,
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
                selectedXAxisWidth =
                    xStart + ((barData.point.x - 0) * xOffset) + barStyle.barWidth.toPx()
                if (yPoint + barStyle.barWidth.toPx() <= yBottom && yPoint >= paddingTop.toPx()) {
                    val x1 = xStart + ((barData.point.x - 0) * xOffset)
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
 * DrawScope.drawUnderXAxisScrollMask extension method used  for drawing a rectangular mask to make graph scrollable under the XAxis.
 * @param columnWidth : Width of the rectangular mask here width of Y Axis is used.
 * @param paddingRight : Padding given at the end of the graph.
 * @param bgColor : Background of the rectangular mask.
 */
internal fun DrawScope.drawUnderXAxisScrollMask(columnWidth: Float, paddingTop: Dp, bgColor: Color) {
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
internal fun getDrawOffset(
    point: Point,
    xMin: Float,
    xOffset: Float,
    xLeft: Float,
    scrollOffset: Float,
    yBottom: Float,
    yOffset: Float,
    yMin: Float,
    startDrawPadding: Float,
    zoomScale: Float,
    barWidth: Float
): Offset {
    val (x, y) = point
    val x1 =
        ((x - xMin) * xOffset) + xLeft + (startDrawPadding * zoomScale) - barWidth / 2 - scrollOffset
    val y1 = yBottom - ((y - yMin) * yOffset)
    return Offset(x1, y1)
}


/**
 * Get draw horizontal offset
 *
 * @param point
 * @param xLeft
 * @param scrollOffset
 * @param yBottom
 * @param yOffset
 * @param yMin
 * @param yMax
 * @param yStart
 * @param dataCategoryOptions
 * @param zoomScale
 * @return
 */
internal fun getDrawHorizontalOffset(
    point: Point,
    xLeft: Float,
    scrollOffset: Float,
    yBottom: Float,
    yOffset: Float,
    yMin: Float,
    yMax: Float,
    yStart: Float,
    dataCategoryOptions: DataCategoryOptions,
    zoomScale: Float
): Offset {
    val (_, y) = point
    val x1 = xLeft
    val y1 =
        if (dataCategoryOptions.isDataCategoryStartFromBottom) yBottom - yStart - ((y - yMin) * yOffset) + scrollOffset else {
            if (zoomScale < 1) {
                yBottom - yStart - ((yMax - y) * yOffset) + scrollOffset
            } else {
                yStart + ((y - yMin) * yOffset) - scrollOffset
            }
        }
    return Offset(x1, y1)
}



/////



/**
 *
 * Used to draw the highlighted text
 * @param identifiedPoint : Selected points
 * @param selectedOffset: Offset selected
 * @param barWidth: Width of single bar
 * @param highlightData: Data for the highlight section
 */
internal fun DrawScope.drawGroupHighlightText(
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
internal fun DrawScope.highlightGroupBar(
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
internal fun getGroupBarDrawOffset(
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
internal fun DrawScope.drawGroupSeparator(
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
internal fun getMaxScrollDistance(
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
 * DrawScope.drawUnderScrollMask extension method used  for drawing a rectangular mask to make graph scrollable under the YAxis.
 * @param columnWidth : Width of the rectangular mask here width of Y Axis is used.
 * @param paddingRight : Padding given at the end of the graph.
 * @param bgColor : Background of the rectangular mask.
 */
internal fun DrawScope.drawUnderScrollMask(columnWidth: Float, paddingRight: Dp, bgColor: Color) {
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