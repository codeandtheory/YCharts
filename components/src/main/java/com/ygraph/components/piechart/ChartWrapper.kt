package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.piechart.charts.PieChart
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {
    @Composable
    fun DrawChart(
        values: List<Float> = listOf(15f, 35f, 50f),
        color: List<Color> = listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40)),
        chartType: ChartType,
        isLegendVisible : Boolean = false,
        sliceThickness: Float = 20f,
    ) {
        when (chartType) {
            is ChartType.PieChart -> {
                PieChart(values = values , isLegendVisible= isLegendVisible, colors = color)
            }
            is ChartType.DonutPieChart -> {
                DonutPieChart(values = values, sliceThickness = sliceThickness, isLegendVisible= isLegendVisible,colors = color)
            }
        }
    }
}