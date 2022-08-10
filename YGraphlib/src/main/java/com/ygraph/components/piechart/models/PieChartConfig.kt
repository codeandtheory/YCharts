package com.ygraph.components.piechart.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ygraph.components.piechart.PieChartConstants.DEFAULT_SLICE_LABEL_TEXT_SIZE
import com.ygraph.components.piechart.PieChartConstants.DEFAULT_START_ANGLE

/**
 * PieChartConfig data class used to mention all config related param required to draw PieChart.
 * @param startAngle: Starting angle
 * @param showSliceLabels: Control the labels visibility
 * @param sliceLabelTextSize: Text size of the labels
 * @param sliceLabelTextColor: Text color of the labels
 * @param isLegendVisible: visibility of legends
 * @param legendLabelTextColor: Text color of the legend labels
 * @param animationDuration: Duration of animation
 * @param strokeWidth: Stroke width of Donut Chart
 * @param percentageFontSize: Percentage text font size
 * @param percentVisible: Percentage text visibility
 * @param percentColor: Percentage text color
 */
data class PieChartConfig(
    val startAngle: Float = DEFAULT_START_ANGLE,
    val showSliceLabels: Boolean = true,
    val sliceLabelTextSize: TextUnit = DEFAULT_SLICE_LABEL_TEXT_SIZE.sp,
    val sliceLabelTextColor: Color = Color.White,
    val isLegendVisible: Boolean = false,
    val legendLabelTextColor: Color = Color.Black,
    val animationDuration: Int = 500,
    val strokeWidth: Float = 100f,
    val percentageFontSize: TextUnit = 60.sp,
    val percentVisible: Boolean = false,
    val percentColor: Color = Color.White)
