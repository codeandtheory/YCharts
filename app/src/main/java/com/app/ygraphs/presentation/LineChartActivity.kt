package com.app.ygraphs.presentation

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.model.Point
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.graph.linegraph.LineGraph
import com.ygraph.components.graph.linegraph.model.*

class LineChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YGraphsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_line_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            items(3) { item ->
                                when (item) {
                                    0 -> LineGraph1(DataUtils.getLineChartData(100, 100))
                                    1 -> LineGraph2(DataUtils.getLineChartData(50, 200))
                                    2 -> LineGraph3(DataUtils.getLineChartData(200, 100))
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun LineGraph1(pointsData: List<Point>) {
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

@Composable
private fun LineGraph2(pointsData: List<Point>) {
    val axisData = AxisData.Builder()
        .yMaxValue(pointsData.maxOf { it.y })
        .yStepValue(20f)
        .xAxisStepSize(40.dp)
        .xAxisSteps(pointsData.size - 1)
        .yLabelData { i -> "${(i * 20)}k" }
        .xLabelData { i -> if (i == 0) "" else (1900 + i).toString() }
        .xAxisLabelAngle(20f)
        .yLabelAndAxisLinePadding(20.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.DarkGray)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()
    val data = LineGraphData(
        line = Line(
            dataPoints = pointsData,
            lineStyle = LineStyle(lineType = LineType.Straight(), color = Color.Blue),
            intersectionPoint = IntersectionPoint(color = Color.Red),
            selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                val xLabel = "x : ${(1900 + x).toInt()} "
                val yLabel = "y : ${String.format("%.2f", y)}"
                "$xLabel $yLabel"
            })
        ),
        bottomPadding = 30.dp,
        axisData = axisData
    )
    LineGraph(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineGraphData = data
    )
}

@Composable
private fun LineGraph3(pointsData: List<Point>) {
    val axisData = AxisData.Builder()
        .yMaxValue(pointsData.maxOf { it.y })
        .yStepValue(10f)
        .xAxisStepSize(40.dp)
        .xAxisSteps(pointsData.size - 1)
        .yLabelData { i -> (i * 10).toString() }
        .xLabelData { i -> i.toString() }
        .axisLineColor(Color.Red)
        .build()
    val data = LineGraphData(
        line = Line(
            dataPoints = pointsData,
            lineStyle = LineStyle(
                lineType = LineType.SmoothCurve(isDotted = true),
                color = Color.Green
            ),
            shadowUnderLine = ShadowUnderLine(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Green,
                        Color.Transparent
                    )
                ), alpha = 0.3f
            ),
            selectionHighlightPoint = SelectionHighlightPoint(
                color = Color.Green
            ),
            selectionHighlightPopUp = SelectionHighlightPopUp(
                backgroundColor = Color.Black,
                backgroundStyle = Stroke(2f),
                labelColor = Color.Red,
                labelTypeface = Typeface.DEFAULT_BOLD
            )
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

