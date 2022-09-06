package com.ygraph.components.piechart.models

import androidx.compose.ui.graphics.Color
import com.ygraph.components.piechart.utils.sum

data class PieChartData(val slices: List<Slice>) {
     val totalLength: Float
        get() {
            return slices.sum()
        }
    data class Slice(val label: String, val value: Float, val color: Color)
}
