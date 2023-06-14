package co.yml.charts.ui.bubblechart.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.AccessibilityConfig
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp


/**
 * Bubble chart data
 *
 * @property bubbles
 * @property xAxisData
 * @property yAxisData
 * @property isZoomAllowed
 * @property paddingTop
 * @property bottomPadding
 * @property paddingRight
 * @property containerPaddingEnd
 * @property backgroundColor
 * @property gridLines
 * @property accessibilityConfig
 * @constructor Create empty Bubble chart data
 */
data class BubbleChartData(
    val bubbles: List<Bubble>,
    val xAxisData: AxisData = AxisData.Builder().build(),
    val yAxisData: AxisData = AxisData.Builder().build(),
    val isZoomAllowed: Boolean = true,
    val paddingTop: Dp = 30.dp,
    val bottomPadding: Dp = 10.dp,
    val paddingRight: Dp = 10.dp,
    val containerPaddingEnd: Dp = 15.dp,
    val backgroundColor: Color = Color.White,
    val gridLines: GridLines? = null,
    val accessibilityConfig: AccessibilityConfig = AccessibilityConfig()
)

/**
 * Bubble
 *
 * @property center
 * @property density
 * @property bubbleStyle
 * @property intersectionPoint
 * @property selectionHighlightPoint
 * @property selectionHighlightPopUp
 * @property draw
 * @constructor Create empty Bubble
 */
data class Bubble(
    val center: Point,
    val density: Float,
    val bubbleStyle: BubbleStyle = BubbleStyle(),
    val intersectionPoint: IntersectionPoint? = null,
    val selectionHighlightPoint: SelectionHighlightPoint? = null,
    val selectionHighlightPopUp: SelectionHighlightPopUp? = null,
    val draw: DrawScope.(Offset) -> Unit = { center ->
        if (bubbleStyle.useGradience) {
            drawCircle(
                brush = getBrush(bubbleStyle, center, density),
                center = center,
                radius = density,
                alpha = bubbleStyle.alpha,
                style = bubbleStyle.style,
                colorFilter = bubbleStyle.colorFilter,
                blendMode = bubbleStyle.blendMode
            )
        } else {
            drawCircle(
                bubbleStyle.solidColor,
                density,
                center,
                bubbleStyle.alpha,
                bubbleStyle.style,
                bubbleStyle.colorFilter,
                bubbleStyle.blendMode
            )
        }
    }
)

private fun getBrush(bubbleStyle: BubbleStyle, center: Offset, density: Float): Brush {
    return Brush.radialGradient(
        colors = bubbleStyle.gradientColors,
        center = center,
        radius = density,
        tileMode = TileMode.Decal
    )
}
