@file:OptIn(ExperimentalMaterialApi::class)

package com.example.piechartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.components.Legends
import com.ygraph.components.common.model.LegendsConfig
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.common.utils.DataUtils.getGroupBarChartData
import com.ygraph.components.charts.bargraph.GroupBarGraph
import com.ygraph.components.charts.bargraph.models.BarPlotData
import com.ygraph.components.charts.bargraph.models.GroupBarGraphData

class BarChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val barSize = 3
                    val groupBarData = getGroupBarChartData(50, 50, barSize)
                    val colorPaletteList = DataUtils.getColorPaletteList(barSize)
                    val groupBarPlotData = BarPlotData(
                        groupBarList = groupBarData,
                        barColorPaletteList = colorPaletteList
                    )
                    val yStepSize = 10
                    val xAxisData = AxisData.Builder()
                        .axisStepSize(30.dp)
                        .steps(groupBarData.size - 1)
                        .bottomPadding(10.dp)
                        .labelData { index -> groupBarData[index].label }
                        .build()
                    val yAxisData = AxisData.Builder()
                        .steps(yStepSize)
                        .labelAndAxisLinePadding(20.dp)
                        .axisOffset(20.dp)
                        .labelData { index -> (index * (50 / yStepSize)).toString() }
                        .build()
                    val legendsConfig = LegendsConfig(
                        DataUtils.getLegendsLabelData(colorPaletteList),
                        gridColumnCount = 3
                    )
                    val groupBarGraphData = GroupBarGraphData(
                        barPlotData = groupBarPlotData,
                        xAxisData = xAxisData,
                        yAxisData = yAxisData
                    )
                    Column {
                        GroupBarGraph(
                            modifier = Modifier
                                .height(400.dp),
                            groupBarGraphData = groupBarGraphData
                        )
                        Legends(legendsConfig = legendsConfig)
                    }
                }
            }
        }
    }
}
