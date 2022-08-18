package com.ygraph.components.axis

import android.graphics.Paint
import android.graphics.PointF
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
        var xAxisHeight by remember { mutableStateOf(0.dp) }
        Row(modifier = modifier.clipToBounds()) {
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(xAxisHeight)
            ) {
                val xAxisTextPaint = TextPaint().apply {
                    textSize = axisLabelFontSize.toPx()
                    color = axisLineColor.toArgb()
                    textAlign = Paint.Align.LEFT
                    typeface = axisData.typeface
                }
                val (xMin, xMax, xAxisScale) = getXAxisScale(chartData, xAxisSteps)
                var xPos = xStart - scrollOffset
                for (it in 0..xAxisSteps) {
                    xAxisHeight = drawXAxisLabel(
                        axisData,
                        it,
                        xAxisScale,
                        xAxisTextPaint,
                        this,
                        xPos
                    )
                    drawAxisLineWithPointers(xPos, axisData, zoomScale, xAxisScale)
                    xPos += ((xAxisStepSize.toPx() * (zoomScale * xAxisScale)))
                }
            }
        }
    }
}

private fun DrawScope.drawAxisLineWithPointers(
    xPos: Float,
    axisData: AxisData,
    zoomScale: Float,
    xAxisScale: Float
) {
    with(axisData) {
        if (axisConfig.isAxisLineRequired) {
            drawLine(
                Color.Black,
                Offset(xPos, 0f),
                Offset(xPos + ((xAxisStepSize.toPx() * (zoomScale * xAxisScale))), 0f),
                strokeWidth = axisLineThickness.toPx()
            )
            drawLine(
                Color.Black,
                Offset(xPos, 0f),
                Offset(xPos, indicatorLineWidth.toPx()),
                strokeWidth = axisLineThickness.toPx()
            )
        }
    }
}

private fun DrawScope.drawXAxisLabel(
    axisData: AxisData,
    it: Int,
    xAxisScale: Float,
    xAxisTextPaint: TextPaint,
    drawScope: DrawScope,
    xPos: Float
): Dp = with(axisData) {
    val calculatedXAxisHeight: Dp
    val xLabel = xLabelData((it * xAxisScale).toInt())
    val labelHeight = xLabel.getTextHeight(xAxisTextPaint)
    val labelWidth = xLabel.getTextWidth(xAxisTextPaint)
    calculatedXAxisHeight =
        if (axisConfig.isAxisLineRequired) {
            labelHeight.toDp() + axisLineThickness +
                    indicatorLineWidth + xLabelAndAxisLinePadding
        } else labelHeight.toDp() + xLabelAndAxisLinePadding
    drawScope.drawContext.canvas.nativeCanvas.apply {
        drawText(
            xLabel,
            xPos - (labelWidth / 2),
            labelHeight / 2 + indicatorLineWidth.toPx() + xLabelAndAxisLinePadding.toPx(),
            xAxisTextPaint
        )
    }
    calculatedXAxisHeight
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

@Preview(showBackground = true)
@Composable
private fun XAxisPreview() {
    val axisData = AxisData.Builder()
        .xLabelAndAxisLinePadding(10.dp)
        .xAxisPos(Gravity.BOTTOM)
        .axisLabelFontSize(14.sp)
        .xLabelData { index -> index.toString() }
        .build()
    XAxis(
        modifier = Modifier.height(40.dp),
        axisData = axisData,
        xStart = 0f,
        scrollOffset = 0f,
        zoomScale = 1f,
        chartData = listOf()
    )
}
