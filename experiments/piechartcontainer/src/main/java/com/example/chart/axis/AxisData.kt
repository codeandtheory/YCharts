package com.example.chart.axis

import android.graphics.Typeface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisConfig
import co.yml.charts.axis.Gravity

/**
 *
 * YAxis data class params used in drawing yAxis in any graph.
 * @param steps: No of step for label segmentation
 * @param axisBottomPadding: Axis Label offset bottom padding
 * @param labelData(Int)-> String: lambda method for providing labels, @param Int will be the index
 * given for each level in Axis
 * @param axisLabelFontSize: Font size of axis label data
 * @param axisPos :Gravity of axis
 * @param labelAndAxisLinePadding: Text label padding from Axis
 * @param axisOffset: Drawing offset for axis.
 * @param axisLineThickness: Thickness of yAxis line
 * @param axisTopPadding: Axis top padding
 * @param axisStartPadding: Axis start padding.
 * @param indicatorLineWidth: Indicator width on Y axis line for showing points
 * @param axisConfig: All config related param to toggle the elements while drawing graph
 * @param axisStepSize: Size of each Axis step in Dp
 * @param axisLineColor Color of the Y or X axis
 * @param axisLabelColor Color of the Y or X axis labels
 * @param backgroundColor Background color of the Y or X components
 * @param typeface The type of font style
 * @param axisBottomPadding: Axis bottom padding,
 * @param axisLabelAngle: Angle for the axis labels
 * @param startDrawPadding: Padding between Axis and first point on the Axis
 * @param shouldDrawAxisLineTillEnd : Boolean to draw axis line till end.
 * @param axisLabelDescription: Description to describe axis value for accessibility service
 */
data class AxisData(
    val steps: Int,
    val labelData: (Int) -> String,
    val axisPos: Gravity,
    val labelAndAxisLinePadding: Dp,
    val axisOffset: Dp,
    val axisTopPadding: Dp,
    val axisBottomPadding: Dp,
    val axisStartPadding: Dp,
    val axisStepSize: Dp,
    val axisLabelAngle: Float,
    val axisLineColor: Color,
    val axisLabelColor: Color,
    val axisLabelFontSize: TextUnit,
    val axisLineThickness: Dp,
    val indicatorLineWidth: Dp,
    val backgroundColor: Color,
    val typeface: Typeface,
    val axisConfig: AxisConfig,
    val startDrawPadding: Dp,
    val shouldDrawAxisLineTillEnd: Boolean,
    val axisLabelDescription: (String) -> String,
    val isDataCategoryInYAxis: Boolean
) {
    class Builder {
        private var steps: Int = 1
        private var labelData: (Int) -> String = { _ -> "" }
        private var axisPos: Gravity = Gravity.LEFT
        private var labelAndAxisLinePadding: Dp = 20.dp
        private var axisStartPadding: Dp = 10.dp
        private var axisOffset: Dp = 10.dp
        private var axisTopPadding: Dp = 20.dp
        private var axisBottomPadding: Dp = 10.dp
        private var axisStepSize: Dp = 30.dp
        private var axisConfig = AxisConfig()
        private var indicatorLineWidth: Dp = 5.dp
        private var backgroundColor: Color = Color.Transparent
        private var typeface: Typeface = Typeface.DEFAULT
        private var axisLineColor: Color = Color.Black
        private var axisLabelFontSize: TextUnit = 14.sp
        private var axisLineThickness: Dp = 2.dp
        private var axisLabelColor: Color = Color.Black
        private var startDrawPadding: Dp = 0.dp
        private var axisLabelAngle: Float = 0f
        private var shouldDrawAxisLineTillEnd: Boolean = false
        private var axisLabelDescription: (String) -> String = { label -> "X Axis label $label" }
        private var isDataCategoryInYAxis: Boolean = false

        fun steps(count: Int) = apply { this.steps = count }

        fun axisOffset(offset: Dp) = apply { this.axisOffset = offset }

        fun labelAndAxisLinePadding(padding: Dp) = apply { this.labelAndAxisLinePadding = padding }

        fun axisStepSize(size: Dp) = apply { this.axisStepSize = size }

        fun labelData(labelData: (Int) -> String) = apply { this.labelData = labelData }

        fun axisLineColor(lineColor: Color) = apply { this.axisLineColor = lineColor }

        fun axisLabelFontSize(fontSize: TextUnit) = apply { this.axisLabelFontSize = fontSize }

        fun axisPosition(pos: Gravity) = apply { this.axisPos = pos }

        fun axisLineThickness(thickness: Dp) = apply { this.axisLineThickness = thickness }

        fun topPadding(padding: Dp) = apply { this.axisTopPadding = padding }

        fun startPadding(padding: Dp) = apply { this.axisStartPadding = padding }

        fun bottomPadding(padding: Dp) = apply { this.axisBottomPadding = padding }

        fun indicatorLineWidth(lineWidth: Dp) = apply { this.indicatorLineWidth = lineWidth }

        fun backgroundColor(color: Color) = apply { this.backgroundColor = color }

        fun typeFace(typeface: Typeface) = apply { this.typeface = typeface }

        fun axisConfig(config: AxisConfig) = apply { this.axisConfig = config }

        fun axisLabelColor(color: Color) = apply { this.axisLabelColor = color }

        fun startDrawPadding(padding: Dp) =
            apply { this.startDrawPadding = padding }

        fun axisLabelAngle(angle: Float) = apply { this.axisLabelAngle = angle }

        fun shouldDrawAxisLineTillEnd(flag: Boolean) =
            apply { this.shouldDrawAxisLineTillEnd = flag }

        fun axisLabelDescription(description: (String) -> String) =
            apply { this.axisLabelDescription = description }

        fun isDataCategoryInYAxis(isInYAxis: Boolean) = apply {
            this.isDataCategoryInYAxis = isInYAxis
        }


        fun build() = AxisData(
            steps,
            labelData,
            axisPos,
            labelAndAxisLinePadding,
            axisOffset,
            axisTopPadding,
            axisBottomPadding,
            axisStartPadding,
            axisStepSize,
            axisLabelAngle,
            axisLineColor,
            axisLabelColor,
            axisLabelFontSize,
            axisLineThickness,
            indicatorLineWidth,
            backgroundColor,
            typeface,
            axisConfig,
            startDrawPadding,
            shouldDrawAxisLineTillEnd,
            axisLabelDescription,
            isDataCategoryInYAxis
        )
    }
}