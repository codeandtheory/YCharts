package com.ygraph.components.axis

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class YGraphExtensionsTest {

    @Test
    fun `When getAxisInitValues extension is invoked should not return null values`() {
        // Given
        val yAxisData = YAxisData.Builder()
            .modifier(Modifier.height(300.dp))
            .yMaxValue(800f)
            .yStepValue(100f)
            .bottomPadding(10.dp)
            .axisPos(Gravity.LEFT)
            .axisLabelFontSize(14.sp)
            .yLabelData { index -> index.toString() }
            .build()

        // When
        val values = getAxisInitValues(
            yAxisData,
            300f,
            20f,
            20f
        )

        // Then
        assertNotNull(values.first)
        assertNotNull(values.second)
        assertNotNull(values.third)
    }

    @Test
    fun `Given yAxisData segmentHeight should be less than yaxisHeight `() {
        // Given
        val yAxisData = YAxisData.Builder()
            .modifier(Modifier.height(300.dp))
            .yMaxValue(800f)
            .yStepValue(100f)
            .bottomPadding(10.dp)
            .axisPos(Gravity.LEFT)
            .axisLabelFontSize(14.sp)
            .yLabelData { index -> index.toString() }
            .build()

        // When
        val values = getAxisInitValues(
            yAxisData,
            300f,
            20f,
            20f
        )

        // Then
        assertTrue(values.third < values.first)
    }
}
