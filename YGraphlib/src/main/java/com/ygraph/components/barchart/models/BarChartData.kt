package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis

/**
 * BarChart data class params used in drawing bar graph.
 * @param chartData : List of BarData
 * @param axisData: Axis data
 * @param yStepValue: Number of steps needed in Y axis
 * @param xStepValue: Number of steps needed in X axis
 * @param axisData: Axis data
 * @param barWidth: Width of a bar
 * @param paddingBetweenBars: Space between adjacent bars
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param isGradientEnabled: Boolean Flag to enable/disable gradient bars
 */
data class BarChartData(
    val chartData: List<BarData>,
    val axisData: AxisData,
    val yStepValue: Int = 5,
    val xStepValue: Int = chartData.size,
    val barWidth: Dp = 30.dp,
    val cornerRadius: Dp = 4.dp,
    val backgroundColor: Color = Color.White,
    val paddingBetweenBars: Dp = 10.dp,
    val horizontalExtraSpace: Dp = 6.dp,
    val paddingEnd: Dp = 12.dp,
    val paddingTop: Dp = 12.dp,
    val isGradientEnabled: Boolean = false
)
