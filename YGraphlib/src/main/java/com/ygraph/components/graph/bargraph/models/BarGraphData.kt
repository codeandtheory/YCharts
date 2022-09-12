package com.ygraph.components.graph.bargraph.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import com.ygraph.components.axis.AxisData


/**
 * BarGraph data class params used in drawing bar graph.
 * @param graphData : List of BarData
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param barWidth: Width of a bar
 * @param cornerRadius: Corner radius for the bars
 * @param paddingBetweenBars: Space between adjacent bars
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param isGradientEnabled: Boolean Flag to enable/disable gradient bars
 * @param barBlendMode: Blend mode for the bars
 * @param barDrawStyle: Draw style for the bars
 * @param tapPadding: Extra padding area for tapping
 * @param showXAxis: Boolean Flag to enable/disable X axis
 * @param showYAxis: Boolean Flag to enable/disable Y axis
 */
data class BarGraphData(
    val graphData: List<BarData>,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val barWidth: Dp = 30.dp,
    val cornerRadius: Dp = 4.dp,
    val backgroundColor: Color = Color.White,
    val paddingBetweenBars: Dp = 15.dp,
    val horizontalExtraSpace: Dp = 0.dp,
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val selectionHighlightData: SelectionHighlightData? = SelectionHighlightData(),
    val isGradientEnabled: Boolean = false,
    val barBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val barDrawStyle: DrawStyle = Fill,
    val tapPadding: Dp = 10.dp,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
)
