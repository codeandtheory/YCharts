package com.example.piechartcontainer

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.common.components.Legends
import com.ygraph.components.common.model.PlotType
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.charts.piechart.charts.PieChart
import com.ygraph.components.charts.piechart.models.PieChartConfig
import com.ygraph.components.charts.piechart.models.PieChartData

class PieChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current

                    val pieChartData = PieChartData(
                        slices = listOf(
                            PieChartData.Slice("MI", 15f, Color(0xFF58BDFF)),
                            PieChartData.Slice("Iphone", 35f, Color(0xFF125B7F)),
                            PieChartData.Slice("Windows", 10f, Color(0xFF092D40)),
                            PieChartData.Slice("Pixel", 10f, Color(0xFF092D10)),
                            PieChartData.Slice("Samsung", 20f, Color(0xFF092D70)),
                            PieChartData.Slice("Oneplus", 0f, Color(0xFF092D80)),
                        ),
                        plotType = PlotType.Pie
                    )

                    val pieChartConfig =
                        PieChartConfig(
                            percentVisible = true,
                            strokeWidth = 120f,
                            percentColor = Color.Black,
                            activeSliceAlpha = .9f,
                            isEllipsizeEnabled = true,
                            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
                            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
                            isAnimationEnable = true,
                            showSliceLabels = true,
                            chartPadding = 25,
                        )
                    Column {
                        Legends(
                            legendsConfig = DataUtils.getLegendsConfigFromPieChartData(
                                pieChartData,
                                3
                            )
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
                }
            }
        }
    }
}
