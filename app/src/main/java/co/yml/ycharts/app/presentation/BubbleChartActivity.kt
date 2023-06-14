package co.yml.ycharts.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.bubblechart.BubbleChart
import co.yml.charts.ui.bubblechart.model.BubbleChartData
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.ycharts.app.R
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme

/**
 * Line chart activity
 *
 * @constructor Create empty Line chart activity
 */
class BubbleChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.bubble_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            item {
                                Text(
                                    modifier = Modifier.padding(12.dp),
                                    text = getString(R.string.gradient_bubble_chart),
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Bold
                                )
                                BubbleChartWithGrid(
                                    pointsData = DataUtils.getRandomPoints(
                                        200,
                                        start = -50,
                                        maxRange = 50
                                    )
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                            item {
                                Text(
                                    modifier = Modifier.padding(12.dp),
                                    text = getString(R.string.solid_bubble_chart),
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Bold
                                )
                                SolidBubbleChart(
                                    pointsData = DataUtils.getRandomPoints(
                                        200,
                                        start = -50,
                                        maxRange = 50
                                    )
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                        })
                    }
                }
            }
        }
    }
}

/**
 * Bubble chart with grid lines
 *
 * @param pointsData
 */
@Composable
private fun BubbleChartWithGrid(pointsData: List<Point>) {
    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()

    val data = BubbleChartData(
        DataUtils.getBubbleChartDataWithGradientStyle(pointsData),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )

    BubbleChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        bubbleChartData = data
    )

}


/**
 * Bubble chart with grid lines
 *
 * @param pointsData
 */
@Composable
private fun SolidBubbleChart(pointsData: List<Point>) {
    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()

    val data = BubbleChartData(
        DataUtils.getBubbleChartDataWithSolidStyle(pointsData),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )

    BubbleChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        bubbleChartData = data
    )

}