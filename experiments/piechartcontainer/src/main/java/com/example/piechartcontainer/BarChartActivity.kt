package com.example.piechartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.common.utils.DataUtils.getGradientBarChartData

class BarChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val barData = getGradientBarChartData(50, 50)
                    val yStepSize = 10
                    val xAxisData = AxisData.Builder()
                        .axisStepSize(30.dp)
                        .steps(barData.size - 1)
                        .bottomPadding(40.dp)
                        .axisLabelAngle(20f)
                        .labelData { index -> barData[index].label }
                        .shouldAxisStartWithPadding(true)
                        .build()
                    val yAxisData = AxisData.Builder()
                        .steps(yStepSize)
                        .labelAndAxisLinePadding(20.dp)
                        .axisOffset(20.dp)
                        .labelData { index -> (index * yStepSize).toString() }
                        .build()
                    val barChartData = BarChartData(
                        chartData = barData,
                        xAxisData = xAxisData,
                        yAxisData = yAxisData,
                        paddingBetweenBars = 30.dp,
                        showYAxis = true,
                        showXAxis = true,
                        isGradientEnabled = true
                    )
                    BarChart(modifier = Modifier.height(600.dp), barChartData = barChartData)
                }
            }
        }
    }
}


