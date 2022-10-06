package com.ygraph.components.piechart.models

import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.IntRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.piechart.PieChartConstants.DEFAULT_PADDING
import com.ygraph.components.piechart.PieChartConstants.DEFAULT_SLICE_LABEL_TEXT_SIZE
import com.ygraph.components.piechart.PieChartConstants.DEFAULT_START_ANGLE
import com.ygraph.components.piechart.PieChartConstants.DEFAULT_STROKE_WIDTH

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
 * @param percentageFontSize: Percentage text font size
 * @param percentageTypeface: Percentage text typeface
 * @param percentVisible: Percentage text visibility
 * @param percentColor: Percentage text color
 * @param activeSliceAlpha: Opacity of the active slice
 * @param inActiveSliceAlpha: Opacity of the inactive slice
 * @param isEllipsizeEnabled: Boolean flag for enabling ellipsize
 * @param sliceMinTextWidthToEllipsize: Minimum width of the label post which label will be ellipsized
 * @param sliceLabelEllipsizeAt: Position at which the label will be truncated or ellipsized
 * @param chartPadding: Padding for the Pie chart/Donut Chart
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
    val percentageFontSize: TextUnit = 24.sp,
    val percentageTypeface: Typeface = Typeface.DEFAULT,
    val percentVisible: Boolean = false,
    val percentColor: Color = Color.White,
    val activeSliceAlpha: Float = .8f,
    val inActiveSliceAlpha: Float = 1f,
    val isEllipsizeEnabled: Boolean = false,
    val sliceMinTextWidthToEllipsize: Dp = 80.dp,
    val sliceLabelEllipsizeAt: TextUtils.TruncateAt = TextUtils.TruncateAt.END,
    val chartPadding: Int = DEFAULT_PADDING
)
