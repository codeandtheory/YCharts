package com.ygraph.components.graph.linegraph

import com.ygraph.components.charts.linegraph.getYAxisScale
import com.ygraph.components.common.model.Point
import org.junit.Assert
import org.junit.Test

class LineGraphExtensionsTest {
    @Test
    fun `Given list of points exact yMin and yMax value should be calculated`() {
        // Given
        val chartData = listOf(
            Point(0f, 10f), Point(1f, 30f), Point(2f, 15f), Point(3f, 50f)
        )
        val steps = 2

        // When
        val (yMin, yMax, scale) = getYAxisScale(chartData, steps)

        //Then
        Assert.assertEquals(yMin.toInt(), 10)
        Assert.assertEquals(yMax.toInt(), 50)
    }

    @Test
    fun `Given list of points when drawing axis using the scale the value should be withing yMin and yMax`() {
        // Given
        val chartData = listOf(
            Point(0f, 10f), Point(1f, 30f), Point(2f, 15f), Point(3f, 50f)
        )
        val steps = 2

        // When
        val (yMin, yMax, scale) = getYAxisScale(chartData, steps)

        //Then
        for (i in 1 until steps) {
            val value = i * scale
            Assert.assertTrue(value >= yMin)
            Assert.assertTrue(value <= yMax)
        }
    }

    @Test
    fun `Given empty list of points all the values should be zero`() {
        // Given
        val chartData = listOf<Point>()
        val steps = 2

        // When
        val (yMin, yMax, scale) = getYAxisScale(chartData, steps)

        //Then
        Assert.assertEquals(yMin.toInt(), 0)
        Assert.assertEquals(yMax.toInt(), 0)
        Assert.assertEquals(scale.toInt(), 0)
    }
}
