package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Typeface
import com.ygraph.components.axis.AxisConfig


/**
 * BarChart data class params used in drawing bar graph.
 * @param chartData : List of BarData
 * @param yStepSize: Number of steps needed in Y axis
 * @param yLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * @param yLabelAndAxisLinePadding: Text label padding from y Axis
 * @param yAxisOffset: Drawing offset for yAxis.
 * @param xStepSize: Number of steps needed in X axis
 * @param xStepSize: Number of steps needed in X axis
 * @param xLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * @param axisLabelFontSize: Font size of axis lablel data
 * @param barWidth: Width of a bar
 * @param cornerRadius: Corner radius for the bars
 * @param paddingBetweenBars: Space between adjacent bars
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param isGradientEnabled: Boolean Flag to enable/disable gradient bars
 * @param showXAxis: Boolean Flag to enable/disable X axis
 * @param showYAxis: Boolean Flag to enable/disable Y axis
 * @param axisConfig: All config related param to toggle the elements while drawing graph
 */
data class BarChartData(
    val chartData: List<BarData>,
    val yStepSize: Int = 5,
    val yLabelData: (Int) -> String = { _ -> "" },
    val yLabelAndAxisLinePadding: Dp = 4.dp,
    val yAxisOffset: Dp = 10.dp,
    val yTopPadding: Dp = 40.dp,
    val xStepSize: Int = chartData.size,
    val xLabelData: (Int) -> String = { _ -> "" },
    val axisLabelFontSize: TextUnit = 14.sp,
    val barWidth: Dp = 30.dp,
    val cornerRadius: Dp = 4.dp,
    val backgroundColor: Color = Color.White,
    val paddingBetweenBars: Dp = 15.dp,
    val horizontalExtraSpace: Dp = 0.dp,
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val selectionHighlightData: SelectionHighlightData? = SelectionHighlightData(),
    val isGradientEnabled: Boolean = false,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
    val axisConfig: AxisConfig = AxisConfig()
)
