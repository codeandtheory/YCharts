@file:OptIn(ExperimentalMaterialApi::class)

package com.app.ygraphs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.common.components.AccessibilityBottomSheetDialog
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.components.Legends
import com.ygraph.components.common.model.LegendsConfig
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.graph.bargraph.models.BarPlotData
import com.ygraph.components.graph.bargraph.models.BarStyle
import com.ygraph.components.graph.bargraph.models.GroupBar
import com.ygraph.components.graph.combinedgraph.CombinedGraph
import com.ygraph.components.graph.combinedgraph.model.CombinedGraphData
import com.ygraph.components.graph.linegraph.model.*
import kotlinx.coroutines.launch

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
                        BarWithLineChart()
                        /*  LazyColumn(content = {
                              items(1) { item ->
                                  when (item) {
                                      0 -> BarWithLineChart()
                                  }
                              }
                          })*/
                    }
                }
            }
        }
    }
}

@Composable
fun BarWithLineChart() {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val maxRange = 100
    val groupBarData = DataUtils.getGroupBarChartData(50, 100, 3)
    val yStepSize = 10
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .bottomPadding(5.dp)
        .labelData { index -> index.toString() }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val linePlotData = LinePlotData(
        lines = listOf(
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
    )
    val colorPaletteList = DataUtils.getColorPaletteList(3)
    val legendsConfig = LegendsConfig(
        legendLabelList = DataUtils.getLegendsLabelData(colorPaletteList),
        gridColumnCount = 3
    )
    val barPlotData = BarPlotData(
        groupBarList = groupBarData,
        barStyle = BarStyle(barWidth = 35.dp),
        barColorPaletteList = colorPaletteList
    )
    val combinedGraphData = CombinedGraphData(
        combinedPlotDataList = listOf(barPlotData, linePlotData),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
    Column(
        Modifier
            .height(500.dp)
    ) {
        CombinedGraph(
            modifier = Modifier
                .height(400.dp)
                .semantics { contentDescription = "Double tap to know the graph in detail" }
                .clickable {
                    scope.launch {
                        accessibilitySheetState.animateTo(
                            ModalBottomSheetValue.HalfExpanded
                        )
                    }
                },
            combinedGraphData = combinedGraphData
        )
        Legends(
            legendsConfig = legendsConfig
        )
    }
    AccessibilityBottomSheetDialog(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        content = {
            LazyColumn {
                items(groupBarData.size) { index ->
                    GroupBarInfo(groupBarData[index], index)
                }
            }
        },
        sheetState = accessibilitySheetState
    )
}

@Composable
private fun GroupBarInfo(groupBar: GroupBar, xPosition: Int) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .clickable { }
        .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("X Axis \n Position : $xPosition")
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, start = 10.dp, bottom = 16.dp, end = 10.dp)
        ) {
            groupBar.barList.forEachIndexed { index, value ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color.Blue)
                            .size(20.dp)
                    )
                    Text(
                        "Bar at $index with label ${value.label} has value ${
                            String.format(
                                "%.2f",
                                value.point.y
                            )
                        }"
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
