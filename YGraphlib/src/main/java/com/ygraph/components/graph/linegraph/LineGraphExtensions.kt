package com.ygraph.components.graph.linegraph

import com.ygraph.components.common.model.Point
import kotlin.math.ceil

fun getMaxElementInYAxis(offset: Float, steps: Int): Float {
    return (if (steps > 1) steps - 1 else 1) * offset
}

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
