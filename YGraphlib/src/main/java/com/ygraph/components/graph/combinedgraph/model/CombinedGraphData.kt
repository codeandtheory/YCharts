package com.ygraph.components.graph.combinedgraph.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.model.PlotData
import com.ygraph.components.common.model.PlotType
import com.ygraph.components.graph.bargraph.models.BarStyle
import com.ygraph.components.graph.bargraph.models.GroupBar
import com.ygraph.components.graph.bargraph.models.LegendsConfig
import com.ygraph.components.graph.linegraph.model.Line

/**
 *
 * CombinedLineAndBarGraphData data class that contains all params user need to define to draw a bar and line graph.
 * @param combinedPlotDataList: Defines list of plot data's to be drawn and order of graph drawing is maintained as
 * per the list order. Distinct plot data's are only allowed.
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param paddingTop: Padding from the top of the canvas to start of the graph container.
 * @param bottomPadding: Padding from the bottom of the canvas to bottom of the graph container.
 * @param containerPaddingEnd: Container inside padding end after the last point of the graph.
 * @param backgroundColor: Background color of the Y & X components.,
 * @param isZoomAllowed: True if zoom in for all vertical graph components is allowed else false.
 */
data class CombinedGraphData(
    val combinedPlotDataList: List<PlotData>,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val paddingTop: Dp = 30.dp,
    val bottomPadding: Dp = 10.dp,
    val paddingEnd: Dp = 10.dp,
    val horizontalExtraSpace: Dp = 10.dp,
    val containerPaddingEnd: Dp = 15.dp,
    val backgroundColor: Color = Color.White,
    val tapPadding: Dp = 10.dp,
    val isZoomAllowed: Boolean = true
)

/**
 * BarPlotData is a data class that holds bar graph related data and styling components
 * @param plotType : Defines the type of plot/graph
 * @param groupBarList : Data related to the bar point.
 * @param barStyle : Styling related to the bars.
 */
data class BarPlotData(
    override val plotType: PlotType = PlotType.Bar,
    val groupBarList: List<GroupBar>,
    val groupingSize: Int = groupBarList.firstOrNull()?.barList?.size ?: 1,
    val barStyle: BarStyle = BarStyle(),
    val legendsConfig: LegendsConfig
) : PlotData {
    companion object {
        fun default() =
            BarPlotData(groupBarList = listOf(), legendsConfig = LegendsConfig(listOf()))
    }
}

/**
 * LinePlotData is a data class that holds line graph related data and styling components
 * @param plotType : Defines the type of plot/graph
 * @param lines : Data related to the list of lines to be drawn.
 */
data class LinePlotData(
    override val plotType: PlotType = PlotType.Line,
    val lines: List<Line>
) : PlotData
