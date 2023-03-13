package co.yml.kmm.charts.ui.linechart.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Represents the point when it is selected on the line graph
 *
 * @param color The color or fill to be applied to the circle
 * @param radius The radius of the circle
 * @param alpha Opacity to be applied to the circle from 0.0f to 1.0f representing
 * fully transparent to fully opaque respectively
 * @param style Whether or not the circle is stroked or filled in
 * @param colorFilter ColorFilter to apply to the [color] when drawn into the destination
 * @param blendMode Blending algorithm to be applied to the brush
 * @param draw override this to change the default drawCircle implementation. You are provided
 * with the actual point [Offset].
 */
data class SelectionHighlightPoint(
    val color: Color = Color.Red,
    val radius: Dp = 6.dp,
    val alpha: Float = 1.0f,
    val style: DrawStyle = Fill,
    val colorFilter: ColorFilter? = null,
    val blendMode: BlendMode = DrawScope.DefaultBlendMode,
    val draw: DrawScope.(Offset) -> Unit = { point ->
        drawCircle(
            color,
            radius.toPx(),
            point,
            alpha,
            style,
            colorFilter,
            blendMode
        )
    },
    val isHighlightLineRequired: Boolean = true,
    val drawHighlightLine: DrawScope.(Offset, Offset) -> Unit = { start, end ->
        drawLine(
            color,
            start,
            end,
            (radius / 2).toPx(),
            Stroke.DefaultCap,
            PathEffect.dashPathEffect(floatArrayOf(40f, 20f)),
            alpha,
            colorFilter,
            blendMode
        )
    }
)
