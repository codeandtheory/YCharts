package com.ygraph.components.piechart.charts

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ygraph.components.common.model.PlotType
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData
import org.junit.Rule
import org.junit.Test


class PieChartTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val pieChartConfig = PieChartConfig(
        percentVisible = false,
        strokeWidth = 120f,
        percentColor = Color.Black
    )
    private val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("A", 15f, Color(0xFF58BDFF)),
            PieChartData.Slice("B", 35f, Color(0xFF125B7F)),
            PieChartData.Slice("C", 40f, Color(0xFF092D40)),
        ), plotType = PlotType.Pie
    )


    @Test
    fun whenIsLegendVisibleIsTrueLegendLabelsAreVisible() {
        composeTestRule.setContent {
            PieChart(
                modifier = Modifier,
                pieChartData = pieChartData,
                pieChartConfig = pieChartConfig
            )
        }
        composeTestRule.onNodeWithText("A").assertIsDisplayed()
        composeTestRule.onNodeWithText("B").assertIsDisplayed()
        composeTestRule.onNodeWithText("C").assertIsDisplayed()
    }

    @Test
    fun whenIsLegendVisibleIsFalseNoLegendLabelsAreShown() {
        composeTestRule.setContent {
            PieChart(
                modifier = Modifier, pieChartData = pieChartData,
                pieChartConfig = pieChartConfig
            )
        }
        composeTestRule.onNodeWithText("A").assertDoesNotExist()
        composeTestRule.onNodeWithText("B").assertDoesNotExist()
        composeTestRule.onNodeWithText("C").assertDoesNotExist()
    }
}
