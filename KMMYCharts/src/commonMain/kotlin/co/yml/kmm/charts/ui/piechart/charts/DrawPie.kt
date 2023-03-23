package co.yml.kmm.charts.ui.piechart.charts

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import co.yml.kmm.charts.ui.piechart.models.PieChartConfig

/**
 * Extension for drawing arcs
 * @param color : Color for slice
 * @param startAngle : StartAngle for the slice, from where to start draw
 * @param arcProgress : Process of slice
 * @param size : Size of the chart
 * @param strokeWidth : StrokeWidth for the pie chart
 * @param padding : Padding from top left
 * @param isDonut : DonutPieChart or PieChart to indicate
 * @param isActive : DonutPieChart zoom slice if IsActive
 */

fun DrawScope.drawPie(
    color: Color,
    startAngle: Float,
    arcProgress: Float,
    size: Size,
    strokeWidth: Float = 100f,
    padding: Float,
    isDonut: Boolean = false,
    isActive: Boolean = false,
    pieChartConfig: PieChartConfig
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = arcProgress,
        useCenter = !isDonut,
        size = size,
        alpha = if (isActive) pieChartConfig.activeSliceAlpha else pieChartConfig.inActiveSliceAlpha,
        style = if (isDonut) Stroke(
            width = if (isActive) (strokeWidth + 20f) else strokeWidth,
        ) else Fill,

        topLeft = Offset(padding / 2, padding / 2)
    )
}
