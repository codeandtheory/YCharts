package com.app.chartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Yellow)
                ) {
                    val columnWidth = remember { mutableStateOf(0f) }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        val axisData = AxisData.Builder()
                            .yMaxValue(100f)
                            .yStepValue(20f)
                            .yBottomPadding(32.5.dp)
                            .axisLabelFontSize(14.sp)
                            .yLabelData { index -> index.toString() }
                            .xLabelData { index -> index.toString() }
                            .yLabelAndAxisLinePadding(20.dp)
                            .yAxisOffset(20.dp)
                            .yLabelData { index -> "$index" }
                            .build()
                        ScrollableCanvasContainer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            calculateMaxDistance = { xZoom -> size.width * 3 }, // T0D0 need to calculate
                            drawXAndYAxis = { scrollOffset, xZoom ->
                                YAxis(
                                    modifier = Modifier
                                        .height(250.dp)
                                        .onGloballyPositioned {
                                            columnWidth.value = it.size.width.toFloat()
                                        },
                                    axisData = axisData
                                )
                                XAxis(
                                    axisData = axisData,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .align(Alignment.BottomStart),
                                    xStart = columnWidth.value,
                                    scrollOffset = scrollOffset,
                                    zoomScale = xZoom,
                                    chartData = getLineChartData(100, 100)
                                )
                            },
                            onDraw = { _, _ ->
                                // T0D0 draw any type of graph here
                            }
                        )
                    }
                }
            }
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
