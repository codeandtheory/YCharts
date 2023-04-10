package co.yml.ycharts.app.presentation

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.utils.proportion
import co.yml.charts.common.components.Legends
import co.yml.charts.common.utils.DataUtils
import co.yml.ycharts.app.R
import co.yml.ycharts.app.ui.compositions.AppBarWithBackButton
import co.yml.ycharts.app.ui.theme.YChartsTheme

/**
 * Donut chart activity
 *
 * @constructor Create empty Donut chart activity
 */
@OptIn(ExperimentalMaterialApi::class)
class DonutChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_donut_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    val context = LocalContext.current
                    LazyColumn(content = {
                        items(2) { item ->
                            when (item) {
                                0 -> {
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = getString(R.string.simple_donut_chart),
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Box(
                                        modifier = Modifier
                                            .padding(it)
                                            .fillMaxWidth()
                                    ) {
                                        Spacer(modifier = Modifier.height(20.dp))
                                        SimpleDonutChart(context)
                                    }
                                }
                                1 -> {
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = getString(R.string.multiple_donuts),
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                    MultipleSmallDonutCharts(context)
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}

/**
 * Simple donut chart
 *
 * @param context
 */
@ExperimentalMaterialApi
@Composable
private fun SimpleDonutChart(context: Context) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val data = DataUtils.getDonutChartData()
    // Sum of all the values
    val sumOfValues = data.totalLength

    // Calculate each proportion value
    val proportions = data.slices.proportion(sumOfValues)
    val pieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 120f,
            percentColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            percentageFontSize = 42.sp
        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = data, 3))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            data,
            pieChartConfig
        ) { slice ->
            Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * Multiple small donut charts
 *
 * @param context
 */
@ExperimentalMaterialApi
@Composable
private fun MultipleSmallDonutCharts(context: Context) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val data = DataUtils.getDonutChartData()
    // Sum of all the values
    val sumOfValues = data.totalLength

    // Calculate each proportion value
    val proportions = data.slices.proportion(sumOfValues)
    val firstPieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 50f,
            percentColor = Color.Black,
            backgroundColor = Color.Yellow,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            percentageFontSize = 42.sp
        )
    val secondPieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 50f,
            percentColor = Color.White,
            backgroundColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            percentageFontSize = 42.sp
        )
    val thirdPieChartConfig =
        PieChartConfig(
            percentVisible = true,
            strokeWidth = 50f,
            percentColor = Color.Black,
            activeSliceAlpha = .9f,
            backgroundColor = Color.LightGray,
            isEllipsizeEnabled = true,
            percentageTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            percentageFontSize = 42.sp
        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = data, 3))
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                DonutPieChart(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    data,
                    firstPieChartConfig
                ) { slice ->
                    Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
                }
                Spacer(modifier = Modifier.width(30.dp))
            }
            item {
                DonutPieChart(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    data,
                    secondPieChartConfig
                ) { slice ->
                    Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
                }
                Spacer(modifier = Modifier.width(30.dp))
            }
            item {
                DonutPieChart(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    data,
                    thirdPieChartConfig
                ) { slice ->
                    Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
                }
                Spacer(modifier = Modifier.width(30.dp))
            }
        }
    }
}
