package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.Color

data class GroupBar(
    val label: String,
    val barList: List<Bar>
) {
    val yMax: Float
        get() = this.barList.maxByOrNull { it.value }?.value ?: 0f
}

data class Bar(
    val value: Float,
    val color: Color?,
    val name: String,
)


