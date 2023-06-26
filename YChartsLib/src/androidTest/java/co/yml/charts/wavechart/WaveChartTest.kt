package co.yml.charts.wavechart

import android.graphics.Typeface
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.getMappingPointsToGraph
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.wavechart.WaveChart
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import co.yml.charts.ui.wavechart.model.WavePlotData
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WaveChartTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val pointsData = listOf(
        Point(0f, 0f),
        Point(1f, -3f),
        Point(2f, -5f),
        Point(3f, -2f),
        Point(4f, 2f),
        Point(5f, 5f),
        Point(6f, 4f),
        Point(7f, 0f),
        Point(8f, -1f),
        Point(9f, -3f),
        Point(10f, 0f),
    )
    private val steps = 10
    private val xAxisData = AxisData.Builder()
        .axisStepSize(40.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(Color.Red)
        .build()
    private val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelData { i ->
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }
        .axisLineColor(Color.Red)
        .labelAndAxisLinePadding(20.dp)
        .bottomPadding(15.dp)
        .build()
    private val data = WaveChartData(
        wavePlotData = WavePlotData(
            lines = listOf(
                Wave(
                    dataPoints = pointsData,
                    waveStyle = LineStyle(
                        lineType = LineType.SmoothCurve(isDotted = true),
                        color = Color.Green
                    ),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPoint = SelectionHighlightPoint(
                        color = Color.Green
                    ),
                    selectionHighlightPopUp = SelectionHighlightPopUp(
                        backgroundColor = Color.Black,
                        backgroundStyle = Stroke(2f),
                        labelColor = Color.Red,
                        labelTypeface = Typeface.DEFAULT_BOLD
                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )

    @Test
    fun assertIfWaveChartIsDisplayed() {
        composeTestRule.setContent {
            WaveChart(modifier = Modifier, waveChartData = data)
        }
        composeTestRule.onNodeWithTag("wave_chart").assertIsDisplayed()
    }

    @Test
    fun assertIfScrollableContainerIsDisplayed() {
        composeTestRule.setContent {
            WaveChart(modifier = Modifier, waveChartData = data)
        }
        composeTestRule.onAllNodesWithTag(testTag = "scrollable_container", useUnmergedTree = true)
            .onFirst().assertIsDisplayed()
    }

    @Test
    fun getMappingPointsToGraphTest() {
        val mappedPoints = getMappingPointsToGraph(
            pointsData, 0f, 1f, 0f, 0f, 0f, 10f, 1f
        )
        val expectedResult = mutableListOf<Offset>()
        pointsData.forEach {
            expectedResult.add(Offset(it.x, (10f - it.y)))
        }
        Assert.assertEquals(expectedResult, mappedPoints)
    }

}