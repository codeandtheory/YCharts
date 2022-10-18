package co.yml.charts.charts.barchart.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.AccessibilityConfig

/**
 * GroupBarGraph data class params used in drawing bar graph.
 * @param barPlotData : Group bar plot data  .
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param showXAxis: Boolean Flag to enable/disable X axis
 * @param showYAxis: Boolean Flag to enable/disable Y axis
 * @param groupSeparatorConfig : All config related to the GroupSeparator.
 * @param accessibilityConfig: Configs related to accessibility service defined here in [AccessibilityConfig]
 */

data class GroupBarChartData(
    val barPlotData: BarPlotData,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val backgroundColor: Color = Color.White,
    val horizontalExtraSpace: Dp = 10.dp,
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
    val tapPadding: Dp = 10.dp,
    val groupSeparatorConfig: GroupSeparatorConfig = GroupSeparatorConfig(),
    val accessibilityConfig: AccessibilityConfig = AccessibilityConfig()
)
