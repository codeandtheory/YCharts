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
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.Gravity
import com.ygraph.components.axis.YAxis
import org.junit.Ignore
import org.junit.Rule

class YGraphTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Ignore("Need to fix as nodes are not visible for components drawn inside canvas")
    fun whenGraphAxisIsRenderedYaxisLabelShouldBeDisplayed(): Unit =
        with(composeTestRule) {
            // Given
            val axisData = AxisData.Builder()
                .ySteps(DEFAULT_AXIS_STEP_SIZE)
                .xAxisPos(Gravity.LEFT)
                .axisLabelFontSize(AXIS_LABEL_FONT_SIZE.sp)
                .yLabelData { index -> index.toString() }
                .build()

            // When
            setContent {
                YAxis(
                    modifier = Modifier.height(AXIS_HEIGHT.dp),
                    axisData = axisData
                )
            }

            // Then
            onNode(hasText("8")).assertIsDisplayed()
        }

    @Ignore("Need to fix as nodes are not visible for components drawn inside canvas")
    fun whenGivenYaxisLineNotRequiredIsConfiguredShouldNotBeVisible(): Unit =
        with(composeTestRule) {
            // Given
            val axisData = AxisData.Builder()
                .xAxisPos(Gravity.LEFT)
                .axisLabelFontSize(AXIS_LABEL_FONT_SIZE.sp)
                .axisConfig(AxisConfig(isAxisLineRequired = false))
                .yLabelData { index -> index.toString() }
                .build()

            // When
            setContent {
                YAxis(
                    modifier = Modifier.height(AXIS_HEIGHT.dp),
                    axisData = axisData
                )
            }

            // Then
            composeTestRule.onNodeWithText("8").assertIsNotDisplayed()
        }

    companion object {
        private const val AXIS_HEIGHT = 250
        private const val AXIS_LABEL_FONT_SIZE = 14
        private const val DEFAULT_AXIS_STEP_SIZE = 10
    }
}
