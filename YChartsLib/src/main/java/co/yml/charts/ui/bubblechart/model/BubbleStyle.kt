package co.yml.charts.ui.bubblechart.model

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.DrawStyle
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
    val lineType: BubbleType = BubbleType.SmoothCurve(isDotted = false),
    val color: Color = Color.Black,
    val width: Float = 8f,
    val alpha: Float = 1.0f,
    val style: DrawStyle = Stroke(width = width),
    val colorFilter: ColorFilter? = null,
    val blendMode: BlendMode = DefaultBlendMode
)

/**
 * Represent different types of line to be drawn
 * @see LineType.Straight Draws straight lines with no curves, just a connection from
 * one point to another.
 * @see LineType.SmoothCurve Draws curved lines using cubic paths
 * It has @param isDotted true if line has to be dotted else false.
 * Also @param intervals Array of "on" and "off" distances for the dashed line segments
 * phase - Pixel offset into the intervals array
 */
sealed class BubbleType {
    abstract val isDotted: Boolean
    abstract var intervals: FloatArray

    data class Straight(
        override val isDotted: Boolean = false,
        override var intervals: FloatArray = floatArrayOf(30f, 10f)
    ) : BubbleType()

    data class SmoothCurve(
        override val isDotted: Boolean = false,
        override var intervals: FloatArray = floatArrayOf(30f, 10f)
    ) : BubbleType()
}
