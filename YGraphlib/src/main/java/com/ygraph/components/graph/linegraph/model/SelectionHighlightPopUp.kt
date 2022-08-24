package com.ygraph.components.graph.linegraph.model

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
data class SelectionHighlightPopUp(
    val backgroundColor: Color = Color.White,
    val backgroundAlpha: Float = 0.7f,
    val backgroundCornerRadius: CornerRadius = CornerRadius(5f),
    val backgroundColorFilter: ColorFilter? = null,
    val backgroundBlendMode: BlendMode = DrawScope.DefaultBlendMode,
    val backgroundStyle: DrawStyle = Fill,
    val paddingBetweenPopUpAndPoint: Dp = 20.dp,
    val labelSize: TextUnit = 14.sp,
    val labelColor: Color = Color.Black,
    val labelAlignment: Paint.Align = Paint.Align.CENTER,
    val labelTypeface: Typeface = Typeface.DEFAULT,
    val popUpLabel: (Float, Float) -> (String) = { x, y ->
        val xLabel = "x : ${x.toInt()} "
        val yLabel = "y : ${String.format("%.2f", y)}"
        "$xLabel $yLabel"
    },
    val draw: DrawScope.(Offset, Point) -> Unit = { selectedOffset, identifiedPoint ->
        val highlightTextPaint = TextPaint().apply {
            textSize = labelSize.toPx()
            color = labelColor.toArgb()
            textAlign = labelAlignment
            typeface = labelTypeface
        }
        val label = popUpLabel(identifiedPoint.x, identifiedPoint.y)
        drawContext.canvas.nativeCanvas.apply {
            val background = getTextBackgroundRect(
                selectedOffset.x,
                selectedOffset.y,
                label,
                highlightTextPaint
            )
            drawRoundRect(
                backgroundColor,
                Offset(
                    background.left.toFloat(),
                    background.top.toFloat() - paddingBetweenPopUpAndPoint.toPx()
                ),
                size = Size(background.width().toFloat(), background.height().toFloat()),
                alpha = backgroundAlpha,
                cornerRadius = backgroundCornerRadius,
                colorFilter = backgroundColorFilter,
                blendMode = backgroundBlendMode,
                style = backgroundStyle
            )
            drawText(
                label,
                selectedOffset.x,
                selectedOffset.y - paddingBetweenPopUpAndPoint.toPx(),
                highlightTextPaint
            )
        }
    }
)
