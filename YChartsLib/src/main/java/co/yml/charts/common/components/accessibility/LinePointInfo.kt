package co.yml.charts.common.components.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable to display each line point data for given line chart.
 * @param axisLabelDescription: Axis label description.
 * @param pointDescription: Details of each point on the line.
 * @param lineColor: Color of each line.
 * @param titleTextSize: TextUnit title font size
 * @param descriptionTextSize: TextUnit description font size
 *
 */
@Composable
fun LinePointInfo(
    axisLabelDescription: String,
    pointDescription: String,
    lineColor: Color,
    titleTextSize: TextUnit,
    descriptionTextSize: TextUnit
) {
    // Merge elements below for accessibility purposes
    Row(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .clickable { }
        .semantics(mergeDescendants = true) {}, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(axisLabelDescription, fontSize = titleTextSize)
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
                Text(pointDescription, fontSize = descriptionTextSize)
            }
        }
    }
}
