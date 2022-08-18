package com.example.barchartcontainer

import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import com.ygraph.components.axis.YAxisData
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.BarData
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

                    val barData = getBarChartData(50,50)
                    val yAxisData = YAxisData.Builder()
                        .modifier(Modifier.height(300.dp))
                        .yMaxValue(50f)
                        .yStepValue(5f)
                        .build()

                    val barChartData = BarChartData(
                        chartData = barData, yAxisData = yAxisData
                    )

                    BarChart(modifier = Modifier.height(300.dp), barChartData = barChartData)
                }
            }
        }
    }
}

private fun getBarChartData(listSize: Int, maxRange: Int): List<BarData> {
    val list = arrayListOf<BarData>()
    for (index in 0 until listSize) {
        list.add(
            BarData(
                index.toFloat(),
                Random.nextDouble(1.0, maxRange.toDouble()).toFloat(),
                Color(
                    Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                ),
                index.toString()
            )
        )
    }
    return list
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YGraphsTheme {
        Greeting("Android")
    }
}
