package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.piechart.charts.PieChart
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {
    @Composable
    fun DrawChart(
        values: List<Float> = listOf(15f, 35f, 50f),
        chartType: ChartType,
        isLegendVisible : Boolean = false,
        sliceThickness: Float = 20f,
    ) {
        when (chartType) {
            is ChartType.PieChart -> {
                PieChart(values = values , isLegendVisible= isLegendVisible)
            }
            is ChartType.DonutPieChart -> {
                DonutPieChart(values = values, sliceThickness = sliceThickness, isLegendVisible= isLegendVisible)
            }
        }
    }
}