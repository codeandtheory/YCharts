package com.ygraph.components.graph.linegraph.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.axis.Gravity
import com.ygraph.components.common.model.Point

/**
 *
 * LineGraphData data class that contains all params user need to define to draw a line graph.
 * @param dataPoints: List of Points to be ploted on the graph
 * @param yAxisLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in YAxis
 * @param xAxisLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in XAxis
 * @param yStepValue: Step value for label segmentation
 * @param xStepSize: Size of each step in X-Axis in Dp
 * @param xAxisSteps: Number of steps in X-Axis
 * @param yAxisPos :Gravity of yAxis either right or left
 * @param xAxisPos: Gravity of xAxis either bottom or top
 * @param isZoomAllowed: True if zoom in X-Axis is allowed else false
 * @param axisLabelFontSize: Font size of axis lablel data
 * @param yLabelAndAxisLinePadding: Text label padding from y Axis and the labels
 * @param yAxisOffset: Drawing offset for yAxis.
 */
data class LineGraphData(
    val dataPoints: List<Point>,
    val yAxisLabelData: (Int) -> String,
    val xAxisLabelData: (Int) -> String,
    val yStepValue: Float,
    val xStepSize: Dp,
    val xAxisSteps: Int,
    val xAxisPos: Gravity = Gravity.BOTTOM,
    val yAxisPos: Gravity = Gravity.LEFT,
    val isZoomAllowed: Boolean = true,
    val paddingTop: Dp = 16.dp,
    val bottomPadding: Dp = 10.dp,
    val paddingRight: Dp = 10.dp,
    val axisLabelFontSize: TextUnit = 14.sp,
    val yLabelAndAxisLinePadding: Dp = 20.dp,
    val yAxisOffset: Dp = 20.dp
)
