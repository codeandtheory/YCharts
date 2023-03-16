package co.yml.kmm.charts.axis

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
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.kmm.charts.common.model.Point
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
@OptIn(ExperimentalTextApi::class)
@Composable
internal fun XAxis(
    xAxisData: AxisData,
    modifier: Modifier,
    xStart: Float,
    scrollOffset: Float,
    zoomScale: Float,
    chartData: List<Point>,
    axisStart: Float
) {
    with(xAxisData) {
        var xAxisHeight by remember { mutableStateOf(0.dp) }
        val textMeasure = rememberTextMeasurer()
        Row(modifier = modifier.clipToBounds()) {
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(xAxisHeight)
                    .background(backgroundColor)
            ) {
                val (_, _, xAxisScale) = getXAxisScale(chartData, steps)
                //this is used when data category draws in Y axis and value in X axis
                val xAxisSegmentWidth = (size.width - xStart - axisEndPadding.toPx()) / steps

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
                        xAxisSegmentWidth,
                        textMeasure
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
                val axisStepWidth = (axisStepSize.toPx() * (zoomScale * xAxisScale))
                drawLine(
                    axisLineColor,
                    Offset(xPos, 0f),
                    if (shouldDrawAxisLineTillEnd) {
                        Offset((xPos + (axisStepWidth / 2) + axisStepWidth), 0f)
                    } else {
                        Offset(xPos + axisStepWidth, 0f)
                    },                    strokeWidth = axisLineThickness.toPx()
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

@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawXAxisLabel(
    axisData: AxisData,
    index: Int,
    xAxisScale: Float,
    xPos: Float,
    xStart: Float,
    dataValueWidth: Float,
    textMeasurer: TextMeasurer
): Dp = with(axisData) {
    val calculatedXAxisHeight: Dp
    val xLabel =
        if (axisData.dataCategoryOptions.isDataCategoryInYAxis) labelData(index) else labelData((index * xAxisScale).toInt())
    val labelHeight = textMeasurer.measure(text = AnnotatedString(xLabel)).size.height
    val labelWidth = textMeasurer.measure(text = AnnotatedString(xLabel)).size.width
    calculatedXAxisHeight =
        if (axisConfig.isAxisLineRequired) {
            labelHeight.toDp() + axisLineThickness +
                    indicatorLineWidth + labelAndAxisLinePadding + axisBottomPadding
        } else labelHeight.toDp() + labelAndAxisLinePadding
    drawContext.canvas.nativeCanvas.apply {
        val x =
            if (axisData.dataCategoryOptions.isDataCategoryInYAxis) xStart + (dataValueWidth * index) - (labelWidth / 2) else xPos - (labelWidth / 2)
        val y = labelHeight / 2 + indicatorLineWidth.toPx() + labelAndAxisLinePadding.toPx()
        drawText(
            textMeasurer = textMeasurer,
            text = xLabel,
            topLeft = Offset(x, y),
            maxSize = IntSize(1440, 1050)
        )
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

@Composable
internal fun XAxisPreview() {
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
        chartData = listOf(),
        axisStart = 0f
    )
}
