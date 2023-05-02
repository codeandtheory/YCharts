package co.yml.charts.extensions

import androidx.compose.ui.geometry.Offset
import co.yml.charts.common.extensions.*
import co.yml.charts.common.model.Point
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsTest{
    @Test
    fun `Find proper maximum and minimum X values from the given points`() {
        val pointList = listOf(
            Point(0f, 10f), Point(1f, 20f), Point(2f, 30f), Point(3f, 40f)
        )

        val (xMin, xMax) = getXMaxAndMinPoints(pointList)

        assertEquals(xMin, 0f)
        assertEquals(xMax, 3f)
    }

    @Test
    fun `Find proper maximum and minimum Y values from the given points`() {
        val pointList = listOf(
            Point(0f, 10f), Point(1f, 20f), Point(2f, 30f), Point(3f, 40f)
        )

        val (yMin, yMax) = getYMaxAndMinPoints(pointList)

        assertEquals(yMin, 10f)
        assertEquals(yMax, 40f)
    }

    @Test
    fun `Given empty list  maximum and minimum points should be zero`() {
        val pointList = emptyList<Point>()

        val (yMin, yMax) = getYMaxAndMinPoints(pointList)

        assertEquals(yMin, 0f)
        assertEquals(yMax, 0f)
    }
    
    @Test
    fun `Find the maximum Y point from the steps size and max value`() {
        val stepSize = 10
        val maxValue = 45f

        val maximumYValue = getMaxElementInYAxis(maxValue,stepSize)

        assertEquals(maximumYValue, 50)
    }

    @Test
    fun `Given a point and proper drag lock status should be calculated`() {
        val offset = Offset(30f, 20f)
        val dragOffsetX = 25f
        val xOffset = 20f

        val isDragLocked = offset.isDragLocked(dragOffsetX, xOffset)
        assertEquals(isDragLocked, true)
    }

    @Test
    fun `Find the maximum X point from the steps size and max value`() {
        val stepSize = 10
        val maxValue = 45f

        val maximumYValue = getMaxElementInXAxis(maxValue, stepSize)

        assertEquals(maximumYValue, 50)
    }

    @Test
    fun `Return true when tapped point within the vertical bar bound`() {
        val middleOffset = Offset(747.5f, 727.8f)
        val tapOffset = Offset(745f, 807f)
        val xOffset = 122.5f
        val bottom = 950f
        val tapPadding = 35f

        val isTapped = middleOffset.isTapped(tapOffset, xOffset, bottom, tapPadding)

        assertEquals(isTapped, true)
    }

    @Test
    fun `Return false when tapped point outside the vertical bar bound`() {
        val middleOffset = Offset(747.5f, 727.8f)
        val tapOffset = Offset(500f, 807f)
        val xOffset = 122.5f
        val bottom = 950f
        val tapPadding = 35f

        val isTapped = middleOffset.isTapped(tapOffset, xOffset, bottom, tapPadding)

        assertEquals(isTapped, false)
    }

    @Test
    fun `Return true when tapped point within the horizontal bar bound`() {
        val middleOffset = Offset(250f, 708.8f)
        val tapOffset = Offset(360.7f, 723.6f)
        val yOffset = 122.5f
        val left = 250f
        val tapPadding = 35f
        val xAxisWidth = 636.75f

        val isTapped = middleOffset.isYAxisTapped(tapOffset, yOffset, left, tapPadding, xAxisWidth)

        assertEquals(isTapped, true)
    }

    @Test
    fun `Return false when tapped point outside the horizontal bar bound`() {
        val middleOffset = Offset(250f, 708.8f)
        val tapOffset = Offset(360.7f, 800f)
        val yOffset = 122.5f
        val left = 250f
        val tapPadding = 35f
        val xAxisWidth = 636.75f

        val isTapped = middleOffset.isYAxisTapped(tapOffset, yOffset, left, tapPadding, xAxisWidth)

        assertEquals(isTapped, false)
    }

    @Test
    fun `Return true when tapped point within the selected stacked bar bound`() {
        val middleOffset = Offset(250f, 500f)
        val tapOffset = Offset(260f, 550f)
        val barWidth = 25f
        val barHeight = 600f
        val tapPadding = 35f

        val isTapped = middleOffset.isStackedBarTapped(tapOffset, barWidth, barHeight, tapPadding)

        assertEquals(isTapped, true)
    }

    @Test
    fun `Return true when tapped point outside the the selected stacked bar bound`() {
        val middleOffset = Offset(250f, 500f)
        val tapOffset = Offset(260f, 770f)
        val barWidth = 25f
        val barHeight = 600f
        val tapPadding = 35f

        val isTapped = middleOffset.isStackedBarTapped(tapOffset, barWidth, barHeight, tapPadding)

        assertEquals(isTapped, false)
    }
}
