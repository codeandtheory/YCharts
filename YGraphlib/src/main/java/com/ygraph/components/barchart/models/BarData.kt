package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.Color
import com.ygraph.components.common.model.Point


/**
 * Data class for individual bar.
 * @param point : Axis point
 * @param color: Color of a bar
 * @param label: label of a bar
 * @param gradientColorList: Color list for the gradient bar

 */
data class BarData(
    val point: Point, val color: Color = Color.Red, val label: String = "",
    val gradientColorList: List<Color> = listOf(Color.Red, Color.Blue)
)
