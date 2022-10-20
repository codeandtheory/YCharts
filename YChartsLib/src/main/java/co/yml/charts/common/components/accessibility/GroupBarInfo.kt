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
import co.yml.charts.ui.barchart.models.GroupBar


/**
 * Composable to display each group bar item for given group bar chart.
 * @param axisLabelDescription: Axis label description.
 * @param groupBar: Details of each group bar.
 * @param barColorPaletteList: List of each bar colors for a given group bar.
 */
@Composable
fun GroupBarInfo(
    groupBar: GroupBar,
    axisLabelDescription: String,
    barColorPaletteList: List<Color>
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
                .padding(5.dp)
        ) {
            groupBar.barList.forEachIndexed { index, value ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(barColorPaletteList[index])
                            .size(20.dp)
                    )
                    Text(value.description, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}
