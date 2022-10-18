package co.yml.charts.common.components.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.charts.piechart.models.PieChartData

@Composable
fun SliceInfo(slice: PieChartData.Slice, slicePercentage: Int) {
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
            Text(slice.sliceDescription(slicePercentage), fontSize = 12.sp)
        }
    }
}
