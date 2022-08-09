package com.ygraph.components.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ygraph.components.bar.piechart.charts.DonutPieChart
import com.ygraph.components.bar.piechart.charts.PieChart
import com.ygraph.components.bar.piechart.models.PieChartData
import com.ygraph.components.piechart.utils.ChartType

object ChartWrapper {

    /**
     * Wrapper compose method for drawing Pie Chart and Donut chart.
     * @param modifier : All modifier related property
     * @param chartType: Type of the chart (Pie or Donut)
     * @param pieChartData: data list for the pie chart
     * @param isLegendVisible: Legends should show or not
     * @param strokeWidth: Stroke width of Donut Chart
     * @param percentageFontSize: Percentage text font size
     * @param percentVisible: Percentage text visibility
     * @param percentColor: Percentage text color
     */

    @Composable
    fun DrawChart(
        modifier: Modifier,
        chartType: ChartType,
        pieChartData: PieChartData,
        isLegendVisible: Boolean = pieChartData.legendVisible,
        strokeWidth: Float = 100f,
        percentageFontSize: TextUnit = 60.sp,
        percentVisible: Boolean = false,
        percentColor: Color = Color.White,
        animationDuration: Int = 1000
    ) {
        when (chartType) {
            is ChartType.PieChart -> {
                PieChart(
                    modifier = modifier,
                    pieChartData = pieChartData,
                    isLegendVisible = isLegendVisible,
                    animationDuration = animationDuration
                )
            }
            is ChartType.DonutPieChart -> {
                DonutPieChart(
                    modifier = modifier,
                    pieChartData = pieChartData,
                    strokeWidth = strokeWidth,
                    isLegendVisible = isLegendVisible,
                    percentVisible = percentVisible,
                    percentColor = percentColor,
                    percentageFontSize = percentageFontSize,
                    animationDuration = animationDuration

                )
            }
        }
    }
}