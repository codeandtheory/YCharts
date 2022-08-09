package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ygraph.components.bar.piechart.charts.DonutPieChart
import com.ygraph.components.bar.piechart.charts.PieChart
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {

    /**
     * Wrapper compose method for drawing Pie Chart and Donut chart.
     * @param modifier : All modifier related property
     * @param values: Value list for the pie chart
     * @param colors: Colors for the pie chart
     * @param chartType: Type of the chart (Pie or Donut)
     * @param legends: Label list
     * @param isLegendVisible: Legends should show or not
     * @param strokeWidth: Stroke width of Donut Chart
     * @param percentageFontSize: Percentage text font size
     * @param percentVisible: Percentage text visibility
     * @param percentColor: Percentage text color
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