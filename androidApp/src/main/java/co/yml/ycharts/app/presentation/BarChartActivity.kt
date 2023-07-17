package co.yml.ycharts.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.yml.kmm.charts.ChartScreen
import co.yml.ycharts.app.R
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme

class BarChartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_bar_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
//                        LazyColumn(content = {
//                            items(3) { item ->
//                                when (item) {
//                                    0 -> BarChart1()
//                                    1 -> BarChart2()
//                                }
//                            }
//                        })
                        ChartScreen(1)
                    }
                }
            }
        }
    }
}


