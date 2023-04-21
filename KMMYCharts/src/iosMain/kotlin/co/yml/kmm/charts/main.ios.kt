import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import co.yml.kmm.charts.StartScreenIOS
import platform.UIKit.UIViewController

fun MainViewController(chartType: Int, isTalkbackEnabled: Boolean): UIViewController =
    Application("KMM YCharts") {
        Column {
            // To skip upper part of screen.
            Box(
                modifier = Modifier
                    .height(40.dp)
            )
            StartScreenIOS(chartType, isTalkbackEnabled)
        }
    }