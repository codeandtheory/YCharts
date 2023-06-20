package co.yml.charts.common.components.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.ui.piechart.models.PieChartData

/**
 * Composable to display each slice for a given pie chart.
 * @param slice: Details of each slice in pie/ donut chart.
 * @param slicePercentage: Percentage of each slice.
 * @param textSize: TextUnit text font size
 */
@Composable
fun SliceInfo(slice: PieChartData.Slice, slicePercentage: Int, textSize: TextUnit) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .clickable { }
        .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(slice.color)
                .size(30.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Text(slice.sliceDescription(slicePercentage), fontSize = textSize)
        }
    }
}
