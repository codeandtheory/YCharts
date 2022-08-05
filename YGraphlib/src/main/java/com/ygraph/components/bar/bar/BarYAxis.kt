package com.ygraph.components.bar

import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 * yAxis compose method using for drawing yAxis in bar graph.
 * @param modifier : All modifier related property
 * @param yMaxValue: yAxis max value
 * @param yStepValue: Step value for label segmentation
 * @param labelOffSet: X,Y Label offset bottom padding
 * @param yLabelData(Int)->String: lambda method for providing labels,
 * @param axisLabelFontSize: Font size of axis lablel data
 * @param axisPos : Axis gravity
 * @param textLabelPadding: Text label padding from y Axis
 * @param yAxisOffset: Drawing offset for yAxis.
 * @param lineStrokeWidth: Thickness of yAxis line
 */
@Composable
fun YAxis(
    modifier: Modifier,
    yMaxValue: Float,
    yStepValue: Float,
    labelOffSet: Dp,
    yLabelData: (Int) -> String,
    yAxisLineColor: Color = Color.Black,
    axisLabelFontSize: TextUnit = 14.sp,
    axisPos: Gravity = Gravity.LEFT,
    textLabelPadding: Dp = 4.dp,
    yAxisOffset: Dp = 20.dp,
    lineStrokeWidth: Dp = 1.dp
) {
    var yAxisWidth by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .width(yAxisWidth)
            .then(modifier)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width(yAxisWidth)
        )
        {
            val yAxisHeight = size.height - labelOffSet.roundToPx()
            var yMax = yMaxValue
            var reqYLabelsQuo = (yMaxValue / yStepValue)
            val reqYLabelsRem = yMaxValue.rem(yStepValue)
            if (reqYLabelsRem > 0f) {
                reqYLabelsQuo += 2
                yMax = (yMaxValue - reqYLabelsRem) + yStepValue * 2
            }
            val segmentHeight = yAxisHeight / yMax

            val yAxisTextPaint = TextPaint().apply {
                textSize = axisLabelFontSize.toPx()
                color = Color.Black.toArgb()
                textAlign = if (axisPos == Gravity.LEFT) Paint.Align.LEFT else Paint.Align.RIGHT
            }
            for (i in 0 until reqYLabelsQuo.toInt()) {
                //Draw the lines
                drawLine(
                    start = Offset(
                        x = yAxisWidth.toPx() - 3.dp.toPx(),
                        y = yAxisHeight - (segmentHeight * (i * yStepValue))
                    ),
                    end = Offset(
                        x = yAxisWidth.toPx() - 3.dp.toPx(),
                        y = yAxisHeight - (segmentHeight * ((i + 1) * yStepValue))
                    ),
                    color = yAxisLineColor, strokeWidth = lineStrokeWidth.toPx()
                )
                //drawing the text
                drawContext.canvas.nativeCanvas.apply {
                    val yAxisLabel = yLabelData(i)
                    val width = yAxisLabel.getTextWidth(yAxisTextPaint)
                    val height: Int = yAxisLabel.getTextHeight(yAxisTextPaint)
                    if (width > yAxisWidth.toPx()) {
                        yAxisWidth = width.toDp() + yAxisOffset
                    }
                    drawText(
                        yAxisLabel,
                        textLabelPadding.toPx(),
                        yAxisHeight + height / 2 - ((segmentHeight * (i * yStepValue))),
                        yAxisTextPaint
                    )
                }
            }
        }
    }
}

/**
return the width of text in canvas drawn text
 */
fun String.getTextWidth(paint: Paint): Float {
    return paint.measureText(this)
}

/**
return the height of text in canvas drawn text
 */
fun String.getTextHeight(paint: Paint): Int {
    val bounds = Rect()
    paint.getTextBounds(
        this,
        0,
        this.length,
        bounds
    )
    return bounds.height()
}
