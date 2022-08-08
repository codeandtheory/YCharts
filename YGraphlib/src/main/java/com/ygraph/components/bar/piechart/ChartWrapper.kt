package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.bar.piechart.charts.PieChart
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {

    /**
     * Wrapper compose method for drawing Pie Chart and Donut chart.
     * All modifier related property [modifier].
     * Value list for the Pie/Donut chart [values].
     * Colors for the pie/Donut chart [color].
     * Type of the chart (Pie or Donut) [chartType].
     * Label list  [legends].
     * Legends visibility  [isLegendVisible].
     * Stroke width of Donut Chart [strokeWidth].
     * Percentage text font size  [percentageFontSize].
     * Percentage text visibility [percentVisible].
     * Percentage text color [percentColor].
     */

    @Composable
    fun DrawChart(
        modifier: Modifier,
        values: List<Float>,
        color: List<Color>,
        chartType: ChartType,
        legends: List<String> = emptyList(),
        isLegendVisible: Boolean = legends.isNotEmpty(),
        strokeWidth: Float = 100f,
        percentageFontSize: TextUnit = 60.sp,
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