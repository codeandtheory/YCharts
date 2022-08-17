package com.ygraph.components.barchart

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.ygraph.components.barchart.models.BarChartData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer


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
    Column(Modifier.onGloballyPositioned {
        totalWidth.value = it.size.width
    }) {

        val xOffset = remember { mutableStateOf(0f) }
        val dragOffset = remember { mutableStateOf(0f) }
        val isDragging = remember { mutableStateOf(false) }
        val visibility = remember { mutableStateOf(false) }
        val columnWidth = remember { mutableStateOf(0f) }
        val horizontalGap = remember { mutableStateOf(0f) }
        val paddingRight = barChartData.paddingEnd
        val points = barChartData.chartData
        val (xMin, xMax) = Pair(0, 75)  // todo : replace with AxisData

        val maxElementInYAxis = barChartData.yAxisData.yMaxValue
        ScrollableCanvasContainer(modifier = modifier, calculateMaxDistance = { xZoom ->
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


