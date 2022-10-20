package co.yml.charts.common.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * LegendsConfig data class params used in config label in graph.
 * @param legendLabelList: stackLabelList is used to show labels with colors
 * @param gridColumnCount : Column Count for stackLabel grid
 * @param gridPaddingHorizontal : Horizontal padding for stackLabel grid
 * @param gridPaddingVertical :  Vertical padding for stackLabel grid
 * @param spaceBWLabelAndColorBox: Space between Label and ColorBox for stackLabel grid item
 * @param colorBoxSize: Blend mode for the groupSeparator
 * @param textStyle: TextStyle for label
 *  */
data class LegendsConfig(
    val legendLabelList: List<LegendLabel>,
    val gridColumnCount: Int = 1,
    val gridPaddingHorizontal: Dp = 8.dp,
    val gridPaddingVertical: Dp = 8.dp,
    val colorBoxSize: Dp = 25.dp,
    val textStyle: TextStyle = TextStyle(),
    val spaceBWLabelAndColorBox: Dp = 8.dp,
    val legendsArrangement: Arrangement.Horizontal = Arrangement.Center
)

/**
 * LegendLabel data class params used in drawing label in graph.
 * @param color : Color of label.
 * @param name : Name of label.
 *  */
data class LegendLabel(
    val color: Color,
    val name: String,
)
