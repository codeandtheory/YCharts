package co.yml.ycharts.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.components.Legends
import co.yml.charts.common.extensions.getMaxElementInYAxis
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.StackedBarChart
import co.yml.charts.ui.barchart.models.*
import co.yml.ycharts.app.R
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme

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
                            .fillMaxWidth()
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            items(4) { item ->
                                when (item) {
                                    0 -> VerticalBarChart()
                                    1 -> HorizontalBarChart()
                                    2 -> VerticalGroupBarChart()
                                    3 -> VerticalStackedBarChart()
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun VerticalBarChart() {
    val maxRange = 50
    val barData =
        DataUtils.getBarChartData(50, maxRange, BarChartType.VERTICAL, DataCategoryOptions())
    val yStepSize = 10

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(12.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(48.dp)
        .shouldDrawAxisLineTillEnd(false)
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
        drawBar = { drawScope, barData, drawOffset, height, barChartType, barStyle ->
            with(drawScope) {
                with(barStyle) {
                    drawRect(
                        color = barData.color,
                        topLeft = drawOffset,
                        size = if (barChartType == BarChartType.VERTICAL) Size(
                            barWidth.toPx(),
                            height
                        ) else Size(height, barWidth.toPx()),
                        style = barDrawStyle,
                        blendMode = barBlendMode
                    )
                }
            }
        }
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}

@Composable
private fun HorizontalBarChart() {
    val maxRange = 30
    val barData =
        DataUtils.getBarChartData(
            10,
            maxRange,
            BarChartType.HORIZONTAL,
            DataCategoryOptions(isDataCategoryInYAxis = true)
        )
    val xStepSize = 10

    val xAxisData = AxisData.Builder()
        .steps(xStepSize)
        .bottomPadding(12.dp)
        .endPadding(40.dp)
        .labelData { index -> (index * (maxRange / xStepSize)).toString() }
        .build()
    val yAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .setDataCategoryOptions(
            DataCategoryOptions(
                isDataCategoryInYAxis = true,
                isDataCategoryStartFromBottom = false
            )
        )
        .startDrawPadding(48.dp)
        .labelData { index -> barData[index].label }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            isGradientEnabled = false,
            paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Red,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { x, _ -> " Value : $x " },
                barChartType = BarChartType.HORIZONTAL
            ),
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp,
        barChartType = BarChartType.HORIZONTAL
    )
    BarChart(
        modifier = Modifier.height(350.dp),
        barChartData = barChartData
    )
}

@Composable
fun VerticalGroupBarChart() {
    val maxRange = 100
    val barSize = 3
    val groupBarData = DataUtils.getGroupBarChartData(50, maxRange, barSize)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .bottomPadding(5.dp)
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
fun VerticalStackedBarChart() {
    val barSize = 3
    val listSize = 10
    val groupBarData = DataUtils.getGroupBarChartData(listSize, 100, barSize)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(listSize - 1)
        .startDrawPadding(48.dp)
        .labelData { index -> "C $index" }
        .build()
    val yAxisData = AxisData.Builder()
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
    val colorPaletteList = DataUtils.getColorPaletteList(barSize)
    val legendsConfig = LegendsConfig(
        legendLabelList = DataUtils.getLegendsLabelData(colorPaletteList),
        gridColumnCount = 3
    )
    val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(
            barWidth = 35.dp
        ),
        barColorPaletteList = colorPaletteList
    )
    val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        paddingBetweenStackedBars = 4.dp,
        drawBar = { drawScope, barChartData, barStyle, drawOffset, height, barIndex ->
            with(drawScope) {
                drawRect(
                    color = colorPaletteList[barIndex],
                    topLeft = drawOffset,
                    size = Size(barStyle.barWidth.toPx(), height),
                    style = barStyle.barDrawStyle,
                    blendMode = barStyle.barBlendMode
                )
            }
        }
    )
    Column(
        Modifier
            .height(500.dp)
    ) {
        StackedBarChart(
            modifier = Modifier
                .height(400.dp),
            groupBarChartData = groupBarChartData
        )
        Legends(
            legendsConfig = legendsConfig
        )
    }
}