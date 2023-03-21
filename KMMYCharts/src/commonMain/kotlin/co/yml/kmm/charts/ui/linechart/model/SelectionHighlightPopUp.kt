package co.yml.kmm.charts.ui.linechart.model

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.kmm.charts.common.extensions.getTextBackgroundRect
import co.yml.kmm.charts.common.model.Point

/**
 * SelectionHighlightPopUp is a data class used to draw the pop on the given selected point on a line
 * to identify the dimensions of the selected point.All the styling related params are included here
 * @param backgroundColor : Defines the background color of the popup.
 * @param backgroundAlpha : Defines the alpha of the background color.
 * @param backgroundCornerRadius : Defines the corner radius of the background.
 * @param backgroundColorFilter : ColorFilter to apply to the color when drawn into the destination.
 * @param backgroundBlendMode :Blending algorithm to be applied to the path when it is drawn.
 * @param backgroundStyle : Whether or not the path is stroked or filled in.
 * @param labelSize : The size of the popUp label in [TextUnit].
 * @param labelColor : The color of the label text.
 * @param labelAlignment : The alignment of the label text.
 * @param labelTypeface : The style of the label text.
 * @param paddingBetweenPopUpAndPoint : The padding between the anchor position/ popup
 * start position and the selected point on the line.
 * @param popUpLabel : The text that can be shown on the popup given 2 input params x and y values
 * @param draw : Draw the popUp marker on the selected point with 2 inputs [Offset] i.e selectedPoint
 * and [Point] i.e the input data w.r.t selected point
 */
data class SelectionHighlightPopUp @OptIn(ExperimentalTextApi::class) constructor(
    val backgroundColor: Color = Color.White,
    val backgroundAlpha: Float = 0.7f,
    val backgroundCornerRadius: CornerRadius = CornerRadius(5f),
    val backgroundColorFilter: ColorFilter? = null,
    val backgroundBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val backgroundStyle: DrawStyle = Fill,
    val paddingBetweenPopUpAndPoint: Dp = 20.dp,
    val labelSize: TextUnit = 14.sp,
    val labelColor: Color = Color.Black,
    val labelAlignment: LineHeightStyle.Alignment = LineHeightStyle.Alignment.Center,
    val popUpLabel: (Float, Float) -> (String) = { x, y ->
        val xLabel = "x : ${x.toInt()} "
        val yLabel = "y : ${y.toInt()}"
        "$xLabel $yLabel"
    },
    val draw: DrawScope.(Offset, Point, TextMeasurer) -> Unit = { selectedOffset, identifiedPoint, textMeasure ->
        val label = popUpLabel(identifiedPoint.x, identifiedPoint.y)
        val labelHeight = textMeasure.measure(text = AnnotatedString(label)).size.height
        val labelWidth = textMeasure.measure(text = AnnotatedString(label)).size.width
        drawContext.canvas.nativeCanvas.apply {
            val background = getTextBackgroundRect(
                selectedOffset.x,
                selectedOffset.y,
                label,
                Paint()
            )
            drawRoundRect(
                color = backgroundColor,
                topLeft = Offset(
                    background.left.toFloat(),
                    background.top.toFloat() - paddingBetweenPopUpAndPoint.toPx()
                ),
                size = Size(labelWidth.toFloat(), labelHeight.toFloat()),
                alpha = backgroundAlpha,
                cornerRadius = backgroundCornerRadius,
                colorFilter = backgroundColorFilter,
                blendMode = backgroundBlendMode,
                style = backgroundStyle
            )
            drawText(
                textMeasurer = textMeasure,
                text = label,
                topLeft = Offset(selectedOffset.x, selectedOffset.y - paddingBetweenPopUpAndPoint.toPx())
            )

        }
    }
)
