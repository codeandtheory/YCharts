package com.app.chartcontainer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.app.chartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.axis.XAxis
import com.ygraph.components.axis.YAxis
import com.ygraph.components.common.model.Point
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Charts")
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(), onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                LineChartActivity::class.java
                            )
                        )
                    }) {
                        Text(text = "Line Chart")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    ScrollableContainer()
                }
            }
        }
    }
}

@Composable
private fun ScrollableContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val columnWidth = remember { mutableStateOf(0f) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .steps(10)
                .labelData { i -> i.toString() }
                .build()
            val yAxisData = AxisData.Builder()
                .steps(5)
                .labelData { i -> (i * 20).toString() }
                .build()
            ScrollableCanvasContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                containerBackgroundColor = Color.Yellow,
                calculateMaxDistance = { xZoom -> size.width * 3 }, // T0D0 need to calculate
                drawXAndYAxis = { scrollOffset, xZoom ->
                    YAxis(
                        modifier = Modifier
                            .height(250.dp)
                            .onGloballyPositioned {
                                columnWidth.value = it.size.width.toFloat()
                            },
                        yAxisData = yAxisData
                    )
                    XAxis(
                        xAxisData = xAxisData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.BottomStart),
                        xStart = columnWidth.value,
                        scrollOffset = scrollOffset,
                        zoomScale = xZoom,
                        chartData = getLineChartData(100, 100),
                        xLineStart = 0f
                    )
                },
                onDraw = { _, _ ->
                    // T0D0 draw any type of graph here
                }
            )
        }
    }
}


// T0D0 pass data through the graph component
private fun getLineChartData(listSize: Int, maxRange: Int): List<Point> {
    val list = arrayListOf<Point>()
    for (index in 0..listSize) {
        list.add(
            Point(
                index.toFloat(),
                Random.nextDouble(1.0, maxRange.toDouble()).toFloat()
            )
        )
    }
    return list
}
