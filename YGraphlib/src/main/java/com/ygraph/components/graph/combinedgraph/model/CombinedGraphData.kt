package com.ygraph.components.graph.combinedgraph.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.graph.bargraph.models.BarStyle
import com.ygraph.components.graph.bargraph.models.GroupBar
import com.ygraph.components.graph.bargraph.models.StackLabelConfig
import com.ygraph.components.graph.linegraph.model.Line

/**
 *
 * CombinedLineAndBarGraphData data class that contains all params user need to define to draw a bar and line graph.
 * @param linePlotData: The path to be drawn on the graph represented by a line.
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param barPlotData: Data related to the drawing of bar graph.
 * @param paddingTop: Padding from the top of the canvas to start of the graph container.
 * @param bottomPadding: Padding from the bottom of the canvas to bottom of the graph container.
 * @param containerPaddingEnd: Container inside padding end after the last point of the graph.
 * @param backgroundColor: Background color of the Y & X components.
 */
data class CombinedGraphData(
    val linePlotData: List<Line>,
    val barPlotData: BarPlotData,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val paddingTop: Dp = 30.dp,
    val bottomPadding: Dp = 10.dp,
    val paddingEnd: Dp = 10.dp,
    val horizontalExtraSpace: Dp = 10.dp,
    val containerPaddingEnd: Dp = 15.dp,
    val backgroundColor: Color = Color.White,
    val tapPadding: Dp = 10.dp
)

/**
 * BarPlotData is a data class that holds bar graph related data and styling components
 * @param groupBarList : Data related to the bar point.
 * @param barStyle : Styling related to the bars.
 */
data class BarPlotData(
    val groupBarList: List<GroupBar>,
    val groupingSize: Int = groupBarList.firstOrNull()?.barList?.size ?: 1,
    val barStyle: BarStyle = BarStyle(),
    val stackLabelConfig: StackLabelConfig
)
