package com.ygraph.components.charts.linegraph.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Represents the grid lines for any graph
 * @param color: Defines the color of the grid lines.
 * @param lineWidth: Defines the width of the lines.
 * @param pathEffect optional effect or pattern to apply to the line.
 * @param alpha Opacity to be applied to the path from 0.0f to 1.0f representing.
 * fully transparent to fully opaque respectively.
 * @param colorFilter ColorFilter to apply to the [color] when drawn into the destination.
 * @param blendMode Blending algorithm to be applied to the path when it is drawn.
 * @param enableHorizontalLines False to disable horizontal line else true.
 * @param enableVerticalLines False to disable vertical lines else true.
 * @param drawHorizontalLines Draw param used to draw the horizontal lines with given input params.
 * @param drawVerticalLines Draw param used to draw the vertical lines with given input params.
 */
data class GridLines(
    val color: Color = Color.LightGray,
    val lineWidth: Dp = 1.dp,
    val pathEffect: PathEffect? = null,
    val alpha: Float = 1.0f,
    val colorFilter: ColorFilter? = null,
    val blendMode: BlendMode = DrawScope.DefaultBlendMode,
    val enableHorizontalLines: Boolean = true,
    val enableVerticalLines: Boolean = true,
    val drawHorizontalLines: DrawScope.(Float, Float, Float) -> Unit = { xStart, y, xEnd ->
        drawLine(
            color = color,
            start = Offset(xStart, y),
            end = Offset(xEnd, y),
            strokeWidth = lineWidth.toPx(),
            pathEffect = pathEffect,
            colorFilter = colorFilter,
            blendMode = blendMode
        )
    },
    val drawVerticalLines: DrawScope.(Float, Float, Float) -> Unit = { x, yStart, yEnd ->
        drawLine(
            color = color,
            start = Offset(x, yStart),
            end = Offset(x, yEnd),
            strokeWidth = lineWidth.toPx(),
            pathEffect = pathEffect,
            colorFilter = colorFilter,
            blendMode = blendMode
        )
    }
)
