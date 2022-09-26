package com.example.piechartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.common.components.Legends
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.common.utils.DataUtils.getGroupBarChartData
import com.ygraph.components.graph.bargraph.GroupBarGraph
import com.ygraph.components.graph.bargraph.models.GroupBarGraphData
import com.ygraph.components.graph.bargraph.models.LegendsConfig

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
                        legendLabelList = DataUtils.getStackLabelData(barSize),
                        gridColumnCount = 3
                    )
                    val groupBarGraphData = GroupBarGraphData(
                        groupedBarList = groupBarData,
                        xAxisData = xAxisData,
                        yAxisData = yAxisData,
                        legendsConfig = legendsConfig
                    )
                    Column {
                        GroupBarGraph(
                            modifier = Modifier.height(600.dp),
                            groupBarGraphData = groupBarGraphData
                        )
                        Legends(legendsConfig = legendsConfig)
                    }
                }
            }
        }
    }
}

