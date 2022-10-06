package com.app.ygraphs.presentation

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ygraphs.R
import com.app.ygraphs.ui.compositions.AppBarWithBackButton
import com.app.ygraphs.ui.theme.YGraphsTheme
import com.ygraph.components.AccessibilityBottomSheetDialog
import com.ygraph.components.common.components.Legends
import com.ygraph.components.common.utils.DataUtils
import com.ygraph.components.piechart.charts.DonutPieChart
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.utils.proportion

@OptIn(ExperimentalMaterialApi::class)
class DonutChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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
                    Box(
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

@ExperimentalMaterialApi
@Composable
private fun DonutChart1(context: Context) {
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
    Column {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = data, 3))
        DonutPieChart(
            modifier = Modifier
                .semantics { contentDescription = "Double tap to know the graph in detail" }
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        accessibilitySheetState.animateTo(
                            ModalBottomSheetValue.HalfExpanded
                        )
                    }
                }
                .height(500.dp),
            data,
            pieChartConfig
        ) { slice ->
            Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
        }
    }
    AccessibilityBottomSheetDialog(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        content = {
            LazyColumn {
                items(data.slices.size) { index ->
                    Row(
                        modifier = Modifier.semantics(mergeDescendants = true) {},
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(data.slices[index].color)
                                .size(30.dp)
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                            text = data.slices[index].label,
                            style = TextStyle(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = " Percentage : ${proportions[index].roundToInt()}",
                            style = TextStyle(),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        },
        sheetState = accessibilitySheetState
    )
}
