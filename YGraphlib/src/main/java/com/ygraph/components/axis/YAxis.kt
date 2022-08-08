package com.ygraph.components.axis

import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.common.extensions.getTextHeight
import com.ygraph.components.common.extensions.getTextWidth

/**
 *
 * YAxis compose method using for drawing yAxis in bar graph.
 * @param yAxisData : All data needed to draw Yaxis
 * @see com.ygraph.components.axis.YAxisData Data class to save all params related to Yaxis
 */
@Composable
fun YAxis(yAxisData: YAxisData) {
    with(yAxisData) {
        var yAxisWidth by remember { mutableStateOf(0.dp) }
        val isRightAligned = axisPos == Gravity.RIGHT
        Column(modifier = modifier.clipToBounds()) {
            Canvas(
                modifier = modifier
                    .clipToBounds()
                    .width(yAxisWidth)
                    .background(Color.White)
            ) {
                val yAxisHeight = size.height - bottomPadding.roundToPx()
                var yMax = yMaxValue
                var reqYLabelsQuo = (yMaxValue / yStepValue) + 1 // Added one since it starts from 0
                val reqYLabelsRem = yMaxValue.rem(yStepValue)
                if (reqYLabelsRem > 0f) {
                    reqYLabelsQuo += 2
                    yMax = (yMaxValue - reqYLabelsRem) + yStepValue * 2
                }
                // Minus the top padding to avoid cropping at the top
                val segmentHeight = (yAxisHeight - topPadding.toPx()) / yMax

                val yAxisTextPaint = TextPaint().apply {
                    textSize = axisLabelFontSize.toPx()
                    color = yAxisLineColor.toArgb()
                    textAlign = if (isRightAligned) Paint.Align.RIGHT else Paint.Align.LEFT
                }
                for (i in 0 until reqYLabelsQuo.toInt()) {
                    //Drawing the axis labels
                    yAxisWidth = drawAxisLabel(
                        i,
                        reqYLabelsQuo,
                        yAxisData,
                        yAxisTextPaint,
                        yAxisWidth,
                        isRightAligned,
                        yAxisHeight,
                        segmentHeight
                    )
                    drawAxisLineWithPointers(
                        yAxisData,
                        i,
                        reqYLabelsQuo,
                        isRightAligned,
                        yAxisWidth,
                        yAxisHeight,
                        segmentHeight
                    )
                }
            }
        }
    }
}


private fun DrawScope.drawAxisLineWithPointers(
    yAxisData: YAxisData,
    i: Int,
    reqYLabelsQuo: Float,
    isRightAligned: Boolean,
    yAxisWidth: Dp,
    yAxisHeight: Float,
    segmentHeight: Float
) {
    if (yAxisData.axisConfig.isAxisLineRequired) {
        // Draw line only until reqYLabelsQuo -1 else will be a extra line at top with no label
        if (i != (reqYLabelsQuo.toInt() - 1)) {
            //Draw Yaxis line
            drawLine(
                start = Offset(
                    x = if (isRightAligned) 0.dp.toPx() else yAxisWidth.toPx(),
                    y = yAxisHeight - (segmentHeight * (i * yAxisData.yStepValue))
                ),
                end = Offset(
                    x = if (isRightAligned) 0.dp.toPx() else yAxisWidth.toPx(),
                    y = yAxisHeight - (segmentHeight * ((i + 1) * yAxisData.yStepValue))
                ),
                color = yAxisData.yAxisLineColor, strokeWidth = yAxisData.lineStrokeWidth.toPx()
            )
        }

        //Draw pointer lines on Yaxis
        drawLine(
            start = Offset(
                x = if (isRightAligned) 0.dp.toPx() else {
                    yAxisWidth.toPx() - yAxisData.indicatorLineWidth.toPx()
                },
                y = yAxisHeight - (segmentHeight * (i * yAxisData.yStepValue))
            ),
            end = Offset(
                x = if (isRightAligned) yAxisData.indicatorLineWidth.toPx() else yAxisWidth.toPx(),
                y = yAxisHeight - (segmentHeight * (i * yAxisData.yStepValue))
            ),
            color = yAxisData.yAxisLineColor, strokeWidth = yAxisData.lineStrokeWidth.toPx()
        )
    }
}


private fun DrawScope.drawAxisLabel(
    index: Int,
    reqYLabelsQuo: Float,
    yAxisData: YAxisData,
    yAxisTextPaint: TextPaint,
    yAxisWidth: Dp,
    isRightAligned: Boolean,
    yAxisHeight: Float,
    segmentHeight: Float
): Dp {
    var calculatedYAxisWidth = yAxisWidth
    drawContext.canvas.nativeCanvas.apply {
        if (index != reqYLabelsQuo.toInt()) {
            val yAxisLabel = yAxisData.yLabelData(index)
            val width = yAxisLabel.getTextWidth(yAxisTextPaint)
            val height: Int = yAxisLabel.getTextHeight(yAxisTextPaint)
            if (width > calculatedYAxisWidth.toPx()) {
                calculatedYAxisWidth = width.toDp() + yAxisData.yAxisOffset
            }
            drawText(
                yAxisLabel,
                if (isRightAligned) calculatedYAxisWidth.toPx() - yAxisData.textLabelPadding.toPx() else {
                    yAxisData.textLabelPadding.toPx()
                },
                yAxisHeight + height / 2 - ((segmentHeight * (index * yAxisData.yStepValue))),
                yAxisTextPaint
            )
        }
    }
    return calculatedYAxisWidth
}

@Preview(showBackground = true)
@Composable
fun YAxisPreview() {
    val yAxisData = YAxisData.Builder()
        .modifier(Modifier.height(300.dp))
        .yMaxValue(800f)
        .yStepValue(100f)
        .bottomPadding(10.dp)
        .axisPos(Gravity.LEFT)
        .axisLabelFontSize(14.sp)
        .yLabelData { index -> index.toString() }
        .build()
    YAxis(yAxisData = yAxisData)
}

