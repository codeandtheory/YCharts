package com.app.ygraphs.presentation

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.piechart.models.PieChartConfig

class DonutChartActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YGraphsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_donut_chart),
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
                        DonutChart1(context)
                    }
                }
            }
        }
    }
}

@Composable
private fun DonutChart1(context: Context) {
    val data = DataUtils.getDonutChartData()

    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 120f,
            percentColor = Color.Black,
            isLegendVisible = true,
            legendGridSize = 3,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            legendBadgeWidth = 20.dp,
            percentageFontSize = 42.sp
        )

    DonutPieChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        data,
        pieChartConfig,
        onSliceClick = { slice ->
            Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
        }
    )
}
