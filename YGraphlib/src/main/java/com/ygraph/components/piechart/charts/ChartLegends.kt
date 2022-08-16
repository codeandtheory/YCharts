package com.ygraph.components.piechart.charts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData


/**
 * @param pieChartData: Data list for the pie chart
 * @param pieChartConfig: Configuration of the pie chart
 **/

@Composable
fun Legends(
    pieChartData: PieChartData,
    pieChartConfig: PieChartConfig,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(pieChartConfig.legendGridSize),
        modifier = Modifier.padding(
            start = pieChartConfig.legendPadding,
            top = pieChartConfig.legendPadding,
            end = pieChartConfig.legendPadding
        ),
        content = {
            items(pieChartData.slices.size) { index ->
                DisplayLegend(
                    color = pieChartData.slices[index].color,
                    legend = pieChartData.slices[index].label,
                    pieChartConfig.legendLabelTextColor,
                    pieChartConfig.legendFontStyle
                )
            }
        })

}


/**
 * @param color: Color of the legend
 * @param legend:Text of the badge
 * **/

@Composable
private fun DisplayLegend(
    color: Color,
    legend: String,
    legendTextColor: Color,
    legendFontStyle: FontStyle
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Badge(
            modifier = Modifier.width(16.dp),
            containerColor = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend,
            color = legendTextColor,
            fontStyle = legendFontStyle)
        
    }
}
