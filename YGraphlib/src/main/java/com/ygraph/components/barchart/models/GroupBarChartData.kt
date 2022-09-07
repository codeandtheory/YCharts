package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisConfig
import com.ygraph.components.axis.AxisData

/**
 * GroupBarChart data class params used in drawing bar graph.
 * @param groupedBarList : List of grouped gar data.
 * @param axisData : All config related to the Axis.
 * @param groupingSize : The number of bars in one set.
 * @param xLabelAngle: Angle of the x axis labels
 * @param barWidth: Width of a bar
 * @param cornerRadius: Corner radius for the bars
 * @param paddingBetweenBars: Space between adjacent bars
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param isGradientEnabled: Boolean Flag to enable/disable gradient bars
 * @param barBlendMode: Blend mode for the bars
 * @param barDrawStyle: Draw style for the bars
 * @param showXAxis: Boolean Flag to enable/disable X axis
 * @param showYAxis: Boolean Flag to enable/disable Y axis
 * @param axisConfig: All config related param to toggle the elements while drawing graph
 * @param stackLabelConfig : All config related to the StackLabel.
 * @param groupSeparatorConfig : All config related to the GroupSeparator.
 */

data class GroupBarChartData(
    val groupedBarList: List<GroupBar>,
    val axisData: AxisData,
    val groupingSize: Int = groupedBarList.firstOrNull()?.barList?.size ?: 1,
    val xLabelAngle: Float = 0f,
    val barWidth: Dp = 30.dp,
    val cornerRadius: Dp = 4.dp,
    val backgroundColor: Color = Color.White,
    val paddingBetweenBars: Dp = 15.dp,
    val horizontalExtraSpace: Dp = 10.dp,
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val selectionHighlightData: SelectionHighlightData? = SelectionHighlightData(),
    val isGradientEnabled: Boolean = false,
    val barBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val barDrawStyle: DrawStyle = Fill,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
    val axisConfig: AxisConfig = AxisConfig(),
    val tapPadding: Dp = 10.dp,
    val stackLabelConfig: StackLabelConfig,
    val groupSeparatorConfig: GroupSeparatorConfig = GroupSeparatorConfig(),
)



