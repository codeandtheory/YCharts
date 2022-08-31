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
import com.ygraph.components.graph.linegraph.LineGraph
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
                        val pointsData = DataUtils.getLineChartData(100, 100)
                        val axisData = AxisData.Builder()
                            .yMaxValue(pointsData.maxOf { it.y })
                            .yStepValue(20f)
                            .xAxisStepSize(30.dp)
                            .xAxisSteps(pointsData.size - 1)
                            .yLabelData { i -> (i * 20).toString() }
                            .xLabelData { i -> i.toString() }
                            .build()
                        val data = LineGraphData(
                            line = Line(
                                dataPoints = pointsData,
                                LineStyle(),
                                IntersectionPoint(),
                                SelectionHighlightPoint(),
                                ShadowUnderLine(),
                                SelectionHighlightPopUp()
                            ),
                            axisData = axisData
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
