package com.ygraph.components.graph.bargraph.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.constants.GraphConstants

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
 * @param shouldHandleBackWhenTalkBackPopUpShown: True by default to dismiss the accessibility dialog when back pressed else false
 * @param graphDescription: Description to describe the graph details for accessibility service.
 */

data class GroupBarGraphData(
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
    val shouldHandleBackWhenTalkBackPopUpShown: Boolean = true,
    val graphDescription: String = GraphConstants.GRAPH_DESCRIPTION
)
