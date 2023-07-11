@file:OptIn(ExperimentalTextApi::class, ExperimentalTextApi::class)

package co.yml.kmm.charts

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.kmm.charts.axis.AxisData
import co.yml.kmm.charts.common.components.Legends
import co.yml.kmm.charts.common.extensions.formatToSinglePrecision
import co.yml.kmm.charts.common.extensions.getLineChartData
import co.yml.kmm.charts.common.model.LegendLabel
import co.yml.kmm.charts.common.model.LegendsConfig
import co.yml.kmm.charts.common.model.PlotType
import co.yml.kmm.charts.common.model.Point
import co.yml.kmm.charts.common.utils.DataUtils
import co.yml.kmm.charts.ui.barchart.BarChart
import co.yml.kmm.charts.ui.barchart.GroupBarChart
import co.yml.kmm.charts.ui.barchart.models.BarChartData
import co.yml.kmm.charts.ui.barchart.models.BarData
import co.yml.kmm.charts.ui.barchart.models.BarPlotData
import co.yml.kmm.charts.ui.barchart.models.BarStyle
import co.yml.kmm.charts.ui.barchart.models.GroupBarChartData
import co.yml.kmm.charts.ui.barchart.models.GroupSeparatorConfig
import co.yml.kmm.charts.ui.bubblechart.BubbleChart
import co.yml.kmm.charts.ui.bubblechart.model.BubbleChartData
import co.yml.kmm.charts.ui.linechart.LineChart
import co.yml.kmm.charts.ui.linechart.model.*
import co.yml.kmm.charts.ui.piechart.charts.DonutPieChart
import co.yml.kmm.charts.ui.piechart.charts.PieChart
import co.yml.kmm.charts.ui.piechart.models.PieChartConfig
import co.yml.kmm.charts.ui.piechart.models.PieChartData
import co.yml.kmm.charts.ui.piechart.utils.proportion
import co.yml.kmm.charts.ui.wavechart.WaveChart
import co.yml.kmm.charts.ui.wavechart.model.Wave
import co.yml.kmm.charts.ui.wavechart.model.WaveChartData
import co.yml.kmm.charts.ui.wavechart.model.WavePlotData
import kotlin.random.Random

@Composable
internal fun WaveChartScreen() {
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
                                val yLabel = "y : ${y.formatToSinglePrecision()}"
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
            Column {
            BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
            VerticalGroupBarChart()
            }

        }
    }
}

@Composable
internal fun VerticalGroupBarChart() {
    val maxRange = 100
    val barSize = 3
    val groupBarData = DataUtils.getGroupBarChartData(50, maxRange, barSize)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .bottomPadding(5.dp)
        .startDrawPadding(48.dp)
        .labelData { index -> index.toString() }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val colorPaletteList = DataUtils.getColorPaletteList(barSize)
    val legendsConfig = LegendsConfig(
        legendLabelList = DataUtils.getLegendsLabelData(colorPaletteList),
        gridColumnCount = 3
    )
    val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(barWidth = 35.dp),
        barColorPaletteList = colorPaletteList
    )
    val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        groupSeparatorConfig = GroupSeparatorConfig(0.dp)
    )
    Column(
        Modifier
            .height(450.dp)
    ) {
        GroupBarChart(
            modifier = Modifier
                .height(400.dp),
            groupBarChartData = groupBarChartData
        )
        Legends(
            legendsConfig = legendsConfig
        )
    }
}

@Composable
internal fun LineChartScreen() {
    val pointsData =  getLineChartData(100, start = 0, maxRange = 100)
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
            val steps = 5
            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .steps(pointsData.size - 1)
                .labelData { i -> i.toString() }
                .labelAndAxisLinePadding(15.dp)
                .build()
            val yAxisData = AxisData.Builder()
                .steps(steps)
                .labelAndAxisLinePadding(30.dp)
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
                            LineStyle(lineType = LineType.Straight()),
                            IntersectionPoint(),
                            SelectionHighlightPoint(),
                            ShadowUnderLine(),
                            SelectionHighlightPopUp()
                        )
                    )
                ),
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                gridLines = GridLines()
            )
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                lineChartData = data
            )
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

/**
 * Returns sample pie chart data
 */
fun getPieChartData(): PieChartData {
    return PieChartData(
        slices = listOf(
            PieChartData.Slice("Android", 30f, Color(0xFF002B5B)),
            PieChartData.Slice("iOS", 30f, Color(0xFF2B4865)),
            PieChartData.Slice("Windows", 15f, Color(0xFF256D85)),
            PieChartData.Slice("Other", 25f, Color(0xFF806D85)),
        ),
        plotType = PlotType.Pie
    )
}

/**
 * Returns the legends config for given pie chart data
 * @param pieChartData:  Pie chart details.
 * @param gridSize: Legends grid size.
 */
fun getLegendsConfigFromPieChartData(pieChartData: PieChartData, gridSize: Int): LegendsConfig {
    val legendsList = mutableListOf<LegendLabel>()
    pieChartData.slices.forEach { slice ->
        legendsList.add(LegendLabel(slice.color, slice.label))
    }
    return LegendsConfig(
        legendLabelList = legendsList,
        gridColumnCount = gridSize,
        legendsArrangement = Arrangement.Start,
        textStyle = TextStyle()
    )
}

/**
 * Returns sample donut chart data
 */
fun getDonutChartData(): PieChartData {
    return PieChartData(
        slices = listOf(
            PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
            PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
            PieChartData.Slice("Lenovo", 10f, Color(0xFFA40606)),
            PieChartData.Slice("Asus", 15f, Color(0xFFF53844)),
            PieChartData.Slice("Acer", 10f, Color(0xFFEC9F05)),
            PieChartData.Slice("Apple", 30f, Color(0xFF009FFD)),
        ),
        plotType = PlotType.Donut
    )
}


@Composable
internal fun PieChartScreen()  {
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

                val pieChartData = getPieChartData()

                val pieChartConfig =
                    PieChartConfig(
                        activeSliceAlpha = .9f,
                        isEllipsizeEnabled = true,
                        isAnimationEnable = true,
                        chartPadding = 20,
                        showSliceLabels = true,
                        percentVisible = true
                    )
                Column(modifier = Modifier.height(500.dp)) {
                    Legends(legendsConfig = getLegendsConfigFromPieChartData(pieChartData, 3))
                    PieChart(
                        modifier = Modifier
                            .width(400.dp)
                            .height(400.dp),
                        pieChartData,
                        pieChartConfig
                    ) { slice ->
                    }
                }
            }

        }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun DonutPieChartScreen()  {
    val data = getDonutChartData()
    // Sum of all the values
    val sumOfValues = data.totalLength

    // Calculate each proportion value
    val proportions = data.slices.proportion(sumOfValues)
    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 120f,
            percentColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            isAnimationEnable = true,
            chartPadding = 25,
            percentageFontSize = 42.sp
        )
    Column {
        Legends(legendsConfig = getLegendsConfigFromPieChartData(pieChartData = data, 3))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            data,
            pieChartConfig
        ) { slice ->

        }
    }
}

/**
 * Bubble chart with grid lines
 *
 * @param pointsData
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun BubbleChartWithGrid() {
    val pointsData = DataUtils.getRandomPoints(50,0,30)
    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .startDrawPadding(55.dp)
        .startDrawPadding(20.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(50.dp)
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
