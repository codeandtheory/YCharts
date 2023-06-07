package co.yml.charts.ui.bubblechart.model

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import co.yml.charts.ui.linechart.model.LineType

/**
 * Handles styling for the path drawn in the line graph
 *
 * @param color Defines the color of the path or line.
 * @param width Defines the width of the path/line stroke.
 * @param alpha Defines the alpha of the path/line.
 * @param style Defines if the path/line is filled or stroke.
 * @param colorFilter ColorFilter to apply to the [color] when drawn into the destination.
 * @param blendMode All prams related to selection popup to be added here in [SelectionHighlightPopUp]
 */
data class BubbleStyle(
    val color: Color = Color.Blue,
    val width: Float = 8f,
    val alpha: Float = 1.0f,
    val style: DrawStyle = Fill,
    val colorFilter: ColorFilter? = null,
    val blendMode: BlendMode = DefaultBlendMode
)