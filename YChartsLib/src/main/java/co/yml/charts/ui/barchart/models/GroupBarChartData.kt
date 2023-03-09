package co.yml.charts.ui.barchart.models

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.AccessibilityConfig

/**
 * GroupBarGraph data class params used in drawing bar graph.
 * @param barPlotData : Group bar plot data.
 * @param xAxisData: All the configurations related to X-Axis to be defined here in [AxisData]
 * @param yAxisData: All the configurations related to Y-Axis to be defined here in [AxisData]
 * @param horizontalExtraSpace: Extra space added in the horizontal axis.
 * @param backgroundColor: Background color of the graph.
 * @param paddingEnd: End Padding.
 * @param paddingTop: Top Padding.
 * @param showXAxis: Boolean Flag to enable/disable X axis.
 * @param showYAxis: Boolean Flag to enable/disable Y axis.
 * @param tapPadding: Tap padding offset.
 * @param groupSeparatorConfig : All config related to the GroupSeparator.
 * @param accessibilityConfig: Configs related to accessibility service defined here in [AccessibilityConfig]
 */

data class GroupBarChartData(
    val barPlotData: BarPlotData,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val backgroundColor: Color = Color.White,
    val horizontalExtraSpace: Dp = 10.dp,
    val paddingEnd: Dp = 10.dp,
    val paddingTop: Dp = 0.dp,
    val showYAxis: Boolean = true,
    val showXAxis: Boolean = true,
    val tapPadding: Dp = 10.dp,
    val groupSeparatorConfig: GroupSeparatorConfig = GroupSeparatorConfig(),
    val accessibilityConfig: AccessibilityConfig = AccessibilityConfig(),
    val paddingBetweenStackedBars: Dp = 0.dp,
    val drawBar: (DrawScope, GroupBarChartData, BarStyle, Offset, Float, Int) -> Unit = { drawScope, barChartData, barStyle, drawOffset, height, barIndex ->
        //default implementation
        drawGroupBarGraph(
            drawScope,
            barChartData,
            barStyle,
            drawOffset,
            height,
            barPlotData.barColorPaletteList[barIndex],
            barIndex
        )
    }
)

/**
 *
 * Used to draw the individual bar[Used round rec as a default shape]
 * @param drawScope : Creates a scoped drawing environment
 * @param barGraphData : all meta data related to the bar graph
 * @param barStyle : all meta data related to the bar styling
 * @param drawOffset: topLeft offset for the drawing the bar
 * @param height : height of the bar graph
 * @param barIndex : Index of the bar
 */
fun drawGroupBarGraph(
    drawScope: DrawScope,
    barGraphData: GroupBarChartData,
    barStyle: BarStyle,
    drawOffset: Offset,
    height: Float,
    barColor: Color,
    barIndex: Int
) {
    with(drawScope) {
        drawRoundRect(
            color = barColor,
            topLeft = drawOffset,
            size = Size(barStyle.barWidth.toPx(), height),
            cornerRadius = CornerRadius(
                barStyle.cornerRadius.toPx(), barStyle.cornerRadius.toPx()
            ),
            style = barStyle.barDrawStyle,
            blendMode = barStyle.barBlendMode
        )
    }
}
