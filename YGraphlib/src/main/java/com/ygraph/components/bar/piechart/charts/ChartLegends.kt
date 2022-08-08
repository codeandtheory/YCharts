package com.ygraph.components.piechart.charts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Value list for iterate the loop [values].
 * List of Labels to display [legend]
 * List of colors [colors]
 * Padding of start [padding]
 **/

@Composable
fun Legends(
    values: List<Float>,
    legend: List<String>,
    colors: List<Color>,
    legendTextColor: Color,
    padding: Dp = 15.dp
) {
    Column(
        modifier = Modifier.padding(start = padding)
    ) {
        for (i in values.indices) {
            DisplayLegend(color = colors[i], legend = legend[i], legendTextColor)
        }
    }
}

/**
 * Color of badge [color]
 * Text of badge [legend]
 * **/

@Composable
fun DisplayLegend(
    color: Color,
    legend: String,
    legendTextColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Badge(
            modifier = Modifier.width(16.dp),
            containerColor = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend,
            color = legendTextColor
        )
    }
}