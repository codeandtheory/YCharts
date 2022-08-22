package com.ygraph.components.barchart.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.Shape
import com.ygraph.components.common.model.Point

/**
return the shape that is used to mask a particular area for given leftPadding & rightPadding
 */
class RowClip(private val leftPadding: Float, private val rightPadding: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            Rect(
                leftPadding,
                0f,
                size.width - rightPadding.value * density.density,
                size.height
            )
        )
    }
}


/**
 * returns the draw offset for bar graph.
 * @param point : chartData point
 * @param xMin: Minimum value on the x axis
 * @param yMin: Minimum value on the y axis
 * @param xOffset: Distance between bars
 * @param yOffset: Distance between y axis points
 * @param xLeft: X starting point of bar graph
 * @param scrollOffset: Scroll offset
 * @param yBottom: Y starting point of bar graph
 */
fun getDrawOffset(
    point: Point, xMin: Float, xOffset: Float,
    xLeft: Float, scrollOffset: Float, yBottom: Float, yOffset: Float, yMin: Float
): Offset {
    val (x, y) = point
    val x1 = ((x - xMin) * xOffset) + xLeft - scrollOffset
    val y1 = yBottom - ((y - yMin) * yOffset)
    return Offset(x1, y1)
}
