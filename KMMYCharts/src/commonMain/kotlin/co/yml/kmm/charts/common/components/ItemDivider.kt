package co.yml.kmm.charts.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Draws a horizontal line as a divider for given thickness and color
 * @param thickness: Defines the thickness of the divider.
 * @param dividerColor: Defines the color of the divider.
 */
@Composable
internal fun ItemDivider(thickness: Dp, dividerColor: Color = Color.Black) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(thickness)
            .background(dividerColor)
    )
}
