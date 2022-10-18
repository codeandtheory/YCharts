package co.yml.charts.extensions

import androidx.compose.ui.geometry.Offset
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.extensions.getXMaxAndMinPoints
import co.yml.charts.common.extensions.getYMaxAndMinPoints
import co.yml.charts.common.extensions.isDragLocked
import co.yml.charts.common.model.Point
import org.junit.Assert.*
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
        val offset = Offset(30f,20f)
        val dragOffsetX = 25f
        val xOffset = 20f

        val isDragLocked = offset.isDragLocked(dragOffsetX, xOffset)
        assertEquals(isDragLocked, true)
    }
}
