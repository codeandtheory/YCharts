package com.app.ygraphs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.SelectionHighlightData
import com.ygraph.components.common.utils.DataUtils

class BarChartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YGraphsTheme.colors.background,
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
                                    0 -> BarChart1()
                                    1 -> BarChart2()
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
private fun BarChart1() {
    val maxRange = 50
    val barData = DataUtils.getBarChartData(50, maxRange)
    val yStepSize = 10
    val barChartData = BarChartData(
        chartData = barData, ySteps = yStepSize,
        paddingBetweenBars = 20.dp,
        barWidth = 25.dp,
        yLabelAndAxisLinePadding = 20.dp,
        yAxisOffset = 20.dp,
        yLabelData = { index -> (index * (maxRange / yStepSize)).toString() },
        xLabelData = { index -> barData[index].label },
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}

@Composable
private fun BarChart2() {
    val maxRange = 100
    val barData = DataUtils.getGradientBarChartData(50, 100)
    val yStepSize = 10
    val barChartData = BarChartData(
        chartData = barData, ySteps = yStepSize,
        paddingBetweenBars = 20.dp,
        barWidth = 35.dp,
        yLabelAndAxisLinePadding = 20.dp,
        yAxisOffset = 20.dp,
        xBottomPadding = 10.dp,
        yLabelData = { index -> (index * (maxRange / yStepSize)).toString() },
        xLabelData = { index -> barData[index].label },
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 20.dp,
        xLabelAngle = 20f,
        isGradientEnabled = true,
        selectionHighlightData = SelectionHighlightData(
            highlightBarColor = Color.Red,
            highlightTextBackgroundColor = Color.Green,
            popUpLabel = { _, y ->" Value : $y "  }
        )
    )
    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
}

