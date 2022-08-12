package com.ygraph.components.axis

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class YGraphExtensionsTest {

    @MockK
    private val yAxisData: YAxisData = mockk()

    @Test
    fun `When getAxisInitValues extension is invoked should not return values lesser than zero`() {
        // Given
        every { yAxisData.yMaxValue }.returns(1000f)
        every { yAxisData.yStepValue }.returns(100f)

        // When
        val values = getAxisInitValues(
            yAxisData,
            687f, // 250.dp.toPx()
            20f,
            20f
        )

        // Then
        assertTrue(values.first > 0)
        assertTrue(values.second > 0)
        assertTrue(values.third > 0)
    }

    @Test
    fun `Given yAxisData segmentHeight should be less than yaxisHeight `() {
        // Given
        every { yAxisData.yMaxValue }.returns(1000f)
        every { yAxisData.yStepValue }.returns(100f)

        // When
        val values = getAxisInitValues(
            yAxisData,
            687f, // 250.dp.toPx()
            20f,
            20f
        )

        // Then
        assertTrue(values.third < values.first)
    }

    @Test
    fun `Given yAxisData product of segmentHeight and reqYLabelsQuo should be less than yaxisHeight `() {
        // Given
        every { yAxisData.yMaxValue }.returns(1000f)
        every { yAxisData.yStepValue }.returns(100f)

        // When
        val values = getAxisInitValues(
            yAxisData,
            687f, // 250.dp.toPx()
            20f,
            20f
        )

        // Then
        assertTrue(values.first > values.second * values.third)
    }

    @Test
    fun `Given yMaxValue & yStepValue exact number of reqYLabelsQuo should be calculated`() {
        // Given
        val yAxisDatEven: YAxisData = mockk()
        every { yAxisDatEven.yMaxValue }.returns(800f)
        every { yAxisDatEven.yStepValue }.returns(100f)

        val yAxisDatOdd: YAxisData = mockk()
        every { yAxisDatOdd.yMaxValue }.returns(800f)
        every { yAxisDatOdd.yStepValue }.returns(90f)

        // When
        val valuesEven = getAxisInitValues(
            yAxisDatEven,
            687f, // 250.dp.toPx()
            20f,
            20f
        )
        val valuesOdd = getAxisInitValues(
            yAxisDatOdd,
            687f, // 250.dp.toPx()
            20f,
            20f
        )

        // Then
        assertEquals(valuesEven.second.toInt(), 9) // Total 9 steps should be visible
        assertEquals(valuesOdd.second.toInt(), 10) // Total 10 steps should be visible
    }
}
