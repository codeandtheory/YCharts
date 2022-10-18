package co.yml.charts.charts.barchart.models

import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.PlotData
import co.yml.charts.common.model.PlotType

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
    val barColorPaletteList: List<Color> = listOf(),
    val barStyle: BarStyle = BarStyle(),
) : PlotData {
    companion object {
        fun default() = BarPlotData(groupBarList = listOf())
    }
}
