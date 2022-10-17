package com.ygraph.components.graph.bargraph

import com.ygraph.components.charts.barchart.getDrawOffset
import com.ygraph.components.charts.barchart.getMaxScrollDistance
import com.ygraph.components.common.model.Point
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BarChartTest{
    @Test
    fun `Given a container with input values valid scroll offset should be returned`() {
        // Given
        val columnWidth = 104f
        val xMax = 100f
        val xMin = 0f
        val xOffset = 200f
        val xLeft = 10f
        val paddingRight = 40f
        val canvasWidth = 500f

        // When
        val scrollDistance = getMaxScrollDistance(
            columnWidth,
            xMax,
            xMin,
            xOffset,
            xLeft,
            paddingRight,
            canvasWidth
        )

        assertTrue(scrollDistance > 0)
    }


    @Test
    fun `Given a total drawing width lesser than container width container should not be able to scrolled`() {
        // Given
        val columnWidth = 20f
        val xMax = 20f
        val xMin = 0f
        val xOffset = 20f
        val xLeft = 10f
        val paddingRight = 40f
        val canvasWidth = 500f

        // When
        val scrollDistance = getMaxScrollDistance(
            columnWidth,
            xMax,
            xMin,
            xOffset,
            xLeft,
            paddingRight,
            canvasWidth
        )

        assertEquals(scrollDistance, 0f)
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
