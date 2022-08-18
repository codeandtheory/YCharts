package com.ygraph.components.barchart

import android.graphics.PointF
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer
import kotlin.math.ceil



/**
 *
 * BarChart compose method for drawing bar graph.
 * @param modifier: All modifier related properties
 * @param barChartData : All data needed to Bar Chart
 * @see com.ygraph.components.barchart.models.BarChartData Data class to save all params related to Bar chart
 */
@Composable
fun BarChart(modifier: Modifier, barChartData: BarChartData) {
    val totalWidth = remember { mutableStateOf(0) }
    Column(modifier.onGloballyPositioned {
        totalWidth.value = it.size.width
    }) {

        val xOffset = remember { mutableStateOf(0f) }
        val dragOffset = remember { mutableStateOf(0f) }
        val isDragging = remember { mutableStateOf(false) }
        val visibility = remember { mutableStateOf(false) }
        val columnWidth = remember { mutableStateOf(0f) }
        val horizontalGap = remember { mutableStateOf(0f) }
        val paddingRight = barChartData.paddingEnd
        val points = barChartData.chartData.map { it.point }
        val rowHeight = remember { mutableStateOf(0f) }
        val paddingTop = barChartData.paddingTop
        val bgColor = MaterialTheme.colors.surface

        val (xMin, xMax, xAxisScale) = getXAxisScale(points, 50) // todo : replace with AxisData
        val (yMin, yMax, yAxisScale) = getYAxisScale(points, 10)



        val maxElementInYAxis = barChartData.yAxisData.yMaxValue
        ScrollableCanvasContainer(modifier = modifier, containerBackgroundColor = barChartData.backgroundColor, calculateMaxDistance = { xZoom ->
            horizontalGap.value = barChartData.horizontalExtraSpace.toPx()
            val xLeft = columnWidth.value + horizontalGap.value
            xOffset.value =
                (barChartData.barWidth.toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
            val xLastPoint =
                (xMax - xMin) * xOffset.value + xLeft + paddingRight.toPx() + 30.dp.toPx()
            if (xLastPoint > size.width) {
                xLastPoint - size.width
            } else 0f
        },
            onDraw = { xZoom, scrollOffset ->

                    val yBottom = size.height - rowHeight.value
                    val yOffset = ((yBottom - paddingTop.toPx()) / maxElementInYAxis) 
                    xOffset.value =
                        (barChartData.barWidth.toPx() + barChartData.paddingBetweenBars.toPx()) * xZoom
                    val xLeft = columnWidth.value + horizontalGap.value
                    val dragLocks = mutableMapOf<Int, Pair<BarData, Offset>>()

                    // Draw bar lines
                    barChartData.chartData.forEachIndexed { index, barData ->
                        val (x, y) = (barData.point)
                        val x1 = ((x - xMin) * xOffset.value ) + xLeft - scrollOffset
                        val y1 = yBottom - ((y - yMin) * yOffset)
                        val drawOffset = Offset(x1, y1)

                        if (barChartData.isGradientEnabled) {
                            val brush = Brush.verticalGradient(
                                colors = barData.gradientColorList
                            )
                            drawRoundRect(
                                brush = brush,
                                topLeft = drawOffset,
                                size = Size(barChartData.barWidth.toPx(), yBottom - y1),
                                cornerRadius = CornerRadius(
                                    barChartData.cornerRadius.toPx(),
                                    barChartData.cornerRadius.toPx()
                                ),
                                style = Fill
                            )
                        } else {
                            drawRoundRect(
                                color = barData.color,
                                topLeft = drawOffset,
                                size = Size(barChartData.barWidth.toPx(), yBottom - y1),
                                cornerRadius = CornerRadius(
                                    barChartData.cornerRadius.toPx(),
                                    barChartData.cornerRadius.toPx()
                                ),
                                style = Fill
                            )
                        }
                    }

                    // Draw column to make graph look scrollable under Yaxis
                    drawRect(
                        bgColor,
                        Offset(0f, 0f),
                        Size(columnWidth.value, size.height)
                    )

                    // Draw right padding
                    drawRect(
                        bgColor,
                        Offset(size.width - paddingRight.toPx(), 0f),
                        Size(paddingRight.toPx(), size.height)
                    )

                },
            drawXAndYAxis = { scrollOffset, xZoom ->

               
            },
            onDragStart = { offset ->
                dragOffset.value = offset.x
                isDragging.value = true
                visibility.value = true
            },
            onDragEnd = {
                isDragging.value = false
                visibility.value = false
            },
            onDragging = { change, _ -> dragOffset.value = change.position.x }
        )
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


fun getYAxisScale(
    points: List<PointF>,
    steps: Int
): Triple<Float, Float, Float> {
    val yMin = points.minOf { it.y }
    val yMax = points.maxOf { it.y }
    val totalSteps = (yMax - yMin)
    val temp =
        totalSteps / if (steps > 1) (steps - 1) else 1 // First step starts from 0 by default
    val scale = ceil(temp)
    return Triple(yMin, yMax, scale)
}

