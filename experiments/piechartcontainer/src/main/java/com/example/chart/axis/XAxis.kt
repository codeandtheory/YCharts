package com.example.chart.axis


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
import co.yml.charts.axis.Gravity
import co.yml.charts.common.extensions.getTextHeight
import co.yml.charts.common.extensions.getTextWidth
import co.yml.charts.common.model.Point
import kotlin.math.ceil

/**
 *
 * XAxis compose method used for drawing xAxis in any given graph.
 * @param xAxisData : All data needed to draw Yaxis
 * @see co.yml.charts.axis.AxisData Data class to save all params related to axis
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
    chartData: List<Point>,
    dataValueWidth: Float = 0f
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
                if (startDrawPadding != 0.dp) {
                    drawLine(
                        axisLineColor,
                        Offset(startDrawPadding.toPx(), 0f),
                        Offset(xPos, 0f),
                        strokeWidth = axisLineThickness.toPx()
                    )
                }

                for (index in 0..steps) {
                    xAxisHeight = drawXAxisLabel(
                        xAxisData,
                        index,
                        xAxisScale,
                        xPos,
                        xStart,
                        dataValueWidth
                    )
                    drawAxisLineWithPointers(
                        xPos,
                        xAxisData,
                        zoomScale,
                        xAxisScale,
                        index != steps,
                        xStart,
                        index,
                        dataValueWidth
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
    canDrawEndLine: Boolean, // Added check to avoid drawing an extra line post the last point
    xStart: Float,
    index: Int,
    dataValueWidth: Float

) {
    with(axisData) {
        if (axisConfig.isAxisLineRequired) {
            if (canDrawEndLine) {
                val axisStepWidth = (axisStepSize.toPx() * (zoomScale * xAxisScale))
                drawLine(
                    axisLineColor,
                    if (isDataCategoryInYAxis) Offset(xStart, 0f) else Offset(xPos, 0f),
                    if (shouldDrawAxisLineTillEnd) {
                        //todo sree_ check this case for horizontal chart
                        Offset((xPos + (axisStepWidth / 2) + axisStepWidth), 0f)
                    } else {
                        if (isDataCategoryInYAxis) Offset(
                            xStart + (dataValueWidth * index),
                            0f
                        ) else Offset(xPos + axisStepWidth, 0f)
                    }, strokeWidth = axisLineThickness.toPx()
                )
            }
            drawLine(
                axisLineColor,
                if (isDataCategoryInYAxis) Offset(
                    xStart + (dataValueWidth * index),
                    0f
                ) else Offset(xPos, 0f),
                if (isDataCategoryInYAxis) Offset(
                    xStart + (dataValueWidth * index),
                    indicatorLineWidth.toPx()
                ) else Offset(xPos, indicatorLineWidth.toPx()),
                strokeWidth = axisLineThickness.toPx()
            )
        }
    }
}

private fun DrawScope.drawXAxisLabel(
    axisData: AxisData,
    index: Int,
    xAxisScale: Float,
    xPos: Float,
    xStart: Float,
    dataValueWidth: Float
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
        val x =
            if (isDataCategoryInYAxis) xStart + (dataValueWidth * index) - (labelWidth / 2) else xPos - (labelWidth / 2)
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

/**
 * Returns triple of Xmax, Xmin & scale for given list of points and steps
 * @param points: List of points in axis
 * @param steps: Total steps in axis
 */
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
