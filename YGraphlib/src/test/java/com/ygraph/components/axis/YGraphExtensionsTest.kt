package com.ygraph.components.axis

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mockk.every
import io.mockk.mockk
import  org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class YGraphExtensionsTest {

    @Test
    fun `When getAxisInitValues extension is invoked should not return null values`() {
        val drawScope: DrawScope = mockk()
        every { drawScope.size }.returns(Size(600f, 800f))
        val yAxisData = YAxisData.Builder()
            .modifier(Modifier.height(300.dp))
            .yMaxValue(800f)
            .yStepValue(100f)
            .bottomPadding(10.dp)
            .axisPos(Gravity.LEFT)
            .axisLabelFontSize(14.sp)
            .yLabelData { index -> index.toString() }
            .build()
        val values = drawScope.getAxisInitValues(yAxisData)
        assertNotNull(values.first)
        assertNotNull(values.second)
        assertNotNull(values.third)
    }

    @Test
    fun `Given yAxisData segmentHeight should be less than yaxisHeight `() {
        val drawScope: DrawScope = mockk()
        every { drawScope.size }.returns(Size(600f, 800f))
        val yAxisData = YAxisData.Builder()
            .modifier(Modifier.height(300.dp))
            .yMaxValue(800f)
            .yStepValue(100f)
            .bottomPadding(10.dp)
            .axisPos(Gravity.LEFT)
            .axisLabelFontSize(14.sp)
            .yLabelData { index -> index.toString() }
            .build()
        val values = drawScope.getAxisInitValues(yAxisData)
        assertTrue(values.third < values.first)
    }
}
