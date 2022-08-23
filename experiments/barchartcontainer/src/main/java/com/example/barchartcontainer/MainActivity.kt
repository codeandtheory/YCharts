package com.example.barchartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.barchartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisConfig
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.common.datautils.getBarChartData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val barData = getBarChartData(50, 50)
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
                    )
                    BarChart(modifier = Modifier.height(600.dp), barChartData = barChartData)
                }
            }
        }
    }
}



