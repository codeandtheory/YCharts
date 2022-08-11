package com.ygraph.components.piechart.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import com.ygraph.components.piechart.PieChartConstants
import com.ygraph.components.piechart.PieChartConstants.ONE_HUNDRED
import com.ygraph.components.piechart.PieChartConstants.TOTAL_ANGLE
import com.ygraph.components.piechart.models.PieChartData
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

sealed class ChartType{
    object PieChart : ChartType()
    object DonutPieChart : ChartType()
}

fun convertTouchEventPointToAngle(
    width: Float,
    height: Float,
    xPos: Float,
    yPos: Float
): Double {
    val x = xPos - (width * 0.5f)
    val y = yPos - (height * 0.5f)

    var angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble()) + Math.PI / 2)
    angle = if (angle < 0) angle + 360 else angle
    return angle
}

fun List<PieChartData.Slice>.sum(): Float {
   return this.map { it.value }.sum()
}


/**
 * @param sAngle Start angle of the point
 * @param arcProgress Progress angle of the point
 * @param size Size of the canvas
 * @param padding padding of the canvas
 */
fun getSliceCenterPoints(sAngle: Float, arcProgress: Float, size: Size, padding: Float): Triple<Float, Float, Float> {
    val arcCenter = sAngle + (arcProgress / 2)
    //middle point radius is half of the radius of the pie chart
    val pointRadius = size.width / 4

    /*Calculate the x & y co-ordinates to show the label/percentage tex
    * find points using angle and radius
    *https://en.wikipedia.org/wiki/Polar_coordinate_system#Converting_between_polar_and_Cartesian_coordinates
    * */
    val x =
        (pointRadius * cos(Math.toRadians(arcCenter.toDouble()))) +
                size.center.x + padding / 2
    val y =
        (pointRadius * sin(Math.toRadians(arcCenter.toDouble()))) +
                size.center.y + padding / 2

    return Triple(arcCenter,x.toFloat(),y.toFloat())
}


// Calculate each proportion value
fun List<PieChartData.Slice>.proportion(total:Float): List<Float> {
    return this.map {
        it.value * ONE_HUNDRED / total
    }
}

// Convert each proportion value to angles
fun List<Float>.sweepAngles(): List<Float> {
    return this.map {
        TOTAL_ANGLE * it / ONE_HUNDRED
    }
}

