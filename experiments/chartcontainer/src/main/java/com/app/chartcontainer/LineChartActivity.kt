package com.app.chartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.chartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.charts.linegraph.LineGraph
import com.ygraph.components.charts.linegraph.model.*
import com.ygraph.components.graph.linegraph.model.*


class LineChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Yellow),
                        verticalArrangement = Arrangement.Center
                    ) {
                        val pointsData = DataUtils.getLineChartData(100, maxRange = 100)
                        val xAxisData = AxisData.Builder()
                            .axisStepSize(30.dp)
                            .steps(pointsData.size -1)
                            .labelData { i -> i.toString() }
                            .build()
                        val yAxisData = AxisData.Builder()
                            .steps(5)
                            .labelData { i -> (i * 20).toString() }
                            .build()
                        val data = LineGraphData(
                            LinePlotData(
                                lines = listOf(
                                    Line(
                                        dataPoints = pointsData,
                                        LineStyle(),
                                        IntersectionPoint(),
                                        SelectionHighlightPoint(),
                                        ShadowUnderLine(),
                                        SelectionHighlightPopUp()
                                    )
                                )
                            ),
                            xAxisData = xAxisData,
                            yAxisData = yAxisData
                        )
                        LineGraph(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            lineGraphData = data
                        )
                    }
                }
            }
        }
    }
}
