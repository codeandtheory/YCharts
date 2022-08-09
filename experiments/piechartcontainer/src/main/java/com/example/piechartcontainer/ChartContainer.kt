package com.example.piechartcontainer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData
import com.ygraph.components.piechart.ChartWrapper.DrawChart
import com.ygraph.components.piechart.utils.ChartType

@Composable
fun ChartContainer() {
    Column() {

        val pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("Mango", 15f, Color(0xFF58BDFF)),
                PieChartData.Slice("Banana", 35f, Color(0xFF125B7F)),
                PieChartData.Slice("Apple", 40f, Color(0xFF092D40)),
            )
        )

        val pieChartConfig =
            PieChartConfig(
                percentVisible = true,
                strokeWidth = 120f,
                percentColor = Color.Black,
                isLegendVisible = pieChartData.legendVisible
            )


        DrawChart(
            modifier = Modifier.height(350.dp),
            pieChartData = pieChartData,
            chartType = ChartType.DonutPieChart,
            pieChartConfig = pieChartConfig
        )
        Spacer(modifier = Modifier.height(32.dp))
        DrawChart(
            modifier = Modifier.height(350.dp),
            pieChartData = pieChartData,
            chartType = ChartType.PieChart,
            pieChartConfig = pieChartConfig
        )
    }

}