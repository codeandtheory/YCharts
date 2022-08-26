package com.app.ygraphs.presentation

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.piechart.charts.PieChart
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData

class PieChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YGraphsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_pie_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    val context = LocalContext.current
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        PieChart1(context)
                    }
                }
            }
        }
    }
}

@Composable
private fun PieChart1(context: Context) {
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("MI", 15f, Color(0xFF58BDFF)),
            PieChartData.Slice("Iphone", 35f, Color(0xFF125B7F)),
            PieChartData.Slice("Windows", 10f, Color(0xFF092D40)),
            PieChartData.Slice("Pixel", 10f, Color(0xFF092D10)),
            PieChartData.Slice("Samsung", 20f, Color(0xFF092D70)),
            PieChartData.Slice("Oneplus", 0f, Color(0xFF092D80)),
        )
    )

    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 120f,
            percentColor = Color.Black,
            isLegendVisible = pieChartData.legendVisible,
            legendGridSize = 3,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            showSliceLabels = true,
            chartPadding = 25,
            legendBadgeWidth = 20.dp
        )

    PieChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        pieChartData,
        pieChartConfig
    ) { slice ->
        Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
    }
}

