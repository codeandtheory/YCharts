package com.ygraph.components.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.ygraph.components.graph.bargraph.models.LegendsConfig

@Composable
fun Legends(modifier: Modifier = Modifier, legendsConfig: LegendsConfig) {
    Box(modifier = modifier) {
        with(legendsConfig) {
            if (showLabel && legendLabelList.size > 1) {
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = gridPaddingHorizontal,
                            vertical = gridPaddingVertical
                        ),
                    columns = GridCells.Fixed(gridColumnCount)
                ) {
                    items(legendLabelList) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .background(it.color)
                                    .size(colorBoxSize)
                            )
                            Spacer(modifier = Modifier.padding(spaceBWLabelAndColorBox))
                            Text(
                                text = it.name,
                                style = textStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}
