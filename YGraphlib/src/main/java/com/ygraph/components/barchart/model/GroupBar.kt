package com.ygraph.components.barchart.model

import androidx.compose.ui.graphics.Color

data class GroupBar(val label: String, val data: List<Point>)

data class Point(
    val value: Float,
    val color: Color
)