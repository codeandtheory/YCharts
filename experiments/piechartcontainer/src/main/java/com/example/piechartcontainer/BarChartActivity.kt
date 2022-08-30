package com.example.piechartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisData
import com.ygraph.components.barchart.BarChart
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.common.utils.DataUtils.getBarChartData
import com.ygraph.components.common.utils.DataUtils.getGradientBarChartData
import com.ygraph.components.barchart.GroupBarChart
import com.ygraph.components.barchart.models.GroupBarChartData
import com.ygraph.components.common.utils.DataUtils.getColorList
import com.ygraph.components.common.utils.DataUtils.getGroupBarChartData

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

                    val groupBarData = getGroupBarChartData(50, 50, 3)
                    val yStepSize = 10
                    val axisData = AxisData.Builder()
                        .ySteps(yStepSize)
                        .xAxisSteps(groupBarData.size - 1)
                        .yLabelData { index -> (index * yStepSize).toString() }
                        .xLabelData { index -> groupBarData[index].label }
                        .yLabelAndAxisLinePadding(20.dp)
                        .yAxisOffset(20.dp)
                        .yTopPadding(40.dp)
                        .shouldXAxisStartWithPadding(true)
                        .xBottomPadding(10.dp)
                        .build()

                    val groupBarChartData = GroupBarChartData(
                        groupedBarList = groupBarData,
                        axisData = axisData,
                        yStepSize = yStepSize,
                        yLabelAndAxisLinePadding = 20.dp,
                        yAxisOffset = 20.dp,
                        colorTemplate = getColorList(3),
                        yLabelData = { index -> (index * yStepSize).toString() },
                        xLabelData = { index -> groupBarData[index].label },
                    )

                    GroupBarChart(
                        modifier = Modifier.height(600.dp),
                        groupBarChartData = groupBarChartData
                    )
                }
            }
        }
    }
}


