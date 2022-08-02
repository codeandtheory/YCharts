package com.ygraph.components.piechart.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    values: List<Float>,
    colors: List<Color> = listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40)),
    isLegendVisible: Boolean = false,
    legends: List<String> = emptyList(),
    startAngle: Float = -90f,
    size: Dp = 200.dp
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(size = size)
        ) {

            var sAngle = startAngle

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = sAngle,
                    sweepAngle = sweepAngles[i],
                    useCenter = true
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