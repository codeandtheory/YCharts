package co.yml.charts.ui.barchart.models


import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.AccessibilityConfig


/**
 * BarGraph data class params used in drawing bar graph.
 * @param chartData : List of BarData
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param horizontalExtraSpace: Extra space added in the horizontal axis
 * @param paddingEnd: End Padding
 * @param paddingTop: Top Padding
 * @param tapPadding: Extra padding area for tapping
 * @param showXAxis: Boolean Flag to enable/disable X axis
 * @param showYAxis: Boolean Flag to enable/disable Y axis
 * @param accessibilityConfig: Configs related to accessibility service defined here in [AccessibilityConfig]
 */
data class BarChartData(
    val chartData: List<BarData>,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val backgroundColor: Color = Color.White,
    val horizontalExtraSpace: Dp = 0.dp,
    val barStyle: BarStyle = BarStyle(),
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val tapPadding: Dp = 10.dp,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
    val accessibilityConfig: AccessibilityConfig = AccessibilityConfig(),
    val barChartType: BarChartType = BarChartType.VERTICAL,
    val drawBar: (DrawScope, BarData, Offset, Float, BarChartType, BarStyle) -> Unit = { drawScope, barChartData, drawOffset, height, barChartType, barStyle ->
        //default implementation
        drawBarGraph(drawScope, barChartData, drawOffset, height, barChartType, barStyle)
    }
)

/**
 * BarStyle data class adds styling related to each bar in the bar graph
 * @param barWidth: Width of a bar
 * @param cornerRadius: Corner radius for the bars
 * @param paddingBetweenBars: Space between adjacent bars
 * @param isGradientEnabled: Boolean Flag to enable/disable gradient bars
 * @param barBlendMode: Blend mode for the bars
 * @param barDrawStyle: Draw style for the bars
 * @param drawBar : Draw an individual bar
 */
data class BarStyle(
    val barWidth: Dp = 30.dp,
    val cornerRadius: Dp = 4.dp,
    val paddingBetweenBars: Dp = 15.dp,
    val isGradientEnabled: Boolean = false,
    val barBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val barDrawStyle: DrawStyle = Fill,
    val selectionHighlightData: SelectionHighlightData? = SelectionHighlightData()
)

/**
 *
 * Used to draw the individual bars
 * @param drawScope : Creates a scoped drawing environment
 * @param barStyle : all meta data related to the bar styling
 * @param barData : data related to a single bar
 * @param drawOffset: top left offset for the drawing the bar
 * @param height : height of the bar graph
 * @param barChartType : type of bar chart
 */
fun drawBarGraph(
    drawScope: DrawScope,
    barData: BarData,
    drawOffset: Offset,
    height: Float,
    barChartType: BarChartType,
    barStyle: BarStyle
) {
    with(drawScope) {
        with(barStyle) {
            // Draw bar lines
            if (isGradientEnabled) {
                val brush = Brush.verticalGradient(
                    colors = barData.gradientColorList
                )
                drawRoundRect(
                    brush = brush,
                    topLeft = drawOffset,
                    size = if (barChartType == BarChartType.VERTICAL) Size(
                        barWidth.toPx(),
                        height
                    ) else Size(height, barWidth.toPx()),
                    cornerRadius = CornerRadius(
                        cornerRadius.toPx(), cornerRadius.toPx()
                    ),
                    style = barDrawStyle,
                    blendMode = barBlendMode
                )
            } else {
                drawRoundRect(
                    color = barData.color,
                    topLeft = drawOffset,
                    size = if (barChartType == BarChartType.VERTICAL) Size(
                        barWidth.toPx(),
                        height
                    ) else Size(height, barWidth.toPx()),
                    cornerRadius = CornerRadius(
                        cornerRadius.toPx(), cornerRadius.toPx()
                    ),
                    style = barDrawStyle,
                    blendMode = barBlendMode
                )
            }
        }
    }
}
