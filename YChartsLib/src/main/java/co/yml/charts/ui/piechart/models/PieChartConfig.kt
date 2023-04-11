package co.yml.charts.ui.piechart.models

import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.IntRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.AccessibilityConfig
import co.yml.charts.ui.piechart.PieChartConstants
import co.yml.charts.ui.piechart.PieChartConstants.DEFAULT_PADDING
import co.yml.charts.ui.piechart.PieChartConstants.DEFAULT_SLICE_LABEL_TEXT_SIZE
import co.yml.charts.ui.piechart.PieChartConstants.DEFAULT_START_ANGLE
import co.yml.charts.ui.piechart.PieChartConstants.DEFAULT_STROKE_WIDTH

/**
 * PieChartConfig data class used to mention all config related param required to draw PieChart.
 * @param startAngle: Starting angle
 * @param showSliceLabels: Control the labels visibility
 * @param sliceLabelTextSize: Text size of the labels
 * @param sliceLabelTextColor: Text color of the labels
 * @param sliceLabelTypeface: Typeface of the labels
 * @param isAnimationEnable: Boolean Flag for enabling animation
 * @param animationDuration: Duration of animation
 * @param strokeWidth: Stroke width of Donut Chart
 * @param labelFontSize: Label text font size
 * @param labelTypeface: Label text typeface
 * @param labelVisible: Label text visibility
 * @param labelType: Type of label (percentage or value of slice), value available only for Donut Chart
 * @param labelColor: Label text color
 * @param labelColorType: Label text color according to the specified in color in labelColor or the slice color, only for Donut Chart
 * @param activeSliceAlpha: Opacity of the active slice
 * @param inActiveSliceAlpha: Opacity of the inactive slice
 * @param isEllipsizeEnabled: Boolean flag for enabling ellipsize
 * @param sliceMinTextWidthToEllipsize: Minimum width of the label post which label will be ellipsized
 * @param sliceLabelEllipsizeAt: Position at which the label will be truncated or ellipsized
 * @param chartPadding: Padding for the Pie chart/Donut Chart
 * @param accessibilityConfig: Configs related to accessibility service defined here in [AccessibilityConfig]
 * @param isSumVisible: When no slice is selected show the sum of values, only used for Donut Chart
 * @param isClickOnSliceEnabled: Enable/Disable the click on slice
 * @param sumUnit: The unit of the sum of values, only used for Donut Chart
 */
data class PieChartConfig(
    val startAngle: Float = DEFAULT_START_ANGLE,
    val showSliceLabels: Boolean = true,
    val sliceLabelTextSize: TextUnit = DEFAULT_SLICE_LABEL_TEXT_SIZE.sp,
    val sliceLabelTextColor: Color = Color.White,
    val sliceLabelTypeface: Typeface = Typeface.DEFAULT,
    val isAnimationEnable: Boolean = false,
    @IntRange(from = 1) val animationDuration: Int = 500,
    val strokeWidth: Float = DEFAULT_STROKE_WIDTH,
    val labelFontSize: TextUnit = 24.sp,
    val labelTypeface: Typeface = Typeface.DEFAULT,
    val labelVisible: Boolean = false,
    val labelType: LabelType = LabelType.PERCENTAGE,
    val labelColor: Color = Color.White,
    val labelColorType: LabelColorType = LabelColorType.SPECIFIED_COLOR,
    val activeSliceAlpha: Float = .8f,
    val inActiveSliceAlpha: Float = 1f,
    val isEllipsizeEnabled: Boolean = false,
    val sliceMinTextWidthToEllipsize: Dp = 80.dp,
    val sliceLabelEllipsizeAt: TextUtils.TruncateAt = TextUtils.TruncateAt.END,
    val chartPadding: Int = DEFAULT_PADDING,
    val accessibilityConfig: AccessibilityConfig = AccessibilityConfig(
        chartDescription = PieChartConstants.DESCRIPTION
    ),
    val isSumVisible: Boolean = false,
    val sumUnit: String = "",
    val isClickOnSliceEnabled: Boolean = true
){
    enum class LabelType {
        PERCENTAGE,
        VALUE
    }

    enum class LabelColorType {
        SPECIFIED_COLOR,
        SLICE_COLOR
    }
}
