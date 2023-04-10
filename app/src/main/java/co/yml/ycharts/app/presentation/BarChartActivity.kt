package co.yml.ycharts.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.SelectionHighlightData
import co.yml.charts.common.utils.DataUtils
import co.yml.ycharts.app.R
import kotlin.random.Random

/**
 * Bar chart activity
 *
 * @constructor Create empty Bar chart activity
 */
class BarChartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_bar_chart),
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
                            items(3) { item ->
                                when (item) {
                                    0 ->{   Text(
                                        modifier=Modifier.padding(12.dp),
                                        text = getString(R.string.barchart_solid_colors),
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                     BarchartWithSolidBars()
                                    }
                                    1 -> { Text(
                                        modifier=Modifier.padding(12.dp),
                                        text = getString(R.string.barchart_gradient_colors),
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                        BarchartWithGradientBars()}
                                    2 ->{
                                        Text(
                                            modifier=Modifier.padding(12.dp),
                                            text = getString(R.string.barchart_background_color),
                                            style = MaterialTheme.typography.subtitle1,
                                            fontWeight = FontWeight.Bold
                                        )
                                        BarchartWithBackgroundColor()
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

/**
 * Barchart with solid bars
 *
 */
@Composable
private fun BarchartWithSolidBars() {
    val maxRange = 50
    val barData = DataUtils.getBarChartData(50, maxRange)
    val yStepSize = 10

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barData[index].label }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 20.dp,
            barWidth = 25.dp
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}

/**
 * Barchart with gradient bars
 *
 */
@Composable
private fun BarchartWithGradientBars() {
    val maxRange = 100
    val barData = DataUtils.getGradientBarChartData(50, 100)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barData[index].label }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            isGradientEnabled = true,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Red,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { _, y -> " Value : $y " }
            )),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}

/**
 * Barchart with background color
 *
 */
@Composable
private fun BarchartWithBackgroundColor() {
    val maxRange = 100
    val backgroundColor = Color.LightGray
    val barData = DataUtils.getBarChartData(50, 100)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barData[index].label }
        .backgroundColor(backgroundColor)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .backgroundColor(backgroundColor)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Red,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { _, y -> " Value : $y " }
            )),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp,
        backgroundColor = backgroundColor
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}

