package co.yml.charts.ui.bubblechart.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import co.yml.charts.ui.linechart.model.ShadowUnderLine


/**
 * Bubble chart data
 *
 * @property bubblePlotData
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
    val bubblePlotData: BubblePlotData,
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
 * @property dataPoint
 * @property density
 * @property bubbleStyle
 * @property intersectionPoint
 * @property selectionHighlightPoint
 * @property selectionHighlightPopUp
 * @constructor Create empty Bubble
 */
data class Bubble(
    val dataPoint: Point,
    val density: Float,
    val bubbleStyle: BubbleStyle = BubbleStyle(),
    val intersectionPoint: BubblePoint? = null,
    val selectionHighlightPoint: SelectionHighlightPoint? = null,
    val selectionHighlightPopUp: SelectionHighlightPopUp? = null,
    val draw: DrawScope.(Offset) -> Unit = { center ->
        drawCircle(
            bubbleStyle.color,
            density.dp.toPx(),
            center,
            bubbleStyle.alpha,
            bubbleStyle.style,
            bubbleStyle.colorFilter,
            bubbleStyle.blendMode
        )
    }
)
