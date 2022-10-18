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
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.Gravity
import co.yml.charts.axis.YAxis
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
                .steps(DEFAULT_AXIS_STEP_SIZE)
                .axisPosition(Gravity.LEFT)
                .axisLabelFontSize(AXIS_LABEL_FONT_SIZE.sp)
                .labelData { index -> index.toString() }
                .build()

            // When
            setContent {
                YAxis(modifier = Modifier.height(AXIS_HEIGHT.dp), yAxisData = axisData)
            }

            // Then
            onNode(hasText("8")).assertIsDisplayed()
        }

    @Ignore("Need to fix as nodes are not visible for components drawn inside canvas")
    fun whenGivenYaxisLineNotRequiredIsConfiguredShouldNotBeVisible(): Unit =
        with(composeTestRule) {
            // Given
            val axisData = co.yml.charts.axis.AxisData.Builder()
                .axisPosition(Gravity.LEFT)
                .axisLabelFontSize(AXIS_LABEL_FONT_SIZE.sp)
                .axisConfig(co.yml.charts.axis.AxisConfig(isAxisLineRequired = false))
                .labelData { index -> index.toString() }
                .build()

            // When
            setContent {
                YAxis(
                    modifier = Modifier.height(AXIS_HEIGHT.dp),
                    yAxisData = axisData
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
