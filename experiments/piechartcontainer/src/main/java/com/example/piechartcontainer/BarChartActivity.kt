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
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.common.utils.DataUtils.getBarChartData
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
                    val barChartData = BarChartData(
                        chartData = barData, ySteps = yStepSize,
                        paddingBetweenBars = 30.dp,
                        yLabelAndAxisLinePadding = 20.dp,
                        yAxisOffset = 20.dp,
                        yLabelData = { index -> (index * yStepSize).toString() },
                        xLabelData = { index -> barData[index].label },
                        showYAxis = true,
                        showXAxis = true,
                        horizontalExtraSpace = 10.dp,
                        xLabelAngle = 20f,
                        isGradientEnabled = true
                    )
                    BarChart(modifier = Modifier.height(600.dp), barChartData = barChartData)
                }
            }
        }
    }
}


