package com.ygraph.components.piechart.utils

import androidx.compose.ui.geometry.Size

sealed class ChartType{
    object PieChart : ChartType()
    object DonutPieChart : ChartType()
}

fun Size.calculateSectorThickness(sliceThick: Float = 0f ,area: Size): Float {

    val minSize = minOf(area.width, area.height)

    return minSize * (sliceThick / 200f)
}

fun convertTouchEventPointToAngle(
    width: Float,
    height: Float,
    xPos: Float,
    yPos: Float
): Double {
    var x = xPos - (width * 0.5f)
    val y = yPos - (height * 0.5f)

    var angle = Math.toDegrees(Math.atan2(y.toDouble(), x.toDouble()) + Math.PI / 2)
    angle = if (angle < 0) angle + 360 else angle
    return angle
}