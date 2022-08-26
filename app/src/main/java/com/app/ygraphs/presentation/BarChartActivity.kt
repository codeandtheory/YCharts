package com.app.ygraphs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
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
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        BarChart1()
                    }
                }
            }
        }
    }
}

@Composable
private fun BarChart1() {
    val barData = DataUtils.getBarChartData(50, 50)
    val yStepSize = 10
    val barChartData = BarChartData(
        chartData = barData, yStepSize = yStepSize,
        paddingBetweenBars = 30.dp,
        yLabelAndAxisLinePadding = 20.dp,
        yAxisOffset = 20.dp,
        yLabelData = { index -> (index * yStepSize).toString() },
        xLabelData = { index -> barData[index].label },
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
        xLabelAngle = 20f
    )
    BarChart(modifier = Modifier.height(600.dp), barChartData = barChartData)
}

