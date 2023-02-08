@file:OptIn(ExperimentalMaterialApi::class)

package com.example.piechartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.common.utils.DataUtils.getGroupBarChartData
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.models.*
import com.example.piechartcontainer.ui.theme.YChartsTheme

class BarChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colors.background),

                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            items(3) { item ->
                                when (item) {
//                                    0 -> BarChartGroup()
                                    0 -> HorizontalBarChart()
                                    1 -> VerticalBarChart()
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
private fun BarChartGroup() {
    val barSize = 3
    val groupBarData = getGroupBarChartData(50, 50, barSize)
    val colorPaletteList = DataUtils.getColorPaletteList(barSize)
    val groupBarPlotData = BarPlotData(
        groupBarList = groupBarData,
        barColorPaletteList = colorPaletteList
    )
    val yStepSize = 10
    val xAxisData = co.yml.charts.axis.AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(groupBarData.size - 1)
        .bottomPadding(10.dp)
        .labelData { index -> groupBarData[index].label }
        .build()
    val yAxisData = co.yml.charts.axis.AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (50 / yStepSize)).toString() }
        .build()
    val legendsConfig = LegendsConfig(
        DataUtils.getLegendsLabelData(colorPaletteList),
        gridColumnCount = 3
    )
    val groupBarChartData = GroupBarChartData(
        barPlotData = groupBarPlotData,
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
//    Column {
    GroupBarChart(
        modifier = Modifier
            .height(350.dp),
        groupBarChartData = groupBarChartData
    )
//        Legends(legendsConfig = legendsConfig)
//    }
}

/**
 * Todo
 * 1. axisLabelAngle not working in Y axis
 *
 */
@Composable
private fun HorizontalBarChart() {
    val maxRange = 10
    val barData =
        DataUtils.getBarChartData(10, maxRange, BarChartType.HORIZONTAL)
    val xStepSize = 10

    val xAxisData = AxisData.Builder()
        .steps(xStepSize)
        .bottomPadding(40.dp)
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
        .labelData { index -> barData[index].label }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
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
private fun VerticalBarChart() {
    val maxRange = 10
    val barData =
        DataUtils.getBarChartData(10, maxRange, BarChartType.VERTICAL)
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
        .bottomPadding(40.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(paddingBetweenBars = 20.dp,
            barWidth = 35.dp,
            isGradientEnabled = false,
            selectionHighlightData = SelectionHighlightData(
                highlightBarColor = Color.Yellow,
                highlightTextBackgroundColor = Color.Green,
                popUpLabel = { _, y -> " Value : $y " }
            )),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp,
        barChartType = BarChartType.VERTICAL
    )
    BarChart(
        modifier = Modifier.height(350.dp),
        barChartData = barChartData
    )
}