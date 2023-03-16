package co.yml.kmm.charts

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.kmm.charts.common.extensions.formatToSinglePrecision
import co.yml.kmm.charts.common.extensions.getLineChartData
import co.yml.kmm.charts.common.model.Point
import co.yml.kmm.charts.ui.barchart.BarChart
import co.yml.kmm.charts.ui.barchart.models.BarChartData
import co.yml.kmm.charts.ui.barchart.models.BarData
import co.yml.kmm.charts.ui.barchart.models.BarStyle
import co.yml.kmm.charts.ui.linechart.model.IntersectionPoint
import co.yml.kmm.charts.ui.linechart.model.LineStyle
import co.yml.kmm.charts.ui.linechart.model.LineType
import co.yml.kmm.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.kmm.charts.ui.wavechart.WaveChart
import co.yml.kmm.charts.ui.wavechart.model.Wave
import co.yml.kmm.charts.ui.wavechart.model.WaveChartData
import co.yml.kmm.charts.ui.wavechart.model.WavePlotData
import co.yml.kmm.charts.axis.AxisData
import kotlin.random.Random

@Composable
internal fun ChartScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        topBar = {
        })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.TopCenter
        ) {
            val pointsData = getLineChartData(100, -20, 80)
            val xAxisData = AxisData.Builder()
                .axisStepSize(40.dp)
                .steps(pointsData.size - 1)
                .labelData { i -> i.toString() }
                .axisLabelAngle(20f)
                .labelAndAxisLinePadding(15.dp)
                .axisLabelColor(Color.Blue)
                .axisLineColor(Color.DarkGray)
                .build()
            val yAxisData = AxisData.Builder()
                .steps(10)
                .labelData { i ->
                    // Add yMin to get the negative axis values to the scale
                    val yMin = pointsData.minOf { it.y }
                    val yMax = pointsData.maxOf { it.y }
                    val yScale = (yMax - yMin) / 10
                    ((i * yScale) + yMin).formatToSinglePrecision()
                }
                .labelAndAxisLinePadding(30.dp)
                .axisLabelColor(Color.Blue)
                .axisLineColor(Color.DarkGray)
                .build()
            val data = WaveChartData(
                wavePlotData = WavePlotData(
                    lines = listOf(
                        Wave(
                            dataPoints = pointsData,
                            waveStyle = LineStyle(lineType = LineType.SmoothCurve(), color = Color.Blue),
                            intersectionPoint = IntersectionPoint(color = Color.Red),
                            selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                                val xLabel = "x : ${x.toInt()} "
                                val yLabel = "y : ${y.formatToSinglePrecision().toString()}"
                                "$xLabel $yLabel"
                            })
                        )
                    )
                ),
                xAxisData = xAxisData,
                yAxisData = yAxisData
            )
            WaveChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                waveChartData = data
            )
        }
    }
}

@Composable
internal fun BarChartScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        topBar = {
        })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.TopCenter
        ) {
            val maxRange = 50
            val barData = getBarChartData(50, maxRange)
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
    }
}

/**
 * Return the sample bar chart data
 * @param listSize Size of the list
 * @param maxRange Maximum range for the values
 */
fun getBarChartData(listSize: Int, maxRange: Int): List<BarData> {
    val list = arrayListOf<BarData>()
    for (index in 0 until listSize) {
        list.add(
            BarData(
                Point(
                    index.toFloat(),
                    Random.nextDouble(1.0, maxRange.toDouble()).toFloat().formatToSinglePrecision().toFloat()
                ),
                Color(
                    Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                ),
                "Bar$index"
            )
        )
    }
    return list
}
