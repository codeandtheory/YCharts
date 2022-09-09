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
import com.ygraph.components.axis.Gravity
import com.ygraph.components.common.extensions.formatToSinglePrecision
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
                                    0 -> LineGraph1(
                                        DataUtils.getLineChartData(
                                            100,
                                            start = 50,
                                            maxRange = 100
                                        )
                                    )
                                    1 -> LineGraph2(DataUtils.getLineChartData(50, maxRange = 200))
                                    2 -> LineGraph3(
                                        DataUtils.getLineChartData(
                                            200,
                                            start = -50,
                                            maxRange = 50
                                        )
                                    )
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
    val steps = 5
    val data = LineGraphData(
        line = Line(
            dataPoints = pointsData,
            LineStyle(),
            IntersectionPoint(),
            SelectionHighlightPoint(),
            ShadowUnderLine(),
            SelectionHighlightPopUp()
        ),
        ySteps = steps,
        xStepSize = 30.dp,
        xAxisSteps = pointsData.size,
        xAxisPos = Gravity.BOTTOM,
        yAxisPos = Gravity.LEFT,
        yAxisLabelData = { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = pointsData.minOf { it.y }
            val yScale = 50 / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        },
        xAxisLabelData = { i -> i.toString() },
        gridLines = GridLines()
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
        ySteps = 10,
        xStepSize = 40.dp,
        xAxisSteps = pointsData.size,
        xAxisPos = Gravity.BOTTOM,
        yAxisPos = Gravity.LEFT,
        yAxisLabelData = { i -> "${(i * 20)}k" },
        xAxisLabelData = { i -> if (i == 0) "" else (1900 + i).toString() },
        xAxisLabelAngle = 20f,
        bottomPadding = 30.dp,
        yLabelAndAxisLinePadding = 20.dp,
        axisLabelColor = Color.Blue,
        axisLineColor = Color.DarkGray,
        typeface = Typeface.DEFAULT_BOLD
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
    val steps = 10
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
        ySteps = steps,
        xStepSize = 40.dp,
        xAxisSteps = pointsData.size,
        xAxisPos = Gravity.BOTTOM,
        yAxisPos = Gravity.LEFT,
        yAxisLabelData = { i ->
            val yMin = pointsData.minOf { it.y }
            val yScale = 100 / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        },
        xAxisLabelData = { i -> i.toString() },
        axisLineColor = Color.Red
    )
    LineGraph(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineGraphData = data
    )
}
