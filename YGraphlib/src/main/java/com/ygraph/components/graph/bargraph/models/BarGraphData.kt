package com.ygraph.components.graph.bargraph.models

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.constants.GraphConstants


/**
 * BarGraph data class params used in drawing bar graph.
 * @param graphData : List of BarData
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param tapPadding: Extra padding area for tapping
 * @param showXAxis: Boolean Flag to enable/disable X axis
 * @param showYAxis: Boolean Flag to enable/disable Y axis
 * @param shouldHandleBackWhenTalkBackPopUpShown: True by default to dismiss the accessibility dialog when back pressed else false
 */
data class BarGraphData(
    val graphData: List<BarData>,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val backgroundColor: Color = Color.White,
    val horizontalExtraSpace: Dp = 0.dp,
    val barStyle: BarStyle = BarStyle(),
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val tapPadding: Dp = 10.dp,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
    val shouldHandleBackWhenTalkBackPopUpShown: Boolean = true,
    val graphDescription: String = GraphConstants.GRAPH_DESCRIPTION
)

/**
 * BarStyle data class adds styling related to each bar in the bar graph
 * @param barWidth: Width of a bar
 * @param cornerRadius: Corner radius for the bars
 * @param paddingBetweenBars: Space between adjacent bars
 * @param isGradientEnabled: Boolean Flag to enable/disable gradient bars
 * @param barBlendMode: Blend mode for the bars
 * @param barDrawStyle: Draw style for the bars
 */
data class BarStyle(
    val barWidth: Dp = 30.dp,
    val cornerRadius: Dp = 4.dp,
    val paddingBetweenBars: Dp = 15.dp,
    val isGradientEnabled: Boolean = false,
    val barBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val barDrawStyle: DrawStyle = Fill,
    val selectionHighlightData: SelectionHighlightData? = SelectionHighlightData()
)
