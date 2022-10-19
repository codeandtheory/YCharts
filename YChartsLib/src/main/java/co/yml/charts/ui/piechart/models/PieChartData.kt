package co.yml.charts.ui.piechart.models

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.utils.sum
import co.yml.charts.common.model.PlotData
import co.yml.charts.common.model.PlotType

data class PieChartData(val slices: List<Slice>, override val plotType: PlotType) : PlotData {
    val totalLength: Float
        get() {
            return slices.sum()
        }

    data class Slice(
        val label: String,
        val value: Float,
        val color: Color,
        val sliceDescription: (Int) -> String = { slicePercentage ->
            "Slice name : $label  \nPercentage  : $slicePercentage %"
        }
    )
}
