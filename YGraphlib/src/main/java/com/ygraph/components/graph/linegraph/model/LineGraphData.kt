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
 * @param line: The path to be drawn on the graph represented by a line
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
    val line: Line,
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

/**
 * Represent a Line in the [LineGraph]
 *
 * @param dataPoints list of points [Point] in the line
 * @param intersectionPoint drawing logic to draw the point itself in [IntersectionPoint].
 * If null, the point is not drawn.
 * @param selectionHighlightPoint drawing logic to draw the highlight at the point when it is selected
 * in [SelectionHighlightPoint] If null, the point won't be highlighted on selection
 * @param shadowUnderLine drawing logic for the section under the line in [ShadowUnderLine].
 */
data class Line(
    val dataPoints: List<Point>,
    val intersectionPoint: IntersectionPoint? = null,
    val selectionHighlightPoint: SelectionHighlightPoint? = null,
    val shadowUnderLine: ShadowUnderLine? = null
)
