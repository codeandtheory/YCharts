package com.ygraph.components.barchart.model

import androidx.compose.ui.graphics.Color

data class BarChartData (val bars: List<Bar>)

data class Bar(
    val value: Float,
    val color: Color,
    val label: String
)
