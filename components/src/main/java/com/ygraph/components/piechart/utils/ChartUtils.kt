package com.ygraph.components.piechart.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

sealed class ChartType{
    object PieChart : ChartType()
    object DonutPieChart : ChartType()
}

fun Float.calculateSectorThickness(sliceThick: Float = 0f ,area: Size): Float {

    val minSize = minOf(area.width, area.height)

    return minSize * (sliceThick / 200f)
}

fun Size.calculateSectorThickness(sliceThick: Float = 0f ,area: Size): Float {

    val minSize = minOf(area.width, area.height)

    return minSize * (sliceThick / 200f)
}

fun Size.calculateDrawableArea(area: Size): Rect {
    val sliceThicknessOffset =
        calculateSectorThickness(area = area) / 2f
    val offsetHorizontally = (area.width - area.height) / 2f

    return Rect(
        left = sliceThicknessOffset + offsetHorizontally,
        top = sliceThicknessOffset,
        right = area.width - sliceThicknessOffset - offsetHorizontally,
        bottom = area.height - sliceThicknessOffset
    )
}