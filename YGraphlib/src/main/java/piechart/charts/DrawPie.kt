package com.ygraph.components.piechart.charts

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Color for slice [color].
 * StartAngle for the slice, from where to start draw [startAngle].
 * Process of slice [arcProgress].
 * Size of the chart [size].
 * StrokeWidth for the pie chart [strokeWidth].
 * Padding from top left [padding].
 * DonutPieChart or PieChart to indicate  [isDonut].
 * DonutPieChart zoom slice if IsActive [isActive].
 */

fun DrawScope.drawPie(
    color: Color,
    startAngle: Float,
    arcProgress: Float,
    size: Size,
    strokeWidth: Float =  100f,
    padding: Float,
    isDonut: Boolean = false,
    isActive: Boolean = false
): Path {

    return Path().apply {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = arcProgress,
            useCenter = !isDonut,
            size = size,
            style = if (isDonut) Stroke(
                width = if (isActive) (strokeWidth + 20f) else strokeWidth,
            ) else Fill,

            topLeft = Offset(padding / 2, padding / 2)
        )
    }
}