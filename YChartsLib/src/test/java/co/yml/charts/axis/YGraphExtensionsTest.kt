package co.yml.charts.axis

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class YGraphExtensionsTest {

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
}
