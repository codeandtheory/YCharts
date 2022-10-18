package co.yml.charts.utils

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import co.yml.charts.charts.piechart.PieChartConstants.DEFAULT_PADDING
import co.yml.charts.charts.piechart.PieChartConstants.DEFAULT_START_ANGLE
import co.yml.charts.charts.piechart.models.PieChartData
import co.yml.charts.charts.piechart.utils.convertTouchEventPointToAngle
import co.yml.charts.charts.piechart.utils.getSliceCenterPoints
import co.yml.charts.charts.piechart.utils.proportion
import co.yml.charts.charts.piechart.utils.sum
import co.yml.charts.charts.piechart.utils.sweepAngles
import org.junit.Assert.*
import org.junit.Test

class PieChartUtilsTest {

    private val sliceList = listOf(
        PieChartData.Slice("A", 10f, Color.Cyan),
        PieChartData.Slice("B", 20f, Color.Red),
        PieChartData.Slice("C", 40f, Color.Black),
        PieChartData.Slice("D", 30f, Color.Blue)
    )


    @Test
    fun `Slice total calculation is correct`() {
        val sum = sliceList.sum()
        assertEquals(100f, sum)
    }

    @Test
    fun `Proportion calculation is correct`() {
        val sum = sliceList.sum()
        val proportions = sliceList.proportion(sum)
        assertEquals(10f, proportions.first())
    }

    @Test
    fun `Sweep angle calculation is correct`() {
        val sum = sliceList.sum()
        val proportions = sliceList.proportion(sum)
        val sweepAngles = proportions.sweepAngles()
        assertEquals(36f, sweepAngles.first())
    }

    @Test
    fun `Arch center calculation is correct and x and y are positive`() {

        val sideSize = 200
        val padding = (sideSize * DEFAULT_PADDING) / 100f
        val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

        val (arcCenter, x, y) = getSliceCenterPoints(
            DEFAULT_START_ANGLE,
            30f,
            size,
            padding
        )
        assertEquals(285f, arcCenter)
        assertTrue(x > 0)
        assertTrue(y > 0)
    }

    @Test
    fun `Calculated angle is in the correct range`() {
        val angle = convertTouchEventPointToAngle(200f, 200f, 10f, 10f)
        assertTrue(angle in 0.0..360.0)
    }
}
