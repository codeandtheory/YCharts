package co.yml.charts.axis

import co.yml.charts.common.model.Point
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

class YChartsExtensionsTest {

    @MockK
    private val axisData: AxisData = mockk()

    @Test
    fun `When getAxisInitValues extension is invoked should not return values lesser than zero`() {
        // Given
        every { axisData.steps }.returns(5)

        // When
        val values = getAxisInitValues(
            axisData,
            687f, // 250.dp.toPx()
            20f,
            20f
        )

        // Then
        assertTrue(values.first > 0)
        assertTrue(values.second > 0)
    }

    @Test
    fun `Given yAxisData segmentHeight should be less than yaxisHeight `() {
        // Given
        every { axisData.steps }.returns(5)

        // When
        val values = getAxisInitValues(
            axisData,
            687f, // 250.dp.toPx()
            20f,
            20f
        )

        // Then
        assertTrue(values.second < values.first)
    }


    @Test
    fun `Given list of points exact yMin and yMax value should be calculated`() {
        // Given
        val chartData = listOf(
            Point(10f, 0f), Point(30f, 1f), Point(15f, 2f), Point(50f, 3f)
        )
        val steps = 2

        // When
        val (yMin, yMax, yAxisScale) = getYAxisScale(chartData, steps)
        //Then
        Assert.assertEquals(yMin.toInt(), 0)
        Assert.assertEquals(yMax.toInt(), 3)
    }

    @Test
    fun `Given list of points when drawing axis using the scale the value should be withing xMin and xMax`() {
        // Given
        val chartData = listOf(
            Point(10f, 0f), Point(30f, 1f), Point(15f, 2f), Point(50f, 3f)
        )
        val steps = 2

        // When
        val (yMin, yMax, yAxisScale) = getYAxisScale(chartData, steps)

        //Then
        for (i in 0 until steps) {
            val value = i * yAxisScale
            assertTrue(value >= yMin)
            assertTrue(value <= yMax)
        }
    }


    @Test
    fun `Given empty list of points all the values should be zero`() {
        // Given
        val chartData = listOf<Point>()
        val steps = 2

        // When
        val (yMin, yMax, yAxisScale) = getYAxisScale(chartData, steps)

        //Then
        Assert.assertEquals(yMin.toInt(), 0)
        Assert.assertEquals(yMax.toInt(), 0)
        Assert.assertEquals(yAxisScale.toInt(), 0)
    }
}
