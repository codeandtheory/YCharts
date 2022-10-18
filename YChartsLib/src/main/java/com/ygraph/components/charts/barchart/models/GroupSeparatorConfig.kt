package com.ygraph.components.charts.barchart.models

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * GroupSeparatorConfig data class params used in config groupSeparator.
 * @param separatorWidth : width of the groupSeparator
 * @param separatorColor : color of the groupSeparator
 * @param showSeparator : Boolean Flag to show/hide groupSeparator
 * @param separatorBlendMode: Blend mode for the groupSeparator
 *  */
data class GroupSeparatorConfig(
    val separatorWidth: Dp = 1.dp,
    val separatorColor: Color = Color.Gray,
    val showSeparator: Boolean = true,
    val separatorBlendMode: BlendMode = DrawScope.DefaultBlendMode,
)
