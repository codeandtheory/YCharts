package com.ygraph.components.barchart.model

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color

data class GroupBar(val label: String, val data: List<Point>)

data class Point(
    val value: Float,
    val color: Color
) {

    // Will be initialise after finding draw area
    lateinit var drawableArea: Rect
}