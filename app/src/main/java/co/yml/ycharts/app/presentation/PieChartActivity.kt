package co.yml.ycharts.app.presentation

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.common.components.Legends
import co.yml.charts.common.utils.DataUtils
import co.yml.ycharts.app.R
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme

/**
 * Pie chart activity
 *
 * @constructor Create empty Pie chart activity
 */
class PieChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
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
                                    0 -> {
                                        Text(
                                            modifier=Modifier.padding(12.dp),
                                            text = getString(R.string.simple_piechart),
                                            style = MaterialTheme.typography.subtitle1,
                                            fontWeight = FontWeight.Bold
                                        )
                                        SimplePiechart(LocalContext.current)
                                    }
                                    1 -> {
                                        Text(
                                            modifier=Modifier.padding(12.dp),
                                            text = getString(R.string.piechart_with_lables),
                                            style = MaterialTheme.typography.subtitle1,
                                            fontWeight = FontWeight.Bold
                                        )
                                        PiechartWithSliceLables(LocalContext.current)
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

/**
 * Simple piechart
 *
 * @param context
 */
@Composable
private fun SimplePiechart(context: Context) {
    val pieChartData = DataUtils.getPieChartData()
    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            isAnimationEnable = true,
            chartPadding = 30,
            backgroundColor = Color.Black,
            showSliceLabels = false,
            animationDuration = 1500
        )
    Column(modifier = Modifier.height(500.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData, 3))
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
}


/**
 * Piechart with slice lables
 *
 * @param context
 */
@Composable
private fun PiechartWithSliceLables(context: Context) {
    val pieChartData = DataUtils.getPieChartData2()

    val pieChartConfig =
        PieChartConfig(
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
            sliceLabelTypeface = Typeface.defaultFromStyle(Typeface.ITALIC),
            isAnimationEnable = true,
            chartPadding = 20,
            showSliceLabels = true,
            percentVisible = true
        )
    Column(modifier = Modifier.height(500.dp)) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData, 3))
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
}

