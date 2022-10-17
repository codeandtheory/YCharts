package com.ygraph.components.charts.linechart

import androidx.compose.ui.geometry.Offset
import com.ygraph.components.common.model.Point
import kotlin.math.ceil

/**
 * returns total offset for given no of steps and offset .
 * @param offset: Distance of each step.
 * @param steps: No of steps in Y-Axis.
 */
fun getMaxElementInYAxis(offset: Float, steps: Int): Float {
    return (if (steps > 1) steps - 1 else 1) * offset
}

/**
 * returns Triple value with minY,maxY and scale of each Y-Axis step.
 * @param points: List of points to be drawn.
 * @param steps: Count of steps in the Y-Axis.
 */
fun getYAxisScale(
    points: List<Point>,
    steps: Int
): Triple<Float, Float, Float> {
    val yMin = points.takeIf { it.isNotEmpty() }?.minOf { it.y } ?: 0f
    val yMax = points.takeIf { it.isNotEmpty() }?.maxOf { it.y } ?: 0f
    val totalSteps = (yMax - yMin)
    val temp =
        totalSteps / if (steps > 1) (steps - 1) else 1 // First step starts from 0 by default
    val scale = ceil(temp)
    return Triple(yMin, yMax, scale)
}

/**
 * Returns true if the given tap offset is selected point or not else false
 */
fun Offset.isTapped(tapOffset: Float, xOffset: Float) =
    ((tapOffset) > x - xOffset / 2) && ((tapOffset) < x + xOffset / 2)
