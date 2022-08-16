package com.ygraph.components.graph.linegraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ygraph.components.axis.AxisConfig
import com.ygraph.components.axis.Gravity
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.YAxisData
import com.ygraph.components.graph.linegraph.model.LineGraphData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

@Composable
fun LineGraph(lineGraphData: LineGraphData) {
    Column {
        with(lineGraphData) {
            ScrollableCanvasContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                calculateMaxDistance = { xZoom ->
                    0f // Need to add logic to calculate the max available scroll distance
                },
                drawXAndYAxis = { _, _ ->
                    val leftAlignYAxisData = YAxisData.Builder()
                        .modifier(Modifier.height(graphHeight))
                        .yMaxValue(lineChartData.maxOf { it.y })
                        .yStepValue(yStepValue)
                        .bottomPadding(bottomPadding)
                        .axisPos(Gravity.LEFT)
                        .axisLabelFontSize(axisLabelFontSize)
                        .yLabelData(yAxisLabelData)
                        .textLabelPadding(20.dp) // T0D0 need to decide how to make it dynamic
                        .yAxisOffset(20.dp) // T0D0 need to decide how to make it dynamic
                        .axisConfig(
                            AxisConfig( // T0D0 need to decide how to make it dynamic
                                shouldEllipsizeLabelEnd = true,
                                minTextWidthToEllipsize = 40.dp
                            )
                        )
                        .build()
                    YAxis(yAxisData = leftAlignYAxisData)
                },
                onDraw = { xZoom, scrollOffset ->
                    // Add draw line graph code here using the input params given above
                })
        }
    }
}
