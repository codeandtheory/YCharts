package co.yml.kmm.charts.ui.piechart.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import co.yml.kmm.charts.ui.piechart.PieChartConstants.ONE_HUNDRED
import co.yml.kmm.charts.ui.piechart.PieChartConstants.TOTAL_ANGLE
import co.yml.kmm.charts.ui.piechart.models.PieChartData
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


/**
 * Returns the angle for given touch point
 * @param width: Width of the chart.
 * @param height: Height of the chart.
 * @param xPos: X offset of the tap point.
 * @param yPos: Y offset of the tap point.
 */
fun convertTouchEventPointToAngle(
    width: Float,
    height: Float,
    xPos: Float,
    yPos: Float
): Double {
    val x = xPos - (width * 0.5f)
    val y = yPos - (height * 0.5f)

    var angle = toDegrees(atan2(y.toDouble(), x.toDouble()) + PI / 2)
    angle = if (angle < 0) angle + 360 else angle
    return angle
}

/**
 * Returns the sum of all the arc values
 */
fun List<PieChartData.Slice>.sum(): Float {
    return this.map { it.value }.sum()
}

fun toDegrees(radians: Double): Double {
    return radians * 180 / PI
}

fun degreesToRadians(degrees: Double): Double {
    return degrees * PI / 180.0
}

/**
 * Returns the center points of the slice
 * @param sAngle Start angle of the point
 * @param arcProgress Progress angle of the point
 * @param size Size of the canvas
 * @param padding padding of the canvas
 */
fun getSliceCenterPoints(sAngle: Float, arcProgress: Float, size: Size, padding: Float):
        Triple<Float, Float, Float> {
    val arcCenter = sAngle + (arcProgress / 2)
    // Middle point radius is half of the radius of the pie chart
    val pointRadius = size.width / 4

    /* Calculate the x & y co-ordinates to show the label/percentage tex
    * find points using angle and radius
    *https://en.wikipedia.org/wiki/Polar_coordinate_system#Converting_between_polar_and_Cartesian_coordinates
    * */
    val x =
        (pointRadius / 2 * cos(degreesToRadians(arcCenter.toDouble()))) +
                size.center.x + padding / 2
    val y =
        (pointRadius / 2 * sin(degreesToRadians(arcCenter.toDouble()))) +
                size.center.y + padding / 2

    return Triple(arcCenter,x.toFloat(),y.toFloat())
}


/**
 * Returns the calculated proportion value of each arc
 * @param total: Total of the the slices.
 */
fun List<PieChartData.Slice>.proportion(total:Float): List<Float> {
    return this.map {
        it.value * ONE_HUNDRED / total
    }
}

/**
 * Returns the list of sweep angles
 */
fun List<Float>.sweepAngles(): List<Float> {
    return this.map {
        TOTAL_ANGLE * it / ONE_HUNDRED
    }
}

