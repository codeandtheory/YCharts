package co.yml.charts.common.components.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.ui.barchart.models.GroupBar
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.model.Point

/**
 * Composable to display each combined chart item for given combined chart.
 * @param pointsList: List of points in each item.
 * @param lineColor: List of colors of lines as per line chart.
 * @param groupBar: Details of each group bar.
 * @param axisLabelDescription: Axis label description.
 * @param barColorPaletteList: List of each bar colors for a given group bar.
 * @param dividerColor: Divider color between each point items.
 */
@Composable
fun CombinedChartInfo(
    pointsList: List<Point>,
    lineColor: List<Color>,
    groupBar: GroupBar?,
    axisLabelDescription: String,
    barColorPaletteList: List<Color>,
    dividerColor: Color
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
            pointsList.forEachIndexed { index, point ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(
                                color = lineColor[index],
                                shape = RoundedCornerShape(corner = CornerSize(10.dp))
                            )
                            .size(10.dp)
                    )
                    Text(point.description, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            ItemDivider(thickness = 1.dp, dividerColor = dividerColor)
            groupBar?.barList?.forEachIndexed { index, value ->
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
