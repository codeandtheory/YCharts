package co.yml.kmm.charts.ui.barchart.models

/**
 * Data class for individual group bar.
 * @param label: Label of a bar
 * @param barList: List of individual bars inside a group bar with each bar data of type [BarData]
 */
data class GroupBar(val label: String, val barList: List<BarData>) {
    val yMax: Float
        get() = this.barList.map { it.point }.maxOf { it.y }
}
