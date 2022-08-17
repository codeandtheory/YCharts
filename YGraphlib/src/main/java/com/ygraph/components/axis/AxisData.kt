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
 * @param bottomPadding: X,Y Label offset bottom padding
 * @param yLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in YAxis
 * @param xLabelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in XAxis
 * @param axisLabelFontSize: Font size of axis lablel data
 * @param axisPos : Axis gravity
 * @param textLabelPadding: Text label padding from y Axis
 * @param yAxisOffset: Drawing offset for yAxis.
 * @param lineStrokeWidth: Thickness of yAxis line
 * @param topPadding: X,Y Label offset top padding
 * @param indicatorLineWidth: Indicator width on Y axis line for showing points
 * @param axisConfig: All config related param to toggle the elements while drawing graph
 */
data class AxisData(
    val yMaxValue: Float,
    val yStepValue: Float,
    val yLabelData: (Int) -> String,
    val xLabelData: (Int) -> String,
    val yAxisLineColor: Color,
    val axisLabelFontSize: TextUnit,
    val axisPos: Gravity,
    val textLabelPadding: Dp,
    val yAxisOffset: Dp,
    val lineStrokeWidth: Dp,
    val topPadding: Dp,
    val bottomPadding: Dp,
    val indicatorLineWidth: Dp,
    val backgroundColor: Color,
    val typeface: Typeface,
    val axisConfig: AxisConfig,
    val xAxisSteps: Int,
    val xAxisStepSize: Dp,
    val xTopPadding: Dp
) {
    class Builder {
        private var yMaxValue: Float = 0f
        private var yStepValue: Float = 0f
        private var yLabelData: (Int) -> String = { _ -> "" }
        private var xLabelData: (Int) -> String = { _ -> "" }
        private var yAxisLineColor: Color = Color.Black
        private var axisLabelFontSize: TextUnit = 14.sp
        private var axisPos: Gravity = Gravity.LEFT
        private var textLabelPadding: Dp = 4.dp
        private var yAxisOffset: Dp = 10.dp
        private var lineStrokeWidth: Dp = 2.dp
        private var topPadding: Dp = 20.dp
        private var bottomPadding: Dp = 10.dp
        private var indicatorLineWidth: Dp = 5.dp
        private var backgroundColor: Color = Color.White
        private var typeface: Typeface = Typeface.DEFAULT
        private val xAxisSteps: Int = 20
        private val xAxisStepSize: Dp = 20.dp
        private var axisConfig = AxisConfig()
        private val xTopPadding: Dp = 15.dp

        fun yMaxValue(maxValue: Float) = apply { this.yMaxValue = maxValue }

        fun yStepValue(stepValue: Float) = apply { this.yStepValue = stepValue }

        fun bottomPadding(padding: Dp) = apply { this.bottomPadding = padding }

        fun yLabelData(labelData: (Int) -> String) = apply { this.yLabelData = labelData }

        fun xLabelData(labelData: (Int) -> String) = apply { this.xLabelData = labelData }

        fun yAxisLineColor(lineColor: Color) = apply { this.yAxisLineColor = lineColor }

        fun axisLabelFontSize(fontSize: TextUnit) = apply { this.axisLabelFontSize = fontSize }

        fun axisPos(pos: Gravity) = apply { this.axisPos = pos }

        fun textLabelPadding(padding: Dp) = apply { this.textLabelPadding = padding }

        fun yAxisOffset(offset: Dp) = apply { this.yAxisOffset = offset }

        fun lineStrokeWidth(strokeWidth: Dp) = apply { this.lineStrokeWidth = strokeWidth }

        fun topPadding(padding: Dp) = apply { this.topPadding = padding }

        fun indicatorLineWidth(lineWidth: Dp) = apply { this.indicatorLineWidth = lineWidth }

        fun backgroundColor(color: Color) = apply { this.backgroundColor = color }

        fun typeFace(typeface: Typeface) = apply { this.typeface = typeface }

        fun axisConfig(config: AxisConfig) = apply { this.axisConfig = config }

        fun build() = AxisData(
            yMaxValue,
            yStepValue,
            yLabelData,
            xLabelData,
            yAxisLineColor,
            axisLabelFontSize,
            axisPos,
            textLabelPadding,
            yAxisOffset,
            lineStrokeWidth,
            topPadding,
            bottomPadding,
            indicatorLineWidth,
            backgroundColor,
            typeface,
            axisConfig,
            xAxisSteps,
            xAxisStepSize,
            xTopPadding
        )
    }
}

/**
 *
 * AxisConfig data class used to mention all config related param required to draw graph.
 * @param isAxisLineRequired : true if should show the axis and points on the line else false
 * @param shouldEllipsizeLabelEnd : true if should ellipsize the axis label at end  else false
 * @param minTextWidthToEllipsize : minimum width of the axis label post which label will be ellipsized
 * @param ellipsizeAt : position at which the label will be truncated or ellipsized

 */
data class AxisConfig(
    val isAxisLineRequired: Boolean = true,
    val shouldEllipsizeLabelEnd: Boolean = false,
    val minTextWidthToEllipsize: Dp = 40.dp,
    val ellipsizeAt: TextUtils.TruncateAt = TextUtils.TruncateAt.END
)
