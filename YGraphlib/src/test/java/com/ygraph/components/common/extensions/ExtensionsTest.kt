package com.ygraph.components.common.extensions

import androidx.compose.ui.geometry.Offset
import com.ygraph.components.barchart.utils.getDrawOffset
import com.ygraph.components.common.model.Point
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
    fun `Given a point and drag lock status should be calculated`() {
        val offset = Offset(30f,20f)
        val dragOffsetX = 25f
        val xOffset = 20f

        val isDragLocked = offset.isDragLocked(dragOffsetX, xOffset)
        assertEquals(isDragLocked, true)
    }

    @Test
    fun `Given a point drawOffset should be positive`() {
        val point = Point(100f, 20f)
        val drawOffset = getDrawOffset(
            point = point,
            xMin = 0f,
            xOffset = 10f,
            xLeft = 20f,
            scrollOffset = 50f,
            yBottom = 1500f,
            yOffset = 20f,
            yMin = 0f
        )
        assertTrue(drawOffset.x > 0 && drawOffset.y > 0)
    }


    @Test
    fun `Given scroll offset,xleft,xmin are zero drawOffset should be product of xOffset and xValue`() {
        val point = Point(1f, 20f)
        val xOffset = 20f
        val drawOffset = getDrawOffset(
            point = point,
            xMin = 0f,
            xOffset = xOffset,
            xLeft = 0f,
            scrollOffset = 0f,
            yBottom = 1500f,
            yOffset = 20f,
            yMin = 0f
        )
        assertEquals(drawOffset.x, xOffset*point.x)
    }
}
