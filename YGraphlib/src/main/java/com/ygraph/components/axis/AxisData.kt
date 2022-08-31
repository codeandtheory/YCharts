package com.ygraph.components.axis

import android.graphics.Typeface
import android.text.TextUtils
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 * YAxis data class params used in drawing yAxis in any graph.
 * @param yMaxValue: yAxis max value
 * @param yStepValue: Step value for label segmentation
 * @param yBottomPadding: Y Label offset bottom padding
 * @param yLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in YAxis
 * @param xLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in XAxis
 * @param axisLabelFontSize: Font size of axis lablel data
 * @param yLabelAndAxisLinePadding: Text label padding from y Axis
 * @param yAxisOffset: Drawing offset for yAxis.
 * @param axisLineThickness: Thickness of yAxis line
 * @param yTopPadding: Y Label offset top padding
 * @param yStartPadding: Y Axis start padding.
 * @param indicatorLineWidth: Indicator width on Y axis line for showing points
 * @param axisConfig: All config related param to toggle the elements while drawing graph
 * @param xAxisSteps: No of steps in the xAxis
 * @param xAxisStepSize: Size of each xAxis step in Dp
 * @param xLabelAndAxisLinePadding: Padding between xAxis line and the labels
 * @param axisLineColor Color of the Y & X axis
 * @param axisLabelColor Color of the Y & X axis labels
 * @param backgroundColor Background color of the Y & X components
 * @param typeface The type of font style
 * @param xBottomPadding: X Label offset bottom padding,
 * @param xAxisLabelAngle: Angle for the X axis labels
 * @param xTopPadding: X Label offset top padding
 * @param shouldXAxisStartWithPadding: Used to append a line in front of the x Axis
 * @param axisGravity: Gravity of the axis i.e for Left gravity Y-Axis will be left aligned &
 * X-Axis aligned to bottom & for Right gravity Y-Axis will be right aligned & X-Axis aligned to top
 */
data class AxisData(
    // All Y-Axis params
    val yMaxValue: Float,
    val yStepValue: Float,
    val yLabelData: (Int) -> String,
    val yLabelAndAxisLinePadding: Dp,
    val yAxisOffset: Dp,
    val yTopPadding: Dp,
    val yBottomPadding: Dp,
    val yStartPadding: Dp,
    // All X-Axis params
    val xLabelData: (Int) -> String,
    val xAxisSteps: Int,
    val xAxisStepSize: Dp,
    val xLabelAndAxisLinePadding: Dp,
    val xTopPadding: Dp,
    val xBottomPadding: Dp,
    val xAxisLabelAngle: Float,
    // All common params
    val axisLineColor: Color,
    val axisLabelColor: Color,
    val axisLabelFontSize: TextUnit,
    val axisLineThickness: Dp,
    val indicatorLineWidth: Dp,
    val backgroundColor: Color,
    val typeface: Typeface,
    val axisConfig: AxisConfig,
    val shouldXAxisStartWithPadding: Boolean,
    val axisGravity: AxisGravity
) {
    class Builder {
        private var yMaxValue: Float = 0f
        private var yStepValue: Float = 0f
        private var yLabelData: (Int) -> String = { _ -> "" }
        private var yLabelAndAxisLinePadding: Dp = 20.dp
        private var yStartPadding: Dp = 10.dp
        private var yAxisOffset: Dp = 20.dp
        private var yTopPadding: Dp = 20.dp
        private var yBottomPadding: Dp = 10.dp
        private var xAxisSteps: Int = 20
        private var xAxisStepSize: Dp = 30.dp
        private var xLabelAndAxisLinePadding: Dp = 15.dp
        private var axisPos: AxisGravity = AxisGravity.Left
        private var xLabelData: (Int) -> String = { _ -> "" }
        private var axisConfig = AxisConfig()
        private var indicatorLineWidth: Dp = 5.dp
        private var backgroundColor: Color = Color.Transparent
        private var typeface: Typeface = Typeface.DEFAULT
        private var axisLineColor: Color = Color.Black
        private var axisLabelFontSize: TextUnit = 14.sp
        private var axisLineThickness: Dp = 2.dp
        private var axisLabelColor: Color = Color.Black
        private var xTopPadding: Dp = 0.dp
        private var xBottomPadding: Dp = 0.dp
        private var shouldStartXAxisWithPadding: Boolean = false
        private var xAxisLabelAngle: Float = 0f

        fun yMaxValue(maxValue: Float) = apply { this.yMaxValue = maxValue }

        fun yStepValue(stepValue: Float) = apply { this.yStepValue = stepValue }

        fun yBottomPadding(padding: Dp) = apply { this.yBottomPadding = padding }

        fun yLabelData(labelData: (Int) -> String) = apply { this.yLabelData = labelData }

        fun xLabelData(labelData: (Int) -> String) = apply { this.xLabelData = labelData }

        fun axisLineColor(lineColor: Color) = apply { this.axisLineColor = lineColor }

        fun axisLabelFontSize(fontSize: TextUnit) = apply { this.axisLabelFontSize = fontSize }

        fun axisPos(pos: AxisGravity) = apply { this.axisPos = pos }

        fun yLabelAndAxisLinePadding(padding: Dp) =
            apply { this.yLabelAndAxisLinePadding = padding }

        fun yAxisOffset(offset: Dp) = apply { this.yAxisOffset = offset }

        fun axisLineThickness(thickness: Dp) = apply { this.axisLineThickness = thickness }

        fun yTopPadding(padding: Dp) = apply { this.yTopPadding = padding }

        fun yStartPadding(padding: Dp) = apply { this.yStartPadding = padding }

        fun indicatorLineWidth(lineWidth: Dp) = apply { this.indicatorLineWidth = lineWidth }

        fun backgroundColor(color: Color) = apply { this.backgroundColor = color }

        fun typeFace(typeface: Typeface) = apply { this.typeface = typeface }

        fun axisConfig(config: AxisConfig) = apply { this.axisConfig = config }

        fun xLabelAndAxisLinePadding(padding: Dp) =
            apply { this.xLabelAndAxisLinePadding = padding }

        fun axisLabelColor(color: Color) = apply { this.axisLabelColor = color }

        fun xAxisSteps(steps: Int) = apply { this.xAxisSteps = steps }

        fun xAxisStepSize(size: Dp) = apply { this.xAxisStepSize = size }

        fun xBottomPadding(padding: Dp) = apply { this.xBottomPadding = padding }

        fun xTopPadding(padding: Dp) = apply { this.xTopPadding = padding }
        
        fun shouldXAxisStartWithPadding(flag: Boolean) = apply { this.shouldStartXAxisWithPadding = flag }
        
        fun xAxisLabelAngle(angle: Float) = apply { this.xAxisLabelAngle = angle }

        fun build() = AxisData(
            yMaxValue,
            yStepValue,
            yLabelData,
            yLabelAndAxisLinePadding,
            yAxisOffset,
            yTopPadding,
            yBottomPadding,
            yStartPadding,
            xLabelData,
            xAxisSteps,
            xAxisStepSize,
            xLabelAndAxisLinePadding,
            xTopPadding,
            xBottomPadding,
            xAxisLabelAngle,
            axisLineColor,
            axisLabelColor,
            axisLabelFontSize,
            axisLineThickness,
            indicatorLineWidth,
            backgroundColor,
            typeface,
            axisConfig,
            shouldStartXAxisWithPadding,
            axisPos
        )
    }
}

/**
 *
 * AxisConfig data class used to mention all config related param required to draw graph.
 * @param isAxisLineRequired : true if should show the axis and points on the line else false
 * @param shouldEllipsizeAxisLabel : true if should ellipsize the axis label at end  else false
 * @param minTextWidthToEllipsize : minimum width of the axis label post which label will be ellipsized
 * @param ellipsizeAt : position at which the label will be truncated or ellipsized

 */
data class AxisConfig(
    val isAxisLineRequired: Boolean = true,
    val shouldEllipsizeAxisLabel: Boolean = false,
    val minTextWidthToEllipsize: Dp = 40.dp,
    val ellipsizeAt: TextUtils.TruncateAt = TextUtils.TruncateAt.END
)
