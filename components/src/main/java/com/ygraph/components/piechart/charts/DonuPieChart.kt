package com.ygraph.components.piechart.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.utils.calculateDrawableArea
import com.ygraph.components.piechart.utils.calculateSectorThickness

@Composable
fun DonutPieChart(
    values: List<Float>,
    colors: List<Color>,
    sliceThickness : Float = 10f,
    startAngle: Float = -90f,
    isLegendVisible: Boolean = false,
    legends: List<String> = emptyList(),
    sizeDp: Dp = 200.dp
) {
    // Sum of all the values
    val sumOfValues = values.sum()

    // Calculate each proportion value
    val proportions = values.map {
        it * 100 / sumOfValues
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(size = sizeDp)
        ) {
            var sAngle =  startAngle
            for (i in sweepAngles.indices) {
                drawSlice(
                    canvas = drawContext.canvas,
                    area = size,
                    startAngle = sAngle,
                    sweepAngle = sweepAngles[i],
                    colors = colors[i],
                    sliceThickness = sliceThickness
                )
                sAngle += sweepAngles[i]
            }
        }
        if (isLegendVisible) {
            Legends(
                values = values,
                colors = colors,
                legend = legends
            )
        }
    }
}

fun drawSlice(
    canvas: Canvas,
    area: Size,
    startAngle: Float,
    sweepAngle: Float,
    colors: Color,
    sliceThickness : Float
) {
    var sectionPaint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Stroke
    }

    canvas.drawArc(
        rect = area.calculateDrawableArea(area),
        paint = sectionPaint.apply {
            color = colors
            strokeWidth = sliceThickness.calculateSectorThickness(sliceThick = sliceThickness, area = area)
        },
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false
    )
}