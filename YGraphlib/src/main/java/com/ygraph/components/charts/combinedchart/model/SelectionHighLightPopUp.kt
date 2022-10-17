package com.ygraph.components.charts.combinedchart.model

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.common.extensions.getTextBackgroundRect
import com.ygraph.components.common.model.Point
import com.ygraph.components.charts.barchart.models.BarData

/**
 * Used to customise the highlighted text and the bar
 *
 * @param highlightTextOffset: Padding between the highlighted bar and the text
 * @param highlightTextSize: Text size of the highlighted bar text
 * @param highlightTextColor: Text color of the highlighted bar text
 * @param highlightTextBackgroundColor: Background color of the highlight background
 * @param highlightTextBackgroundAlpha: Alpha for the highlighted text background
 * @param highlightTextTypeface: Typeface of the highlighted bar text
 * @param highlightBarCornerRadius: Corner radius of the highlighted bar
 * @param highlightBarColor: Color of the highlighted bar
 * @param highlightBarStrokeWidth: Stroke width of the highlighted bar
 * @param highlightPopUpCornerRadius: Corner radius of the highlighted background
 * @param backgroundColorFilter: ColorFilter to apply to the color when drawn into the destination.
 * @param backgroundBlendMode:Blending algorithm to be applied to the path when it is drawn.
 * @param backgroundStyle: Whether or not the path is stroked or filled in.
 * @param isHighlightBarRequired: Boolean flag to enable disable highlight
 * @param popUpLabel : The text that can be shown on the popup given 3 input params x and y1 & y2 values
 * @param drawPopUp: Override this to change the default background implementation. You are provided
 * with the selected offset, x, y values, center point.
 */
data class SelectionHighLightPopUp(
    //highlight text
    val highlightTextOffset: Dp = 15.dp,
    val highlightTextSize: TextUnit = 12.sp,
    val highlightTextColor: Color = Color.Black,
    val highlightTextBackgroundColor: Color = Color.Yellow,
    val highlightTextBackgroundAlpha: Float = 0.7f,
    val highlightTextTypeface: Typeface = Typeface.DEFAULT,
    // highlight bar
    val highlightBarCornerRadius: Dp = 2.dp,
    val highlightBarColor: Color = Color.Black,
    val highlightBarStrokeWidth: Dp = 2.dp,

    val highlightPopUpCornerRadius: CornerRadius = CornerRadius(5f),
    val backgroundColorFilter: ColorFilter? = null,
    val backgroundBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val backgroundStyle: DrawStyle = Fill,
    val highlightLabelAlignment: Paint.Align = Paint.Align.CENTER,
    val isHighlightBarRequired: Boolean = true,
    val popUpLabel: (Float, Float, Float) -> (String) = { x, y1, y2 ->
        val xLabel = "x : ${x.toInt()} "
        val y1Label = " Point, y : ${String.format("%.2f", y1)}"
        val y2Label = " Bar, y : ${String.format("%.2f", y2)}"
        "$xLabel $y1Label $y2Label"
    },

    val drawPopUp: DrawScope.(Offset, Point, BarData, Float) -> Unit = { selectedOffset,
                                                                         identifiedLinePoint,
                                                                         identifiedBarPoint,
                                                                         centerPoint ->
        val highlightTextPaint = TextPaint().apply {
            textSize = highlightTextSize.toPx()
            color = highlightTextColor.toArgb()
            textAlign = highlightLabelAlignment
            typeface = highlightTextTypeface
        }
        val label = popUpLabel(
            identifiedBarPoint.point.x,
            identifiedLinePoint.y,
            identifiedBarPoint.point.y
        )
        drawContext.canvas.nativeCanvas.apply {
            val background = getTextBackgroundRect(
                centerPoint,
                selectedOffset.y,
                label,
                highlightTextPaint
            )
            drawRoundRect(
                color = highlightTextBackgroundColor,
                topLeft = Offset(
                    background.left.toFloat(),
                    background.top.toFloat() - highlightTextOffset.toPx()
                ),
                size = Size(background.width().toFloat(), background.height().toFloat()),
                alpha = highlightTextBackgroundAlpha,
                cornerRadius = highlightPopUpCornerRadius,
                colorFilter = backgroundColorFilter,
                blendMode = backgroundBlendMode,
                style = backgroundStyle
            )
            drawText(
                label,
                centerPoint,
                selectedOffset.y - highlightTextOffset.toPx(),
                highlightTextPaint
            )
        }
    }
)
