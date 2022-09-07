package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.AxisConfig
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.utils.DataUtils.getColorList

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
 * @param stackLabelList: stackLabelList is used to show labels with colors
 * @param showStackLabel : Boolean Flag to show/hide stack label
 * @param groupSeparatorWidth : width of the groupSeparator
 * @param groupSeparatorColor : color of the groupSeparator
 * @param showGroupSeparator : Boolean Flag to show/hide groupSeparator
 * @param groupSeparatorBlendMode: Blend mode for the groupSeparator
 * @param labelGridColumnCount : Column Count for stackLabel grid
 * @param labelGridPaddingHorizontal : Horizontal padding for stackLabel grid
 * @param labelGridPaddingVertical :  Vertical padding for stackLabel grid
 * @param spaceBWLabelAndColorBox: space between Label and ColorBox for stackLabel grid item
 * @param labelColorBoxSize: Blend mode for the groupSeparator
 * @param labelTextStyle: Label text style for the stackLabel
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
    val stackLabelList: List<StackLabel>,
    val showStackLabel: Boolean = true,
    val labelGridColumnCount: Int = 3,
    val labelGridPaddingHorizontal: Dp = 8.dp,
    val labelGridPaddingVertical: Dp = 8.dp,
    val labelColorBoxSize: Dp = 25.dp,
    val labelTextStyle: TextStyle = TextStyle(),
    val spaceBWLabelAndColorBox: Dp = 4.dp,
    val tapPadding: Dp = 10.dp,
    val groupSeparatorWidth: Dp = 1.dp,
    val groupSeparatorColor: Color = Color.Gray,
    val showGroupSeparator: Boolean = true,
    val groupSeparatorBlendMode: BlendMode = DrawScope.DefaultBlendMode,
)

/**
 * StackLabel data class params used in drawing label in graph.
 * @param color : color of label.
 * @param name : name of label.
 *  */
data class StackLabel(
    val color: Color,
    val name: String,
)
