package co.yml.charts.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ItemDivider(thickness: Dp, dividerColor: Color = Color.Black) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(thickness)
            .background(dividerColor)
    )
}
