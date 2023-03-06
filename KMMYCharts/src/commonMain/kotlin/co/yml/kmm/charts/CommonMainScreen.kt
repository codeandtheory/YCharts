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
import co.yml.kmm.charts.ui.linechart.model.IntersectionPoint
import co.yml.kmm.charts.ui.linechart.model.LineStyle
import co.yml.kmm.charts.ui.linechart.model.LineType
import co.yml.kmm.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.kmm.charts.ui.wavechart.WaveChart
import co.yml.kmm.charts.ui.wavechart.model.Wave
import co.yml.kmm.charts.ui.wavechart.model.WaveChartData
import co.yml.kmm.charts.ui.wavechart.model.WavePlotData
import com.example.kmmcocoapods.axis.AxisData

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
