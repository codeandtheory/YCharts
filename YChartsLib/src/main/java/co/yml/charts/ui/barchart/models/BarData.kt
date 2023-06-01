package co.yml.charts.ui.barchart.models

import androidx.compose.ui.graphics.Color
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point


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
    val dataCategoryOptions: DataCategoryOptions = DataCategoryOptions(),
    val description: String = if (dataCategoryOptions.isDataCategoryInYAxis) "Value of bar $label is value ${
        String.format(
            "%.2f",
            point.x
        )
    }" else "Value of bar $label is value ${String.format("%.2f", point.y)}"
)
