package co.yml.ycharts.app.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisConfig
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.ycharts.app.R
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme

/**
 * Line chart activity
 *
 * @constructor Create empty Line chart activity
 */
class LineChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_line_chart),
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

                                SingleLineChartWithGridLines(
                                    DataUtils.getLineChartDataValues(),
                                    DataUtils.getLineChart2DataValues()
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Divider(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp))
                            }
                        })
                    }
                }
            }
        }
    }
}

/**
 * Single line chart with grid lines
 *
 * @param pointsData
 */
@Composable
private fun SingleLineChartWithGridLines(pointsData: List<Point>, pointsData2: List<Point>) {
    val steps = 5

    val xAxisData = AxisData.Builder()
        .axisStepSize(75.dp)
        .topPadding(155.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> pointsData[i].xLable.toString() }
        .labelAndAxisLinePadding(15.dp)
        .indicatorLineWidth(0.dp)
        .axisLabelFontSize(10.sp)
        .axisLineThickness(1.dp)
        .startPadding(50.dp)
//        .axisPosition(co.yml.charts.axis.Gravity.RIGHT)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .axisConfig(AxisConfig(isAxisLineRequired = false))
        .labelAndAxisLinePadding(20.dp)
        .axisLabelFontSize(10.sp)
        .axisLineThickness(1.dp)
        .backgroundColor(Color.Transparent)
        .labelData { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(color = Color(0xFF2096F3)),
                    IntersectionPoint(color = Color(0xFF1C7ECC)),
                    null,
                    null,
                    SelectionHighlightPopUp()
                ),

                Line(
                    dataPoints = pointsData2,
                    LineStyle(color = Color(0xFF2096F3)),
                    IntersectionPoint(color = Color(0xFF1C7ECC)),
                    null,
                    null,
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = null,
        isZoomAllowed = false,
        backgroundColor = MaterialTheme.colors.background
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = data
    )
}


@Composable
private fun MultipleToneLinechart(pointsData: List<Point>) {
    val xAxisData = AxisData.Builder()
        .axisStepSize(40.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> (1900 + i).toString() }
        .axisLabelAngle(20f)
        .labelAndAxisLinePadding(15.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.DarkGray)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(10)
        .labelData { i -> "${(i * 20)}k" }
        .labelAndAxisLinePadding(30.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.DarkGray)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(lineType = LineType.Straight(), color = Color.Blue),
                    intersectionPoint = IntersectionPoint(color = Color.Red),
                    selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                        val xLabel = "x : ${(1900 + x).toInt()} "
                        val yLabel = "y : ${String.format("%.2f", y)}"
                        "$xLabel $yLabel"
                    })
                ), Line(
                    dataPoints = pointsData.subList(0, 10),
                    lineStyle = LineStyle(lineType = LineType.Straight(), color = Color.Magenta),
                    intersectionPoint = IntersectionPoint(color = Color.Red),
                    selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                        val xLabel = "x : ${(1900 + x).toInt()} "
                        val yLabel = "y : ${String.format("%.2f", y)}"
                        "$xLabel $yLabel"
                    })
                ), Line(
                    dataPoints = pointsData.subList(15, 30),
                    lineStyle = LineStyle(lineType = LineType.Straight(), color = Color.Yellow),
                    intersectionPoint = IntersectionPoint(color = Color.Red),
                    selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                        val xLabel = "x : ${(1900 + x).toInt()} "
                        val yLabel = "y : ${String.format("%.2f", y)}"
                        "$xLabel $yLabel"
                    })
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = data
    )
}


