package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.piechart.charts.PieChart
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {

    /**
     * Wrapper compose method for drawing Pie Chart and Donut chart.
     * @param modifier : All modifier related property
     * @param chartType: Type of the chart (Pie or Donut)
     * @param pieChartData: data list for the pie chart
     */

    @Composable
    fun DrawChart(
        modifier: Modifier,
        chartType: ChartType,
        pieChartData: PieChartData,
        pieChartConfig: PieChartConfig
    ) {
        when (chartType) {
            is ChartType.PieChart -> {
                PieChart(
                    modifier = modifier,
                    pieChartData = pieChartData,
                    pieChartConfig = pieChartConfig
                )
            }
            is ChartType.DonutPieChart -> {
                DonutPieChart(
                    modifier = modifier,
                    pieChartData = pieChartData,
                    pieChartConfig = pieChartConfig
                )
            }
        }
    }
}