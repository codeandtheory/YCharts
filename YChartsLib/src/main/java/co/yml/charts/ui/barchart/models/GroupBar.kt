package co.yml.charts.ui.barchart.models

data class GroupBar(val label: String, val barList: List<BarData>) {
    val yMax: Float
        get() = this.barList.map { it.point }.maxOf { it.y }
}
