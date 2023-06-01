@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package co.yml.charts.ui.barchart


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import co.yml.charts.common.components.ItemDivider
import co.yml.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.charts.common.components.accessibility.BarInfo
import co.yml.charts.common.extensions.collectIsTalkbackEnabledAsState
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import kotlinx.coroutines.launch


/**
 *
 * [BarChart] compose method for drawing bar chart.
 * @param modifier: All modifier related properties
 * @param barChartData : All data needed to Bar Chart
 * @see [BarChartData] Data class to save all params related to Bar Chart
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BarChart(modifier: Modifier, barChartData: BarChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()

    if (accessibilitySheetState.isVisible && isTalkBackEnabled
        && barChartData.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        BackHandler {
            scope.launch {
                accessibilitySheetState.hide()
            }
        }
    }
    Surface(modifier) {
        when (barChartData.barChartType) {
            BarChartType.HORIZONTAL -> HorizontalBarChart(
                barChartData = barChartData,
                modifier = modifier,
                scope = scope,
                accessibilitySheetState = accessibilitySheetState,
                isTalkBackEnabled = isTalkBackEnabled
            )

            BarChartType.VERTICAL -> VerticalBarChart(
                barChartData = barChartData,
                modifier = modifier,
                scope = scope,
                accessibilitySheetState = accessibilitySheetState,
                isTalkBackEnabled = isTalkBackEnabled
            )
        }

        if (isTalkBackEnabled) {
            with(barChartData) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Color.White,
                    content = {
                        LazyColumn {
                            items(chartData.size) { index ->
                                Column {
                                    BarInfo(
                                        if (barChartType == BarChartType.VERTICAL) {
                                            xAxisData.axisLabelDescription(
                                                xAxisData.labelData(
                                                    index
                                                )
                                            )
                                        } else {
                                            yAxisData.axisLabelDescription(
                                                yAxisData.labelData(
                                                    index
                                                )
                                            )
                                        },
                                        chartData[index].description,
                                        chartData[index].color
                                    )
                                    ItemDivider(
                                        thickness = accessibilityConfig.dividerThickness,
                                        dividerColor = accessibilityConfig.dividerColor
                                    )
                                }
                            }
                        }
                    },
                    popUpTopRightButtonTitle = accessibilityConfig.popUpTopRightButtonTitle,
                    popUpTopRightButtonDescription = accessibilityConfig.popUpTopRightButtonDescription,
                    sheetState = accessibilitySheetState
                )
            }
        }
    }
}
