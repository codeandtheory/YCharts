package com.ygraph.components

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.Gravity
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.YAxisData
import org.junit.Rule
import org.junit.Test

class YGraphTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `When graph Yaxis is rendered yaxis label should be displayed`() {
        composeTestRule.setContent {
            val yAxisData = YAxisData.Builder()
                .modifier(Modifier.height(300.dp))
                .yMaxValue(800f)
                .yStepValue(100f)
                .bottomPadding(10.dp)
                .axisPos(Gravity.LEFT)
                .axisLabelFontSize(14.sp)
                .yLabelData { index -> index.toString() }
                .build()
            YAxis(yAxisData = yAxisData)
        }
        composeTestRule.onNodeWithText("800").assertIsDisplayed()
    }
}
