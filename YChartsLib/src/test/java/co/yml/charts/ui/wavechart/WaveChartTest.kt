package co.yml.charts.ui.wavechart

import androidx.compose.ui.geometry.Offset
import co.yml.charts.ui.wavechart.model.AxisPosition
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.abs

class WaveChartTest {

    @Test
    fun `Given two points on a line find slop and y intercept`() {
        //Give
        val p1 = Offset(-3f, 2f)
        val p2 = Offset(-15f, -6f)

        //When
        val (m1, b1) = findSlopeAndYIntercept(p1, p2)

        //Then
        assertEquals(2f / 3f, m1)
        assertEquals(4f, b1)
    }

    @Test
    fun `Given two points on a line where y values are 0 then return 0 as slop and y intercept`() {
        //Give
        val p1 = Offset(-3f, 0f)
        val p2 = Offset(-15f, 0f)

        //When
        val (m1, b1) = findSlopeAndYIntercept(p1, p2)

        //Then
        assertEquals(0f, abs(m1))
        assertEquals(0f, abs(b1))
    }

    @Test
    fun `Given slops and y intercepts of two lines find the intersection point`() {
        //Give
        val m1 = 2f
        val b1 = 3f
        val m2 = -3f
        val b2 = 8f

        //When
        val intersection = findLineIntersection(m1, b1, m2, b2)

        //Then
        assertEquals(Offset(1f, 5f), intersection)
    }

    @Test
    fun `Given slops and y intercepts of two lines return null when slops are equal`() {
        //Give
        val m1 = 2f
        val b1 = 3f
        val m2 = 2f
        val b2 = 8f

        //When
        val intersection = findLineIntersection(m1, b1, m2, b2)

        //Then
        assertEquals(null, intersection)
    }

    @Test
    fun `Given t and two points find intermediate point`() {
        //Given
        val t = 0.5
        val p0 = Offset(10f, 10f)
        val p1 = Offset(20f, 30f)

        //When
        val interpolate = interpolate(t, p0, p1)

        //Then
        assertEquals(Offset(15f, 20f), interpolate)
    }

    @Test
    fun `Given 4 control points on a cubic curve find intermediate point based on t value`() {
        //Given
        val t = 0.5
        val p0 = Offset(10f, 10f)
        val p1 = Offset(20f, 30f)
        val p2 = Offset(40f, 30f)
        val p3 = Offset(50f, 10f)

        //When
        val curvePoint = findCubicCurvePoints(t, p0, p1, p2, p3)

        //Then
        assertEquals(Offset(30f, 25f), curvePoint)
    }

    @Test
    fun `Given a list of Offsets find the closest y value for the given point`() {
        //Given
        val list = listOf(Offset(10f, 10f), Offset(15f, 15f), Offset(18f, 18f), Offset(20f, 20f))
        val target = 16f

        //When
        val nearestValue = findNearestValue(list, target)

        //Then
        assertEquals(Offset(15f, 15f), nearestValue)
    }

    @Test
    fun `Given two points calculated cubicPoints value should be returned`() {
        // Given
        val startPoint = Offset(1f, 30f)
        val endPoint = Offset(2f, 10f)

        // When
        val pairCubicPoints = findControlPoints(startPoint, endPoint)

        //Then
        val expectedCubic1Point = Offset(1.3f, 30f)
        val expectedCubic2Point = Offset(1.7f, 10f)

        assertEquals(expectedCubic1Point, pairCubicPoints.first)
        assertEquals(expectedCubic2Point, pairCubicPoints.second)
    }


    @Test
    fun `Given two points calculated cubicPoints1 y value should be returned`() {
        // Given
        val startPoint = Offset(1f, 30f)
        val endPoint = Offset(2f, 10f)

        // When
        val pairCubicPoints = findControlPoints(startPoint, endPoint)

        //Then
        assertEquals(30f, pairCubicPoints.first.y)
    }

    @Test
    fun `Given y values of two points position should return bottom`() {
        // Given
        val startPoint = 650f
        val endPoint = 700f
        val xAxisYPosition = 500f

        // When
        val position = findPointPosition(startPoint, endPoint, xAxisYPosition)

        //Then
        assertEquals(AxisPosition.BOTTOM, position)
    }

    @Test
    fun `Given y values of two points of curve position should return top`() {
        // Given
        val startPoint = 450f
        val endPoint = 400f
        val xAxisYPosition = 500f

        // When
        val position = findPointPosition(startPoint, endPoint, xAxisYPosition)

        //Then
        assertEquals(AxisPosition.TOP, position)
    }

    @Test
    fun `Given y values of two points of curve position should return intersect`() {
        // Given
        val startPoint = 450f
        val endPoint = 550f
        val xAxisYPosition = 500f

        // When
        val position = findPointPosition(startPoint, endPoint, xAxisYPosition)

        //Then
        assertEquals(AxisPosition.INTERSECT, position)
    }

    @Test
    fun `Given y values of two points of curve position should return top when start point is equal to xAxis y value`() {
        // Given
        val startPoint = 500f
        val endPoint = 550f
        val xAxisYPosition = 500f

        // When
        val position = findPointPosition(startPoint, endPoint, xAxisYPosition)

        //Then
        assertEquals(AxisPosition.BOTTOM, position)
    }

    @Test
    fun `Given y values of two points of curve position should return bottom when end point is equal to xAxis y value`() {
        // Given
        val startPoint = 450f
        val endPoint = 500f
        val xAxisYPosition = 500f

        // When
        val position = findPointPosition(startPoint, endPoint, xAxisYPosition)

        //Then
        assertEquals(AxisPosition.TOP, position)
    }

    @Test
    fun `Given curve points return the point intersect on xAxis y position`() {
        // Given
        val curvePoints = listOf(
            Offset(100f, 150f),
            Offset(200f, 250f),
            Offset(300f, 350f),
            Offset(400f, 450f),
            Offset(500f, 650f),
            Offset(600f, 750f)
        )
        val xAxisYPosition = 500f

        // When
        val intersectionPoint = getCurveIntersectionPoint(curvePoints, xAxisYPosition)

        //Then
        assertEquals(Offset(400f, 450f), intersectionPoint)
    }

    @Test
    fun `Given control points of a cubic curve find the points on the curve`() {
        // Given
        val p0 = Offset(0f, 0f)
        val p1 = Offset(1f, 2f)
        val p2 = Offset(3f, 4f)
        val p3 = Offset(4f, 3f)

        // When
        val controlPoints = getCurvePoints(p0, p1, p2, p3)

        //Then
        assertEquals(Offset(4f, 3f), controlPoints.last())
    }
}