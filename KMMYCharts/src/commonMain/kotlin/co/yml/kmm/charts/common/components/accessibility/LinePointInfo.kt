package co.yml.kmm.charts.common.components.accessibility

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
 * Composable to display each line point data for given line chart.
 * @param axisLabelDescription: Axis label description.
 * @param pointDescription: Details of each point on the line.
 * @param lineColor: Color of each line.
 */
@Composable
internal fun LinePointInfo(
    axisLabelDescription: String,
    pointDescription: String,
    lineColor: Color
) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .clickable { }
        .semantics(mergeDescendants = true) {}, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(axisLabelDescription, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(lineColor)
                        .size(20.dp)
                )
                Text(pointDescription, fontSize = 12.sp)
            }
        }
    }
}
