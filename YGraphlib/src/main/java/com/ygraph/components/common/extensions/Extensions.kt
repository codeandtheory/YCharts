package com.ygraph.components.common.extensions

import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.ygraph.components.common.model.Point


/**
return the width of text in canvas drawn text
 */
fun String.getTextWidth(paint: Paint): Float {
    return paint.measureText(this)
}

/**
return the height of text in canvas drawn text
 */
fun String.getTextHeight(paint: Paint): Int {
    val bounds = Rect()
    paint.getTextBounds(
        this,
        0,
        this.length,
        bounds
    )
    return bounds.height()
}


/**
return the maximum and minimum points of X axis
 */
fun getXMaxAndMinPoints(
    points: List<Point>,
): Pair<Float, Float> {
    val xMin = points.minOf { it.x }
    val xMax = points.maxOf { it.x }
    return Pair(xMin, xMax)
}


/**
 * @param points List of points
return the maximum and minimum points of Y axis
 */
fun getYMaxAndMinPoints(
    points: List<Point>,
): Pair<Float, Float> {
    if (points.isEmpty())
        return Pair(0f, 0f)
    val xMin = points.minOf { it.y }
    val xMax = points.maxOf { it.y }
    return Pair(xMin, xMax)
}

/**
 * @param yMax Maximum value in the Y axis
 * @param yStepSize size of one step in the Y axis
return the maximum value of Y axis
 */
fun getMaxElementInYAxis(yMax: Float, yStepSize: Int): Int {
    var reqYLabelsQuo =
        (yMax / yStepSize)
    val reqYLabelsRem = yMax.rem(yStepSize)
    if (reqYLabelsRem > 0f) {
        reqYLabelsQuo += 1
    }
    return reqYLabelsQuo.toInt() * yStepSize
}


fun Offset.isDragLocked(dragOffset: Float, xOffset: Float) =
    ((dragOffset) > x - xOffset / 2) && ((dragOffset) < x + xOffset / 2)


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
            androidx.compose.ui.geometry.Rect(
                leftPadding,
                0f,
                size.width - rightPadding.value * density.density,
                size.height
            )
        )
    }
}

/**
 * returns the background rect for the highlighted text.
 * @param x : X point.
 * @param y: Y point.
 * @param text: Text to be drawn inside the background.
 * @param paint: Background paint.
 */
fun getTextBackgroundRect(
    x: Float,
    y: Float,
    text: String,
    paint: TextPaint
): Rect {
    val fontMetrics = paint.fontMetrics
    val textLength = paint.measureText(text)
    return Rect(
        (x - (textLength / 2)).toInt(),
        (y + fontMetrics.top).toInt(),
        (x + (textLength / 2)).toInt(),
        (y + fontMetrics.bottom).toInt()
    )
}
