package com.ygraph.components

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.AxisConfig
import com.ygraph.components.axis.Gravity
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.YAxisData
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class YGraphTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Ignore("Need to fix as nodes are not visible for components drawn inside canvas")
    fun whenGraphAxisIsRenderedYaxisLabelShouldBeDisplayed(): Unit =
        with(composeTestRule) {
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
            setContent {
                YAxis(yAxisData = yAxisData)
            }

            // Then
            onNode(hasText("8")).assertIsDisplayed()
        }

    @Ignore("Need to fix as nodes are not visible for components drawn inside canvas")
    fun whenGivenYaxisLineNotRequiredIsConfiguredShouldNotBeVisible(): Unit =
        with(composeTestRule) {
            // Given
            val yAxisData = YAxisData.Builder()
                .modifier(Modifier.height(300.dp))
                .yMaxValue(800f)
                .yStepValue(100f)
                .bottomPadding(10.dp)
                .axisPos(Gravity.LEFT)
                .axisLabelFontSize(14.sp)
                .axisConfig(AxisConfig(isAxisLineRequired = false))
                .yLabelData { index -> index.toString() }
                .build()

            // When
            setContent {
                YAxis(yAxisData = yAxisData)
            }

            // Then
            composeTestRule.onNodeWithText("8").assertIsNotDisplayed()
        }
}
