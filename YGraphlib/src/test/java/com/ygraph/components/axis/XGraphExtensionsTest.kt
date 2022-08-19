package com.ygraph.components.axis

import com.ygraph.components.common.model.Point
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class XGraphExtensionsTest {

    @Test
    fun `Given list of points exact xMin and xMax value should be calculated`() {
        // Given
        val chartData = listOf(
            Point(0f, 10f), Point(1f, 30f), Point(2f, 15f), Point(3f, 50f)
        )
        val steps = 2

        // When
        val (xMin, xMax, scale) = getXAxisScale(chartData, steps)

        //Then
        assertEquals(xMin.toInt(), 0)
        assertEquals(xMax.toInt(), 3)
    }

    @Test
    fun `Given list of points when drawing axis using the scale the value should be withing xMin and xMax`() {
        // Given
        val chartData = listOf(
            Point(0f, 10f), Point(1f, 30f), Point(2f, 15f), Point(3f, 50f)
        )
        val steps = 2

        // When
        val (xMin, xMax, scale) = getXAxisScale(chartData, steps)

        //Then
        for (i in 0 until steps) {
            val value = i * scale
            assertTrue(value >= xMin)
            assertTrue(value <= xMax)
        }
    }

    @Test
    fun `Given empty list of points all the values should be zero`() {
        // Given
        val chartData = listOf<Point>()
        val steps = 2

        // When
        val (xMin, xMax, scale) = getXAxisScale(chartData, steps)

        //Then
        assertEquals(xMin.toInt(), 0)
        assertEquals(xMax.toInt(), 0)
        assertEquals(scale.toInt(), 0)
    }

}
