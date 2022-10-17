package com.ygraph.components.graph.linegraph

import androidx.compose.ui.geometry.Offset
import com.ygraph.components.charts.linegraph.getCubicPoints
import com.ygraph.components.charts.linegraph.getMappingPointsToGraph
import com.ygraph.components.charts.linegraph.getMaxScrollDistance
import com.ygraph.components.common.model.Point
import org.junit.Assert
import org.junit.Test

class LineGraphTest {

    @Test
    fun `Given few points data n-1 size of cubicPoints1 & cubicPoints2 should be returned`() {
        // Given
        val pointsData = listOf(Offset(1f, 30f), Offset(2f, 10f), Offset(3f, 55f))

        // When
        val pairCubicPoints = getCubicPoints(pointsData)

        //Then
        Assert.assertEquals(pointsData.size - 1, pairCubicPoints.first.size)
        Assert.assertEquals(pointsData.size - 1, pairCubicPoints.second.size)
    }

    @Test
    fun `Given two points calculated cubicPoints1 value should be returned`() {
        // Given
        val pointsData = listOf(Offset(1f, 30f), Offset(2f, 10f))

        // When
        val pairCubicPoints = getCubicPoints(pointsData)

        //Then
        val expectedCubic1Point = Offset(
            (pointsData[1].x + pointsData.first().x) / 2,
            pointsData.first().y
        )

        Assert.assertEquals(expectedCubic1Point, pairCubicPoints.first.first())
    }

    @Test
    fun `Given two points calculated cubicPoints2 value should be returned`() {
        // Given
        val pointsData = listOf(Offset(1f, 30f), Offset(2f, 10f))

        // When
        val pairCubicPoints = getCubicPoints(pointsData)

        //Then
        val expectedCubic1Point = Offset(
            (pointsData[1].x + pointsData.first().x) / 2,
            pointsData[1].y
        )

        Assert.assertEquals(expectedCubic1Point, pairCubicPoints.second.first())
    }

    @Test
    fun `Given a container with input values valid scroll offset should be returned`() {
        // Given
        val columnWidth = 104f
        val xMax = 100f
        val xMin = 0f
        val xOffset = 200f
        val paddingRight = 40f
        val canvasWidth = 500f

        // When
        val scrollDistance = getMaxScrollDistance(
            columnWidth,
            xMax,
            xMin,
            xOffset,
            paddingRight,
            canvasWidth
        )

        Assert.assertTrue(scrollDistance > 0)
    }

    @Test
    fun `Given a total drawing width lesser than container width container should not be able to scrolled`() {
        // Given
        val columnWidth = 20f
        val xMax = 20f
        val xMin = 0f
        val xOffset = 20f
        val paddingRight = 40f
        val canvasWidth = 500f

        // When
        val scrollDistance = getMaxScrollDistance(
            columnWidth,
            xMax,
            xMin,
            xOffset,
            paddingRight,
            canvasWidth
        )

        Assert.assertEquals(scrollDistance, 0f)
    }

    @Test
    fun `Given input points size the transformed list size should be same`() {
        // Given
        val pointsData = listOf(Point(1f, 30f), Point(2f, 10f), Point(3f, 55f))
        val xLeft = 20f
        val scrollOffset = 0f
        val xMin = 0f
        val xOffset = 20f
        val yBottom = 40f
        val yMin = 0f
        val yOffset = 20f

        //When
        val transformedPointsList = getMappingPointsToGraph(
            pointsData, xMin,
            xOffset,
            xLeft,
            scrollOffset,
            yBottom,
            yMin,
            yOffset
        )

        // Then
        Assert.assertEquals(pointsData.size, transformedPointsList.size)

    }

    @Test
    fun `Given a point x and transformed point x to w_r_t container should be same `() {
        // Given
        val pointData = listOf(Point(1f, 30f))
        val xLeft = 20f
        val scrollOffset = 0f
        val xMin = 0f
        val xOffset = 20f
        val yBottom = 40f
        val yMin = 0f
        val yOffset = 20f

        //When
        val transformedXPoint = getMappingPointsToGraph(
            pointData, xMin,
            xOffset,
            xLeft,
            scrollOffset,
            yBottom,
            yMin,
            yOffset
        ).first().x

        //Then
        val expectedXPoint = ((pointData.first().x - xMin) * xOffset) + xLeft - scrollOffset
        Assert.assertEquals(expectedXPoint, transformedXPoint)
    }

    @Test
    fun `Given a point y and transformed point y to w_r_t container should be same `() {
        // Given
        val pointData = listOf(Point(1f, 30f))
        val xLeft = 20f
        val scrollOffset = 0f
        val xMin = 0f
        val xOffset = 20f
        val yBottom = 40f
        val yMin = 0f
        val yOffset = 20f

        //When
        val transformedYPoint = getMappingPointsToGraph(
            pointData, xMin,
            xOffset,
            xLeft,
            scrollOffset,
            yBottom,
            yMin,
            yOffset
        ).first().y

        //Then
        val expectedYPoint = yBottom - ((pointData.first().y - yMin) * yOffset)
        Assert.assertEquals(expectedYPoint, transformedYPoint)
    }

}


