package com.app.chartcontainer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.ChartWrapper.DrawChart
import com.ygraph.components.piechart.utils.ChartType

@Composable
fun ChartContainer() {
    Column() {
        DrawChart(
            legends = listOf("Mango", "Banana", "Apple"),
            color = listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40)),
            modifier = Modifier.height(350.dp),
            values = listOf(15f, 35f, 50f),
            strokeWidth = 120f,
            percentVisible = true,
            percentColor = Color.Black,
            chartType = ChartType.DonutPieChart)
        Spacer(modifier = Modifier.height(32.dp))
        DrawChart(
            legends = listOf("Mango", "Banana", "Apple"),
            color = listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40)),
            values = listOf(15f, 35f, 50f),
            modifier = Modifier.height(350.dp),
            chartType = ChartType.PieChart)
    }

}