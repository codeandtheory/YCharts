package com.ygraph.components.axis

import android.graphics.Paint
import android.graphics.PointF
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.ygraph.components.common.extensions.getTextHeight
import com.ygraph.components.common.extensions.getTextWidth
import kotlin.math.ceil

/**
 *
 * XAxis compose method used for drawing xAxis in any given graph.
 * @param axisData : All data needed to draw Yaxis
 * @see com.ygraph.components.axis.AxisData Data class to save all params related to axis
 * @param modifier : All modifier related property.
 * @param xStart : Start position of xAxis.
 * @param scrollOffset : Offset of delta scrolled position.
 * @param zoomScale : Scale at which zoom transformation being applied.
 * @param chartData : List of data points used in the graph.
 */
@Composable
fun XAxis(
    axisData: AxisData,
    modifier: Modifier,
    xStart: Float,
    scrollOffset: Float,
    zoomScale: Float,
    chartData: List<PointF>
) {
    with(axisData) {
        Row(modifier = modifier.clipToBounds()) {
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp) // Need to calculate the height dynamically
            ) {
                val xAxisTextPaint = TextPaint().apply {
                    textSize = axisLabelFontSize.toPx()
                    color = yAxisLineColor.toArgb()
                    textAlign = Paint.Align.LEFT
                    typeface = axisData.typeface
                }
                val (xMin, xMax, xAxisScale) = getXAxisScale(chartData, xAxisSteps)
                var xPos = xStart - scrollOffset
                for (it in 0 until xAxisSteps + 1) {
                    val xLabel = (it * xAxisScale).toInt().toString() // xLabelData()
                    val labelHeight = xLabel.getTextHeight(xAxisTextPaint)
                    val labelWidth = xLabel.getTextWidth(xAxisTextPaint)

                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            xLabel,
                            xPos - (labelWidth / 2),
                            labelHeight / 2 + indicatorLineWidth.toPx() + xTopPadding.toPx(),
                            xAxisTextPaint
                        )
                    }

                    drawLine(
                        Color.Black,
                        Offset(xPos, 0f),
                        Offset(xPos + ((xAxisStepSize.toPx() * (zoomScale * xAxisScale))), 0f),
                        strokeWidth = lineStrokeWidth.toPx()
                    )
                    drawLine(
                        Color.Black,
                        Offset(xPos, 0f),
                        Offset(xPos, indicatorLineWidth.toPx()),
                        strokeWidth = lineStrokeWidth.toPx()
                    )
                    xPos += ((xAxisStepSize.toPx() * (zoomScale * xAxisScale)))
                }
            }
        }
    }
}

fun getXAxisScale(
    points: List<PointF>,
    steps: Int,
): Triple<Float, Float, Float> {
    val xMin = points.minOf { it.x }
    val xMax = points.maxOf { it.x }
    val totalSteps = (xMax - xMin)
    val temp = totalSteps / steps
    val scale = ceil(temp)
    return Triple(xMin, xMax, scale)
}
