package co.yml.kmm.charts

import androidx.compose.runtime.Composable

@Composable
fun CommonMainScreen(chartType: Int) {
    when (chartType) {
        1 -> BarChartScreen()
        2 -> ChartScreen()
        3 -> LineChartScreen()
        4 -> PieChartScreen()
        5 -> DonutPieChartScreen()
    }
}