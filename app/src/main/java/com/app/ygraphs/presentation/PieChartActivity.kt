package com.app.ygraphs.presentation

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(content = {
                            items(3) { item ->
                                when (item) {
                                    0 -> PieChart1(LocalContext.current)
                                    1 -> PieChart2(LocalContext.current)
                                }
                            }
                        })
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
            PieChartData.Slice("SciFi", 15f, Color(0xFF333333)),
            PieChartData.Slice("Comedy", 15f, Color(0xFF666a86)),
            PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
            PieChartData.Slice("Romance", 10f, Color(0xFFE8DDB5)),
            PieChartData.Slice("Action", 20f, Color(0xFFEDAFB8)),
            PieChartData.Slice("Thriller", 0f, Color(0xFFF94892)),
            PieChartData.Slice("Western", 10f, Color(0xFFA675A1)),
            PieChartData.Slice("Fantasy", 10f, Color(0xFF8F3985)),
        )
    )

    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 120f,
            percentColor = Color.Black,
            isLegendVisible = pieChartData.legendVisible,
            legendGridSize = 4,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 30,
            legendBadgeWidth = 20.dp,
            legendPadding = 30.dp,
            showSliceLabels = false,
            animationDuration = 1500
        )

    PieChart(
        modifier = Modifier
            .width(400.dp)
            .height(400.dp),
        pieChartData,
        pieChartConfig
    ) { slice ->
        Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
    }
}


@Composable
private fun PieChart2(context: Context) {
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Android", 30f, Color(0xFF002B5B)),
            PieChartData.Slice("iOS", 30f, Color(0xFF2B4865)),
            PieChartData.Slice("Windows", 15f, Color(0xFF256D85)),
            PieChartData.Slice("Other", 25f, Color(0xFF806D85)),
        )
    )

    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 120f,
            isLegendVisible = false,
            legendGridSize = 3,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            sliceLabelTypeface = Typeface.defaultFromStyle(Typeface.ITALIC),
            isAnimationEnable = true,
            chartPadding = 20,
            legendBadgeWidth = 30.dp,
            showSliceLabels = true
        )

    PieChart(
        modifier = Modifier
            .width(400.dp)
            .height(400.dp),
        pieChartData,
        pieChartConfig
    ) { slice ->
        Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
    }
}

