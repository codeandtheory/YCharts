package co.yml.charts.ui.bubblechart.model

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import co.yml.charts.ui.linechart.model.LineType

/**
 * Bubble style
 *
 * @property gradientColors
 * @property gradientType
 * @property solidColor
 * @property useGradience
 * @property width
 * @property alpha
 * @property style
 * @property colorFilter
 * @property blendMode
 * @constructor Create empty Bubble style
 */
data class BubbleStyle(
    val gradientColors: List<Color> = listOf(Color.Blue, Color.Red),
    val gradientType: BubbleGradientType = BubbleGradientType.HorizontalGradient,
    val tileMode: TileMode = TileMode.Clamp,
    val useGradience: Boolean = false,
    val solidColor: Color = Color.Blue,
    val width: Float = 8f,
    val alpha: Float = 1.0f,
    val style: DrawStyle = Fill,
    val colorFilter: ColorFilter? = null,
    val blendMode: BlendMode = DefaultBlendMode
)


/**
 * Bubble gradient type
 *
 * @constructor Create empty Bubble gradient type
 */
sealed class BubbleGradientType {

    /**
     * Linear gradient
     *
     * @constructor Create empty Linear gradient
     */
    object LinearGradient : BubbleGradientType()

    /**
     * Radial gradient
     *
     * @constructor Create empty Radial gradient
     */
    object RadialGradient : BubbleGradientType()

    /**
     * Vertical gradient
     *
     * @constructor Create empty Vertical gradient
     */
    object VerticalGradient : BubbleGradientType()

    /**
     * Horizontal gradient
     *
     * @constructor Create empty Horizontal gradient
     */
    object HorizontalGradient : BubbleGradientType()

}
