package co.yml.charts.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig

/**
 * Renders the list of legends in a grid format for given given grid column count
 * @param modifier: Defines the arrangements of ui compositions.
 * @param legendsConfig: Defines the configurations required for rendering legends in [LegendsConfig]
 */
@Composable
fun Legends(modifier: Modifier = Modifier, legendsConfig: LegendsConfig) {
    with(legendsConfig) {
        if (legendLabelList.size > 1) {
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = gridPaddingHorizontal, vertical = gridPaddingVertical
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(gridColumnCount)
            ) {
                items(legendLabelList) {
                    Legend(legendsConfig, it)
                }
            }
        }
    }
}

@Composable
private fun Legend(config: LegendsConfig, legendLabel: LegendLabel) {
    Row(
        horizontalArrangement = config.legendsArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val boxModifier = Modifier.size(config.colorBoxSize)
        if (legendLabel.brush != null) {
            Box(modifier = boxModifier.background(legendLabel.brush))
        } else {
            Box(modifier = boxModifier.background(legendLabel.color))
        }

        Spacer(modifier = Modifier.padding(config.spaceBWLabelAndColorBox))
        Text(
            text = legendLabel.name, style = config.textStyle, overflow = TextOverflow.Ellipsis
        )
    }
}

