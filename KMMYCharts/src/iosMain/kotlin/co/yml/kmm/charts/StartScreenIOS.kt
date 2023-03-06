package co.yml.kmm.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun StartScreenIOS() {
    Box(Modifier.background(Color.Red)) {
        ChartScreen()
    }
}