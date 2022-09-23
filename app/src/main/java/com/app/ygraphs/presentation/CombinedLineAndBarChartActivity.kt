package com.app.ygraphs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.graph.bargraph.models.BarStyle
import com.ygraph.components.graph.bargraph.models.StackLabelConfig
import com.ygraph.components.graph.combinedgraph.CombinedGraph
import com.ygraph.components.graph.combinedgraph.model.BarPlotData
import com.ygraph.components.graph.combinedgraph.model.CombinedGraphData
import com.ygraph.components.graph.linegraph.model.*

class CombinedLineAndBarChartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YGraphsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_bar_with_line_chart),
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
                            items(1) { item ->
                                when (item) {
                                    0 -> BarWithLineChart()
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
fun BarWithLineChart() {
    val maxRange = 100
    val groupBarData = DataUtils.getGroupBarChartData(50, 100, 3)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .bottomPadding(40.dp)
        .labelData { index -> index.toString() }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val linePlotData = listOf(
        Line(
            DataUtils.getLineChartData(50, maxRange = 100),
            lineStyle = LineStyle(color = Color.Blue),
            intersectionPoint = IntersectionPoint(),
            selectionHighlightPoint = SelectionHighlightPoint(),
            selectionHighlightPopUp = SelectionHighlightPopUp()
        ),
        Line(
            DataUtils.getLineChartData(50, maxRange = 100),
            lineStyle = LineStyle(color = Color.Black),
            intersectionPoint = IntersectionPoint(),
            selectionHighlightPoint = SelectionHighlightPoint(),
            selectionHighlightPopUp = SelectionHighlightPopUp()
        )
    )
    val combinedGraphData = CombinedGraphData(
        linePlotData = linePlotData,
        barPlotData = BarPlotData(
            groupBarData,
            barStyle = BarStyle(barWidth = 35.dp),
            stackLabelConfig = StackLabelConfig(
                stackLabelList = DataUtils.getStackLabelData(3),
                gridColumnCount = 3
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
    CombinedGraph(
        modifier = Modifier.height(400.dp),
        combinedGraphData = combinedGraphData
    )
}
