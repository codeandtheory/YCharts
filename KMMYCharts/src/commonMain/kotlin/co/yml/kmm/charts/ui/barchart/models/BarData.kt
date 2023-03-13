package co.yml.kmm.charts.ui.barchart.models

import androidx.compose.ui.graphics.Color
import co.yml.kmm.charts.common.model.Point


/**
 * Data class for individual bar.
 * @param point : Axis point
 * @param color: Color of a bar
 * @param label: label of a bar
 * @param gradientColorList: Color list for the gradient bar
 * @param description: Description to describe bar value for accessibility service.
 */
data class BarData(
    val point: Point,
    val color: Color = Color.Red,
    val label: String = "",
    val gradientColorList: List<Color> = listOf(Color.Red, Color.Blue),
    val description: String = "Value of bar $label is value ${point.y}"
)
