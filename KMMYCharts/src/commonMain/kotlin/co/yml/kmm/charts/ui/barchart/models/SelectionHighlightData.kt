package co.yml.kmm.charts.ui.barchart.models

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.kmm.charts.common.extensions.getTextBackgroundRect

/**
 * Used to customise the highlighted text and the bar
 *
 * @param highlightTextOffset: Padding between the highlighted bar and the text
 * @param highlightTextSize: Text size of the highlighted bar text
 * @param highlightTextColor: Text color of the highlighted bar text
 * @param highlightTextBackgroundColor: Background color of the highlight background
 * @param highlightTextBackgroundAlpha: Alpha for the highlighted text background
 * @param highlightBarCornerRadius: Corner radius of the highlighted bar
 * @param highlightBarColor: Color of the highlighted bar
 * @param highlightBarStrokeWidth: Stroke width of the highlighted bar
 * @param highlightPopUpCornerRadius: Corner radius of the highlighted background
 * @param backgroundColorFilter: ColorFilter to apply to the color when drawn into the destination.
 * @param backgroundBlendMode:Blending algorithm to be applied to the path when it is drawn.
 * @param backgroundStyle: Whether or not the path is stroked or filled in.
 * @param isHighlightBarRequired: Boolean flag to enable disable highlight
 * @param popUpLabel : The text that can be shown on the popup given 2 input params x and y values
 * @param drawPopUp: Override this to change the default background implementation. You are provided
 * with the selected offset, x, y values, center point of bar.
 * @param drawHighlightBar: draw override this to change the default drawRoundRect implementation. You are provided
 * with the actual point x, y, height, width.
 * @param groupBarPopUpLabel: Popup label for selected bar in group bar chart.
 * @param drawGroupBarPopUp: draw override this to change the default popup implementation. You are provided
 * with the actual point x, y, height, width.
 */
data class SelectionHighlightData @OptIn(ExperimentalTextApi::class) constructor(
    //highlight text 
    val highlightTextOffset: Dp = 15.dp,
    val highlightTextSize: TextUnit = 12.sp,
    val highlightTextColor: Color = Color.Black,
    val highlightTextBackgroundColor: Color = Color.Yellow,
    val highlightTextBackgroundAlpha: Float = 0.7f,
    // highlight bar
    val highlightBarCornerRadius: Dp = 2.dp,
    val highlightBarColor: Color = Color.Black,
    val highlightBarStrokeWidth: Dp = 2.dp,

    val highlightPopUpCornerRadius: CornerRadius = CornerRadius(5f),
    val backgroundColorFilter: ColorFilter? = null,
    val backgroundBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val backgroundStyle: DrawStyle = Fill,
    val isHighlightBarRequired: Boolean = true,
    val popUpLabel: (Float, Float) -> (String) = { x, y ->
        val xLabel = "x : ${x.toInt()} "
        val yLabel = "y : $y"
        "$xLabel $yLabel"
    },

    val drawPopUp: DrawScope.(Offset, BarData, Float, TextMeasurer) -> Unit = { selectedOffset, identifiedPoint, centerPointOfBar, textMeasure ->
        val label = popUpLabel(identifiedPoint.point.x, identifiedPoint.point.y)
        val labelHeight = textMeasure.measure(text = AnnotatedString(label)).size.height
        val labelWidth = textMeasure.measure(text = AnnotatedString(label)).size.width
        drawContext.canvas.nativeCanvas.apply {
            val background = getTextBackgroundRect(
                centerPointOfBar,
                selectedOffset.y,
                label,
                Paint()
            )
            drawRoundRect(
                color = highlightTextBackgroundColor,
                topLeft = Offset(
                    background.left,
                    background.top - highlightTextOffset.toPx()
                ),
                alpha = highlightTextBackgroundAlpha,
                cornerRadius = highlightPopUpCornerRadius,
                colorFilter = backgroundColorFilter,
                blendMode = backgroundBlendMode,
                style = backgroundStyle,
                size = Size(labelWidth.toFloat(), labelHeight.toFloat())
            )
            drawText(
                textMeasurer = textMeasure,
                text = label,
                topLeft = Offset(centerPointOfBar, selectedOffset.y - highlightTextOffset.toPx())
            )
        }
    },

    val drawHighlightBar: DrawScope.(Float, Float, Float, Float) -> Unit = { x, y, width, height ->
        drawRoundRect(
            color = highlightBarColor,
            topLeft = Offset(x, y),
            size = Size(width, height),
            cornerRadius = CornerRadius(
                highlightBarCornerRadius.toPx(),
                highlightBarCornerRadius.toPx()
            ),
            style = Stroke(width = highlightBarStrokeWidth.toPx())
        )
    },

    val groupBarPopUpLabel: (String, Float) -> (String) = { name, value ->
        val xLabel = "Name : $name "
        val yLabel = "Value : $value}"
        "$xLabel $yLabel"
    },


    val drawGroupBarPopUp: DrawScope.(Offset, BarData, Float, TextMeasurer) -> Unit = { selectedOffset, identifiedPoint, centerPointOfBar, textMeasure ->
        val xLabel = "B${identifiedPoint.point.x.toInt()}"
        val label = groupBarPopUpLabel(xLabel, identifiedPoint.point.y)
        drawContext.canvas.nativeCanvas.apply {
            val background = getTextBackgroundRect(
                centerPointOfBar,
                selectedOffset.y,
                label,
                Paint()
            )
            drawRoundRect(
                color = highlightTextBackgroundColor,
                topLeft = Offset(
                    background.left,
                    background.top - highlightTextOffset.toPx()
                ),
                alpha = highlightTextBackgroundAlpha,
                cornerRadius = highlightPopUpCornerRadius,
                colorFilter = backgroundColorFilter,
                blendMode = backgroundBlendMode,
                style = backgroundStyle
            )
            drawText(
                textMeasurer = textMeasure,
                text = label,
                topLeft = Offset(centerPointOfBar, selectedOffset.y - highlightTextOffset.toPx())
            )
        }
    }
)
