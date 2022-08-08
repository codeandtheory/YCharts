package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.bar.piechart.charts.PieChart
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {
    @Composable
    fun DrawChart(
        modifier: Modifier,
        values: List<Float>,
        color: List<Color>,
        chartType: ChartType,
        legends: List<String> = emptyList(),
        isLegendVisible: Boolean = legends.isNotEmpty(),
        strokeWidth: Float = 100f,
        percentageFontSize: Dp = 60.dp,
        percentVisible: Boolean = false,
        percentColor: Color = Color.White
    ) {
        when (chartType) {
            is ChartType.PieChart -> {
                PieChart(
                    modifier = modifier,
                    values = values,
                    isLegendVisible = isLegendVisible,
                    colors = color,
                    legends = legends
                )
            }
            is ChartType.DonutPieChart -> {
                DonutPieChart(
                    modifier = modifier,
                    values = values,
                    colors = color,
                    legends =legends,
                    strokeWidth = strokeWidth,
                    isLegendVisible = isLegendVisible,
                    percentVisible = percentVisible,
                    percentColor = percentColor,
                    percentageFontSize = percentageFontSize
                )
            }
        }
    }
}