package com.ygraph.components.common.extensions

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.ui.geometry.Offset
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
