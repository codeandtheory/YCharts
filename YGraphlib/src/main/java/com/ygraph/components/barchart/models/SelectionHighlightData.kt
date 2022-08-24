package com.ygraph.components.barchart.models

import android.graphics.Typeface
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
 * @param isHighlightBarRequired: Boolean flag to enable disable highlight
 * @param drawHighlightBar: draw override this to change the default drawRoundRect implementation. You are provided
 * with the actual point x, y, height, width.

 */
data class SelectionHighlightData(
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

    val isHighlightBarRequired: Boolean = true,
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
    }
)
