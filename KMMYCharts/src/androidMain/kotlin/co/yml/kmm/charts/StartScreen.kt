package co.yml.kmm.charts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
fun ChartScreen(chartType: Int, isTalkBackEnabled: State<Boolean>) {
    when (chartType) {
        1 -> BarChartScreen(isTalkBackEnabled.value)
        2 -> WaveChartScreen(isTalkBackEnabled.value)
        3 -> LineChartScreen(isTalkBackEnabled.value)
        4 -> PieChartScreen(isTalkBackEnabled.value)
        5 -> DonutPieChartScreen(isTalkBackEnabled.value)
    }
}