package com.ygraph.components.axis

import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.withRotation
import com.ygraph.components.common.extensions.getTextHeight
import com.ygraph.components.common.extensions.getTextWidth
import com.ygraph.components.common.model.Point
import kotlin.math.ceil

/**
 *
 * XAxis compose method used for drawing xAxis in any given graph.
 * @param xAxisData : All data needed to draw Yaxis
 * @see com.ygraph.components.axis.AxisData Data class to save all params related to axis
 * @param modifier : All modifier related property.
 * @param xStart : Start position of xAxis Points.
 * @param scrollOffset : Offset of delta scrolled position.
 * @param zoomScale : Scale at which zoom transformation being applied.
 * @param chartData : List of data points used in the graph.
 */
@Composable
fun XAxis(
    xAxisData: AxisData,
    modifier: Modifier,
    xStart: Float,
    scrollOffset: Float,
    zoomScale: Float,
    chartData: List<Point>
) {
    with(xAxisData) {
        var xAxisHeight by remember { mutableStateOf(0.dp) }
        Row(modifier = modifier.clipToBounds()) {
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(xAxisHeight)
                    .background(backgroundColor)
            ) {
                val (_, _, xAxisScale) = getXAxisScale(chartData, steps)
                var xPos = xStart - scrollOffset

                // used in the case of barchart
                if (initialDrawPadding != 0.dp) {
                    drawLine(
                        axisLineColor,
                        Offset(initialDrawPadding.toPx(), 0f),
                        Offset(xPos, 0f),
                        strokeWidth = axisLineThickness.toPx()
                    )
                }

                for (index in 0..steps) {
                    xAxisHeight = drawXAxisLabel(
                        xAxisData,
                        index,
                        xAxisScale,
                        xPos
                    )
                    drawAxisLineWithPointers(
                        xPos,
                        xAxisData,
                        zoomScale,
                        xAxisScale,
                        index != steps
                    )
                    xPos += ((axisStepSize.toPx() * (zoomScale * xAxisScale)))
                }
            }
        }
    }
}

private fun DrawScope.drawAxisLineWithPointers(
    xPos: Float,
    axisData: AxisData,
    zoomScale: Float,
    xAxisScale: Float,
    canDrawEndLine: Boolean // Added check to avoid drawing an extra line post the last point
) {
    with(axisData) {
        if (axisConfig.isAxisLineRequired) {
            if (canDrawEndLine) {
                drawLine(
                    axisLineColor,
                    Offset(xPos, 0f),
                    Offset(xPos + ((axisStepSize.toPx() * (zoomScale * xAxisScale))), 0f),
                    strokeWidth = axisLineThickness.toPx()
                )
            }
            drawLine(
                axisLineColor,
                Offset(xPos, 0f),
                Offset(xPos, indicatorLineWidth.toPx()),
                strokeWidth = axisLineThickness.toPx()
            )
        }
    }
}

private fun DrawScope.drawXAxisLabel(
    axisData: AxisData,
    index: Int,
    xAxisScale: Float,
    xPos: Float
): Dp = with(axisData) {
    val calculatedXAxisHeight: Dp
    val xAxisTextPaint = TextPaint().apply {
        textSize = axisLabelFontSize.toPx()
        color = axisLabelColor.toArgb()
        textAlign = Paint.Align.LEFT
        typeface = axisData.typeface
    }
    val xLabel = labelData((index * xAxisScale).toInt())
    val labelHeight = xLabel.getTextHeight(xAxisTextPaint)
    val labelWidth = xLabel.getTextWidth(xAxisTextPaint)
    calculatedXAxisHeight =
        if (axisConfig.isAxisLineRequired) {
            labelHeight.toDp() + axisLineThickness +
                    indicatorLineWidth + labelAndAxisLinePadding + axisBottomPadding
        } else labelHeight.toDp() + labelAndAxisLinePadding
    val ellipsizedText = TextUtils.ellipsize(
        xLabel,
        xAxisTextPaint,
        axisStepSize.toPx(),
        axisConfig.ellipsizeAt
    )
    drawContext.canvas.nativeCanvas.apply {
        val x = xPos - (labelWidth / 2)
        val y = labelHeight / 2 + indicatorLineWidth.toPx() + labelAndAxisLinePadding.toPx()
        withRotation(axisLabelAngle, x, y) {
            drawText(
                if (axisConfig.shouldEllipsizeAxisLabel) ellipsizedText.toString() else xLabel,
                x,
                y,
                xAxisTextPaint
            )
        }
    }
    calculatedXAxisHeight
}

fun getXAxisScale(
    points: List<Point>,
    steps: Int,
): Triple<Float, Float, Float> {
    val xMin = points.takeIf { it.isNotEmpty() }?.minOf { it.x } ?: 0f
    val xMax = points.takeIf { it.isNotEmpty() }?.maxOf { it.x } ?: 0f
    val totalSteps = (xMax - xMin)
    val temp = totalSteps / steps
    val scale = ceil(temp)
    return Triple(xMin, xMax, scale)
}

@Preview(showBackground = true)
@Composable
private fun XAxisPreview() {
    val axisData = AxisData.Builder()
        .labelAndAxisLinePadding(10.dp)
        .axisPosition(Gravity.BOTTOM)
        .axisLabelFontSize(14.sp)
        .labelData { index -> index.toString() }
        .build()
    XAxis(
        modifier = Modifier.height(40.dp),
        xAxisData = axisData,
        xStart = 0f,
        scrollOffset = 0f,
        zoomScale = 1f,
        chartData = listOf()
    )
}
