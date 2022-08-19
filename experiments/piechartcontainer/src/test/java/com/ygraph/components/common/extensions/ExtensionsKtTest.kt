package com.ygraph.components.common.extensions

import com.ygraph.components.common.model.Point
import org.junit.Assert.*
import org.junit.Test

class ExtensionsKtTest{
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
    fun `Find the maximum Y point from the steps size and max value `() {
       val stepSize = 10
       val maxValue = 45f
        
        val maximumYValue = getMaxElementInYAxis(maxValue,stepSize)

        assertEquals(maximumYValue, 50)
    }
}
