package co.yml.kmm.charts.ui.bubblechart.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.kmm.charts.axis.AxisData
import co.yml.kmm.charts.common.model.AccessibilityConfig
import co.yml.kmm.charts.common.model.Point
import co.yml.kmm.charts.ui.linechart.model.GridLines
import co.yml.kmm.charts.ui.linechart.model.IntersectionPoint
import co.yml.kmm.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.kmm.charts.ui.linechart.model.SelectionHighlightPopUp


/**
 * Bubble chart data
 *
 * @property bubbles
 * @property maximumBubbleRadius
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
    val maximumBubbleRadius:Float = 100f,
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
    val draw: DrawScope.(Offset,Float) -> Unit = { center,maximumRadius ->
        val drawingRadius:Float = (density / maximumRadius) * 100
        if (bubbleStyle.useGradience) {
            drawCircle(
                brush = getBrush(bubbleStyle, center, density),
                center = center,
                radius = drawingRadius,
                alpha = bubbleStyle.alpha,
                style = bubbleStyle.style,
                colorFilter = bubbleStyle.colorFilter,
                blendMode = bubbleStyle.blendMode
            )
        } else {
            drawCircle(
                bubbleStyle.solidColor,
                drawingRadius,
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
    when (bubbleStyle.gradientType) {
        BubbleGradientType.RadialGradient -> {
            return Brush.radialGradient(
                colors = bubbleStyle.gradientColors,
                center = center,
                radius = density,
                tileMode = bubbleStyle.tileMode
            )
        }

        BubbleGradientType.LinearGradient -> {
            return Brush.linearGradient(
                colors = bubbleStyle.gradientColors,
                tileMode = bubbleStyle.tileMode,
                start = center,
                end = center
            )
        }

        BubbleGradientType.VerticalGradient -> {
            return Brush.verticalGradient(
                colors = bubbleStyle.gradientColors,
                tileMode = bubbleStyle.tileMode,
                startY = center.y - density / 2,
                endY = center.y + density / 2,
            )
        }

        BubbleGradientType.HorizontalGradient -> {
            return Brush.horizontalGradient(
                colors = bubbleStyle.gradientColors,
                tileMode = bubbleStyle.tileMode,
                startX = center.x - density / 2,
                endX = center.x + density / 2,
            )
        }
    }


}
