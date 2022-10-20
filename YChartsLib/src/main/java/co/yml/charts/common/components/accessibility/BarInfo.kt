package co.yml.charts.common.components.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable to display each bar item for given bar chart
 * @param axisLabelDescription: Axis label description
 * @param barDescription: Bar description
 */
@Composable
fun BarInfo(
    axisLabelDescription: String,
    barDescription: String, barColor: Color
) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .clickable { }
        .semantics(mergeDescendants = true) {}, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(axisLabelDescription, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(barColor)
                        .size(20.dp)
                )
                Text(barDescription, fontSize = 12.sp)
            }
        }
    }
}
