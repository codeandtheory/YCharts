package com.ygraph.components.graph.linegraph.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.Gravity
import com.ygraph.components.common.model.DataPoint

// T0D0 Add params as per requirement
data class LineGraphData(
    val lineChartData: List<DataPoint>,
    val isZoomAllowed: Boolean = true,
    val paddingTop: Dp = 16.dp,
    val bottomPadding: Dp = 10.dp,
    val paddingRight: Dp = 10.dp,
    val graphHeight: Dp = 250.dp,
    val yStepValue: Float = 10f,
    val yAxisLabelData: (Int) -> String,
    val xAxisLabelData: (Int) -> String,
    val axisPos: Gravity = Gravity.LEFT,
    val textLabelPadding: Dp,
    val axisLabelFontSize: TextUnit = 14.sp,
)
