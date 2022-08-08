package com.ygraph.components.axis

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 * YAxis data class params used in drawing yAxis in any graph.
 * @param modifier : All modifier related property
 * @param yMaxValue: yAxis max value
 * @param yStepValue: Step value for label segmentation
 * @param bottomPadding: X,Y Label offset bottom padding
 * @param yLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in YAxis
 * @param axisLabelFontSize: Font size of axis lablel data
 * @param axisPos : Axis gravity
 * @param textLabelPadding: Text label padding from y Axis
 * @param yAxisOffset: Drawing offset for yAxis.
 * @param lineStrokeWidth: Thickness of yAxis line
 * @param topPadding: X,Y Label offset top padding
 * @param indicatorLineWidth: Indicator width on Y axis line for showing points
 * @param axisConfig: All config related param to toggle the elements while drawing graph
 */
data class YAxisData(
    val modifier: Modifier,
    val yMaxValue: Float,
    val yStepValue: Float,
    val yLabelData: (Int) -> String,
    val yAxisLineColor: Color,
    val axisLabelFontSize: TextUnit,
    val axisPos: Gravity,
    val textLabelPadding: Dp,
    val yAxisOffset: Dp,
    val lineStrokeWidth: Dp,
    val topPadding: Dp,
    val bottomPadding: Dp,
    val indicatorLineWidth: Dp,
    val axisConfig: AxisConfig
) {
    class Builder {
        private var modifier: Modifier = Modifier
        private var yMaxValue: Float = 0f
        private var yStepValue: Float = 0f
        private var yLabelData: (Int) -> String = { _ -> "" }
        private var yAxisLineColor: Color = Color.Black
        private var axisLabelFontSize: TextUnit = 14.sp
        private var axisPos: Gravity = Gravity.LEFT
        private var textLabelPadding: Dp = 4.dp
        private var yAxisOffset: Dp = 30.dp
        private var lineStrokeWidth: Dp = 2.dp
        private var topPadding: Dp = 20.dp
        private var bottomPadding: Dp = 10.dp
        private var indicatorLineWidth: Dp = 5.dp
        private var axisConfig = AxisConfig(isAxisLineRequired = true)

        fun modifier(modifier: Modifier) = apply { this.modifier = modifier }

        fun yMaxValue(maxValue: Float) = apply { this.yMaxValue = maxValue }

        fun yStepValue(stepValue: Float) = apply { this.yStepValue = stepValue }

        fun bottomPadding(padding: Dp) = apply { this.bottomPadding = padding }

        fun yLabelData(labelData: (Int) -> String) = apply { this.yLabelData = labelData }

        fun yAxisLineColor(lineColor: Color) = apply { this.yAxisLineColor = lineColor }

        fun axisLabelFontSize(fontSize: TextUnit) = apply { this.axisLabelFontSize = fontSize }

        fun axisPos(pos: Gravity) = apply { this.axisPos = pos }

        fun textLabelPadding(padding: Dp) = apply { this.textLabelPadding = padding }

        fun yAxisOffset(offset: Dp) = apply { this.yAxisOffset = offset }

        fun lineStrokeWidth(strokeWidth: Dp) = apply { this.lineStrokeWidth = strokeWidth }

        fun topPadding(padding: Dp) = apply { this.topPadding = padding }

        fun indicatorLineWidth(lineWidth: Dp) = apply { this.indicatorLineWidth = lineWidth }

        fun axisConfig(config: AxisConfig) = apply { this.axisConfig = config }

        fun build() = YAxisData(
            modifier,
            yMaxValue,
            yStepValue,
            yLabelData,
            yAxisLineColor,
            axisLabelFontSize,
            axisPos,
            textLabelPadding,
            yAxisOffset,
            lineStrokeWidth,
            topPadding,
            bottomPadding,
            indicatorLineWidth,
            axisConfig
        )
    }
}

/**
 *
 * AxisConfig data class used to mention all config related param required to draw graph.
 * @param isAxisLineRequired : true if should show the axis and points on the line else false
 */
data class AxisConfig(val isAxisLineRequired: Boolean)
