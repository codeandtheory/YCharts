package com.example.barchartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.barchartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.common.datautils.getBarChartData
import com.ygraph.components.common.model.Point
import kotlin.random.Random

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
                    val barChartData = BarChartData(
                        chartData = barData, yStepSize = 10,
                        yLabelAndAxisLinePadding = 20.dp,
                        yAxisOffset = 20.dp,
                        yLabelData = { index -> (index * 10).toString() },
                        xLabelData = { index -> index.toString() }
                    )
                    BarChart(modifier = Modifier.height(600.dp), barChartData = barChartData)
                }
            }
        }
    }
}



