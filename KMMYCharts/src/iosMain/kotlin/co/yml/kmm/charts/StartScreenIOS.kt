package co.yml.kmm.charts

import androidx.compose.runtime.Composable

@Composable
internal fun StartScreenIOS(chartType: Int) {
    when (chartType) {
        1 -> BarChartScreen()
        2 -> WaveChartScreen()
        3 -> LineChartScreen()
        4 -> PieChartScreen()
        5 -> DonutPieChartScreen()
        6-> BubbleChartWithGrid()
    }
}