package com.example.piechartcontainer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ygraph.components.bar.piechart.models.PieChartData
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

        DrawChart(
            modifier = Modifier.height(350.dp),
            pieChartData = pieChartData,
            strokeWidth = 120f,
            percentVisible = true,
            percentColor = Color.Black,
            chartType = ChartType.DonutPieChart,
            animationDuration = 3000
        )
        Spacer(modifier = Modifier.height(32.dp))
        DrawChart(
            modifier = Modifier.height(350.dp),
            pieChartData = pieChartData,
            chartType = ChartType.PieChart,
            animationDuration = 3000
        )
    }

}