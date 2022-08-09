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
import com.ygraph.components.piechart.models.PieChartData


/**
 * @param pieChartData: Data list for the pie chart
 * @param padding: Padding of start
 **/

@Composable
fun Legends(
    pieChartData: PieChartData,
    legendTextColor: Color,
    padding: Dp = 15.dp
) {
    Column(
        modifier = Modifier.padding(start = padding)
    ) {
        for (i in pieChartData.slices.indices) {
            DisplayLegend(
                color = pieChartData.slices[i].color,
                legend = pieChartData.slices[i].label,
                legendTextColor
            )
        }
    }
}

/**
 * @param color: Color of the legend
 * @param legend:Text of the badge
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