package co.yml.charts.barchart

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.StackedBarChart
import co.yml.charts.ui.barchart.models.*
import org.junit.Rule
import org.junit.Test

class StackedBarChartTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val barSize = 3
    private val listSize = 5
    private val groupBarData = getGroupBarChartTestData(listSize, barSize)
    private val yStepSize = 10

    private val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(listSize - 1)
        .startDrawPadding(48.dp)
        .labelData { index -> "C $index" }
        .build()

    private val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index ->
            val valueList = mutableListOf<Float>()
            groupBarData.map { groupBar ->
                var yMax = 0f
                groupBar.barList.forEach {
                    yMax += it.point.y
                }
                valueList.add(yMax)
            }
            val maxElementInYAxis = getMaxElementInYAxis(valueList.maxOrNull() ?: 0f, yStepSize)

            (index * (maxElementInYAxis / yStepSize)).toString()
        }
        .topPadding(36.dp)
        .build()

    private val colorPaletteList = listOf(Color.Black, Color.Yellow, Color.Blue)
    val legendsConfig = LegendsConfig(
        legendLabelList = DataUtils.getLegendsLabelData(colorPaletteList),
        gridColumnCount = 3
    )
    private val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(
            barWidth = 35.dp,
            selectionHighlightData = SelectionHighlightData(
                isHighlightFullBar = true,
                groupBarPopUpLabel = { name, value ->
                    "Name"
                }
            )
        ),
        barColorPaletteList = colorPaletteList
    )
    private val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        paddingBetweenStackedBars = 4.dp
    )

    @Test
    fun assertIfStackedBarChartIsDisplayed() {

        startStackedBarChart()
        composeTestRule.onNodeWithTag("stacked_bar_chart", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun assertIfScrollableContainerIsDisplayed() {
        startStackedBarChart()

        composeTestRule.onNodeWithTag("scrollable_container").assertIsDisplayed()
    }

    @Test
    fun assertIfCanvasIsDisplayed() {
        startStackedBarChart()

        composeTestRule.onNodeWithTag("chart_canvas", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun assertIfXAxisIsDisplayed() {
        startStackedBarChart()

        composeTestRule.onNodeWithTag("x_axis", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun assertIfYAxisIsDisplayed() {
        startStackedBarChart()

        composeTestRule.onNodeWithTag("y_axis", useUnmergedTree = true)
            .assertExists()
    }


    @Test
    fun assertIfStackedBarClicked() {
        startStackedBarChart()

        composeTestRule.onNode(hasAnyChild(hasClickAction()), useUnmergedTree = true)
            .onChildren()
            .onFirst()
            .performClick()
    }


    private fun startStackedBarChart() {
        composeTestRule.setContent {
            StackedBarChart(modifier = Modifier, groupBarChartData = groupBarChartData)
        }
    }
}


fun getGroupBarChartTestData(listSize: Int, barSize: Int): List<GroupBar> {
    val list = mutableListOf<GroupBar>()
    for (index in 0 until listSize) {
        val barList = mutableListOf<BarData>()
        for (i in 0 until barSize) {
            val barValue = (((i + 1) * 10) + ((index + 1) * 10)).toFloat()
            barList.add(
                BarData(
                    Point(
                        index.toFloat(),
                        barValue
                    ),
                    label = "B$i",
                    description = "Bar at $index with label B$i has value ${
                        String.format(
                            "%.2f", barValue
                        )
                    }"
                )
            )
        }
        list.add(GroupBar(index.toString(), barList))
    }
    return list
}