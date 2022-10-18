package co.yml.charts.axis

import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import co.yml.charts.common.extensions.getTextHeight
import co.yml.charts.common.extensions.getTextWidth

/**
 *
 * YAxis compose method used for drawing yAxis in any given graph.
 * @param modifier : All modifier related property.
 * @param yAxisData : All data needed to draw Yaxis.
 * @see co.yml.charts.axis Data class to save all params related to Yaxis
 */
@Composable
fun YAxis(modifier: Modifier, yAxisData: AxisData) {
    with(yAxisData) {
        var yAxisWidth by remember { mutableStateOf(0.dp) }
        val isRightAligned = axisPos == Gravity.RIGHT
        Column(modifier = modifier.clipToBounds()) {
            val steps = steps + 1
            Canvas(
                modifier = modifier
                    .clipToBounds()
                    .width(yAxisWidth)
                    .background(backgroundColor)
            ) {
                val (yAxisHeight, segmentHeight) = getAxisInitValues(
                    yAxisData,
                    size.height,
                    axisBottomPadding.toPx(),
                    axisTopPadding.toPx()
                )
                for (index in 0 until steps) {
                    // Drawing the axis labels
                    yAxisWidth = drawAxisLabel(
                        index,
                        yAxisData,
                        yAxisWidth,
                        isRightAligned,
                        yAxisHeight,
                        segmentHeight
                    )
                    drawAxisLineWithPointers(
                        yAxisData,
                        index,
                        steps,
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

fun getAxisInitValues(
    axisData: AxisData,
    canvasHeight: Float,
    bottomPadding: Float,
    topPadding: Float
): Pair<Float, Float> = with(axisData) {
    val yAxisHeight = canvasHeight - bottomPadding
    // Minus the top padding to avoid cropping at the top
    val segmentHeight = (yAxisHeight - topPadding) / axisData.steps
    Pair(yAxisHeight, segmentHeight)
}


private fun DrawScope.drawAxisLineWithPointers(
    axisData: AxisData,
    index: Int,
    totalSteps: Int,
    isRightAligned: Boolean,
    yAxisWidth: Dp,
    yAxisHeight: Float,
    segmentHeight: Float
) {
    with(axisData) {
        if (axisConfig.isAxisLineRequired) {
            // Draw line only until reqYLabelsQuo -1 else will be a extra line at top with no label
            if (index != (totalSteps - 1)) {
                //Draw Yaxis line
                drawLine(
                    start = Offset(
                        x = if (isRightAligned) 0.dp.toPx() else yAxisWidth.toPx(),
                        y = yAxisHeight - (segmentHeight * index)
                    ),
                    end = Offset(
                        x = if (isRightAligned) 0.dp.toPx() else yAxisWidth.toPx(),
                        y = yAxisHeight - (segmentHeight * (index + 1))
                    ),
                    color = axisLineColor, strokeWidth = axisLineThickness.toPx()
                )
            }

            //Draw pointer lines on Yaxis
            drawLine(
                start = Offset(
                    x = if (isRightAligned) 0.dp.toPx() else {
                        yAxisWidth.toPx() - indicatorLineWidth.toPx()
                    },
                    y = yAxisHeight - (segmentHeight * index)
                ),
                end = Offset(
                    x = if (isRightAligned) indicatorLineWidth.toPx() else yAxisWidth.toPx(),
                    y = yAxisHeight - (segmentHeight * index)
                ),
                color = axisLineColor, strokeWidth = axisLineThickness.toPx()
            )
        }
    }
}


private fun DrawScope.drawAxisLabel(
    index: Int,
    axisData: AxisData,
    yAxisWidth: Dp,
    isRightAligned: Boolean,
    yAxisHeight: Float,
    segmentHeight: Float
): Dp = with(axisData) {
    var calculatedYAxisWidth = yAxisWidth
    val yAxisTextPaint = TextPaint().apply {
        textSize = axisLabelFontSize.toPx()
        color = axisLabelColor.toArgb()
        textAlign = if (isRightAligned) Paint.Align.RIGHT else Paint.Align.LEFT
        typeface = axisData.typeface
    }
    val yAxisLabel = labelData(index)
    val measuredWidth = yAxisLabel.getTextWidth(yAxisTextPaint)
    val height: Int = yAxisLabel.getTextHeight(yAxisTextPaint)
    if (measuredWidth > calculatedYAxisWidth.toPx()) {
        val width =
            if (axisConfig.shouldEllipsizeAxisLabel) {
                axisConfig.minTextWidthToEllipsize
            } else measuredWidth.toDp()
        calculatedYAxisWidth =
            width + labelAndAxisLinePadding + axisOffset
    }
    val ellipsizedText = TextUtils.ellipsize(
        yAxisLabel,
        yAxisTextPaint,
        axisConfig.minTextWidthToEllipsize.toPx(),
        axisConfig.ellipsizeAt
    )
    drawContext.canvas.nativeCanvas.apply {
        drawText(
            if (axisConfig.shouldEllipsizeAxisLabel) ellipsizedText.toString() else yAxisLabel,
            if (isRightAligned) calculatedYAxisWidth.toPx() - labelAndAxisLinePadding.toPx() else {
                axisStartPadding.toPx()
            },
            yAxisHeight + height / 2 - ((segmentHeight * index)),
            yAxisTextPaint
        )
    }
    return calculatedYAxisWidth
}

@Preview(showBackground = true)
@Composable
fun YAxisPreview() {
    val yAxisData = AxisData.Builder()
        .steps(5)
        .bottomPadding(10.dp)
        .axisPosition(Gravity.LEFT)
        .axisLabelFontSize(14.sp)
        .labelData { index -> index.toString() }
        .build()
    YAxis(modifier = Modifier.height(300.dp), yAxisData = yAxisData)
}

