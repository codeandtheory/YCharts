package com.ygraph.components.barchart.models

import android.graphics.PointF
import androidx.compose.ui.graphics.Color


/**
 * Data class for individual bar.
 * @param point : Axis point
 * @param color: Color of a bar
 * @param label: label of a bar
 * @param gradientColorList: Color list for the gradient bar

 */
data class BarData(
    val point: PointF, val color: Color = Color.Red, val label: String,
    val gradientColorList: List<Color> = listOf(Color.Red, Color.Blue)
)
