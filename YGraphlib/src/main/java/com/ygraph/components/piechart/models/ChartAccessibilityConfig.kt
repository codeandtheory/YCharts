package com.ygraph.components.piechart.models

import androidx.compose.ui.Alignment

/**
 * ChartAccessibilityConfig is a data class to configure accessibility requirements.
 * @param nextButtonText: Text of button that forward scroll or rotate clockwise direction.
 * @param prevButtonText: Text of button that back scroll or rotate anti-clockwise direction,
 * @param buttonContainerAlignment: Alignment or gravity of the buttons container.
 * @param contentDescriptionDelay: Delay given to wait for announcing the selected item description.
 * @param contentDescription: Description of selected item.
 */
data class ChartAccessibilityConfig(
    val nextButtonText: String = NEXT_BUTTON_TEXT,
    val prevButtonText: String = PREV_BUTTON_TEXT,
    val buttonContainerAlignment: Alignment = Alignment.BottomCenter,
    val contentDescriptionDelay: Long = 4000L,
    val contentDescription: (Int, Int, Int, PieChartData.Slice) -> String = { sliceIndex, totalSlices, slicePercentage, slice ->
        "${sliceIndex + 1} of $totalSlices slices is selected that is ${slice.label} is ${slicePercentage}%"
    }
) {
    companion object {
        const val NEXT_BUTTON_TEXT = "Next"
        const val PREV_BUTTON_TEXT = "Previous"
    }
}
