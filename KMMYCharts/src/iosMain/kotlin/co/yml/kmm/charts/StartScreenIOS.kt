package co.yml.kmm.charts

import androidx.compose.runtime.Composable

@Composable
internal fun StartScreenIOS(chartType: Int, isTalkbackEnabled: Boolean) {
    when (chartType) {
        1 -> BarChartScreen(isTalkbackEnabled)
        2 -> WaveChartScreen(isTalkbackEnabled)
        3 -> LineChartScreen(isTalkbackEnabled)
        4 -> PieChartScreen(isTalkbackEnabled)
        5 -> DonutPieChartScreen(isTalkbackEnabled)
    }
}