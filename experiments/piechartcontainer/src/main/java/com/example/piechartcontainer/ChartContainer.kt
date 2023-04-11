package com.example.piechartcontainer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.ChartWrapper.DrawChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun ChartContainer() {
    Column() {

        val pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("Mango", 15f, Color(0xFF58BDFF)),
                PieChartData.Slice("Banana", 35f, Color(0xFF125B7F)),
                PieChartData.Slice("Apple", 40f, Color(0xFF092D40)),
            ),
            plotType = PlotType.Pie
        )

        val pieChartConfig =
            PieChartConfig(
                labelVisible = true,
                strokeWidth = 120f,
                labelColor = Color.Black
            )


        DrawChart(
            modifier = Modifier.height(350.dp),
            pieChartData = pieChartData,
            plotType = PlotType.Pie,
            pieChartConfig = pieChartConfig
        )
        Spacer(modifier = Modifier.height(32.dp))
        DrawChart(
            modifier = Modifier.height(350.dp),
            pieChartData = pieChartData,
            plotType = PlotType.Donut,
            pieChartConfig = pieChartConfig
        )
    }

}
