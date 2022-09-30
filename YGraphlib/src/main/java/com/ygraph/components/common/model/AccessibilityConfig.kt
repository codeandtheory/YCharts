package com.ygraph.components.common.model

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * [AccessibilityConfig] data class params used in config accessibility.
 * @param alignment : alignment of buttons
 * @param nextButtonText : next button text
 * @param nextButtonColor : next button color
 * @param nextButtonTextColor : next button text color
 * @param prevButtonText : prev button text
 * @param prevButtonColor : prev button color
 * @param prevButtonTextColor : prev button text color
 * @param paddingHorizontal : padding Horizontal
 * @param paddingVertical : padding Vertical
 *  */

data class AccessibilityConfig(
    val alignment: Alignment = Alignment.Center,
    val nextButtonText: String = "Next",
    val nextButtonColor: Color = Color.Blue,
    val nextButtonTextColor: Color = Color.White,
    val prevButtonText: String = "Prev",
    val prevButtonColor: Color = Color.Blue,
    val prevButtonTextColor: Color = Color.White,
    val paddingHorizontal: Dp = 8.dp,
    val paddingVertical: Dp = 0.dp,
)
