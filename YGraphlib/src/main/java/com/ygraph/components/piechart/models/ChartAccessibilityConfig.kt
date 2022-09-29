package com.ygraph.components.piechart.models

data class ChartAccessibilityConfig(
    val nextButtonText: String = NEXT_BUTTON_TEXT,
    val prevButtonText: String = PREV_BUTTON_TEXT,
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
