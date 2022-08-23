package com.android.linegraph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.Gravity
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.graph.linegraph.LineGraph
import com.ygraph.components.graph.linegraph.model.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Yellow),
                    verticalArrangement = Arrangement.Center
                ) {
                    val pointsData = DataUtils.getLineChartData(100, 100)
                    val data = LineGraphData(
                        line = Line(
                            dataPoints = pointsData,
                            IntersectionPoint(),
                            SelectionHighlightPoint(),
                            ShadowUnderLine()
                        ),
                        yStepValue = 20f,
                        xStepSize = 30.dp,
                        xAxisSteps = pointsData.size,
                        xAxisPos = Gravity.BOTTOM,
                        yAxisPos = Gravity.LEFT,
                        yAxisLabelData = { i -> (i * 20).toString() },
                        xAxisLabelData = { i -> i.toString() }
                    )
                    LineGraph(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        lineGraphData = data
                    )
                }
            }
        }
    }
}

