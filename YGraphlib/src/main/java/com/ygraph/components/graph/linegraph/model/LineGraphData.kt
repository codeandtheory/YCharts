package com.ygraph.components.graph.linegraph.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.Gravity
import com.ygraph.components.common.model.Point
import com.ygraph.components.common.utils.DataUtils

// T0D0 Add params as per requirement
data class LineGraphData(
    val dataPoints: List<Point> = DataUtils.getLineChartData(100, 100),
    val isZoomAllowed: Boolean = true,
    val paddingTop: Dp = 16.dp,
    val bottomPadding: Dp = 10.dp,
    val paddingRight: Dp = 10.dp,
    val graphHeight: Dp = 250.dp,
    val yStepValue: Float = 20f,
    val yAxisLabelData: (Int) -> String = { i -> (i * 20).toString() }, //T0D0 Need to refactor
    val xAxisLabelData: (Int) -> String = { i -> i.toString() },
    val axisPos: Gravity = Gravity.LEFT,
    val textLabelPadding: Dp = 10.dp,
    val axisLabelFontSize: TextUnit = 14.sp,
    val yLabelAndAxisLinePadding: Dp = 20.dp,
    val yAxisOffset: Dp = 20.dp
)
