package co.yml.kmm.charts.ui.piechart.charts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import co.yml.kmm.charts.common.components.accessibility.AccessibilityBottomSheetDialog
import co.yml.kmm.charts.common.components.accessibility.SliceInfo
import co.yml.kmm.charts.common.model.PlotType
import co.yml.kmm.charts.ui.piechart.models.PieChartConfig
import co.yml.kmm.charts.ui.piechart.models.PieChartData
import co.yml.kmm.charts.ui.piechart.utils.convertTouchEventPointToAngle
import co.yml.kmm.charts.ui.piechart.utils.proportion
import co.yml.kmm.charts.ui.piechart.utils.sweepAngles
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


/**
 * Compose function for Drawing Donut chart
 * @param modifier : All modifier related property
 * @param pieChartData: data list for the pie chart
 * @param pieChartConfig: configuration for the pie chart
 * @param onSliceClick(pieChartData.Slice)->Unit: The event that captures the click
 */
@OptIn(ExperimentalTextApi::class)
@ExperimentalMaterialApi
@Composable
internal fun DonutPieChart(
    modifier: Modifier,
    pieChartData: PieChartData,
    pieChartConfig: PieChartConfig,
    talkBackEnabled: Boolean,
    onSliceClick: (PieChartData.Slice) -> Unit = {}
) {
    // Sum of all the values
    val sumOfValues = pieChartData.totalLength

    // Calculate each proportion value
    val proportions = pieChartData.slices.proportion(sumOfValues)

    // Convert each proportions to angle
    val sweepAngles = proportions.sweepAngles()

    val progressSize = mutableListOf<Float>()
    progressSize.add(sweepAngles.first())

    for (x in 1 until sweepAngles.size) {
        progressSize.add(sweepAngles[x] + progressSize[x - 1])
    }

    var activePie by rememberSaveable {
        mutableStateOf(-1)
    }
    val textMeasurer = rememberTextMeasurer()
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    if (accessibilitySheetState.isVisible && talkBackEnabled
        && pieChartConfig.accessibilityConfig.shouldHandleBackWhenTalkBackPopUpShown
    ) {
        // todo handle accessibility bottomsheet dismissal
    }
    Surface(
        modifier = modifier
    ) {
        BoxWithConstraints(
            modifier = modifier
                .aspectRatio(1f)
                .semantics {
                    contentDescription = pieChartConfig.accessibilityConfig.chartDescription
                }
                .clickable {
                    if (talkBackEnabled) {
                        scope.launch {
                            accessibilitySheetState.animateTo(
                                ModalBottomSheetValue.Expanded
                            )
                        }
                    }
                }) {

            val sideSize =
                if (constraints.maxWidth < constraints.maxHeight) constraints.maxWidth else constraints.maxHeight
            val padding = (sideSize * pieChartConfig.chartPadding) / 100f
            val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

            val pathPortion = remember {
                Animatable(initialValue = 0f)
            }

            if (pieChartConfig.isAnimationEnable) {
                LaunchedEffect(key1 = Unit) {
                    pathPortion.animateTo(
                        1f, animationSpec = tween(pieChartConfig.animationDuration)
                    )
                }
            }

            Canvas(
                modifier = Modifier
                    .width(sideSize.dp)
                    .height(sideSize.dp)
                    .pointerInput(true) {

                        detectTapGestures {
                            val clickedAngle = convertTouchEventPointToAngle(
                                sideSize.toFloat(),
                                sideSize.toFloat(),
                                it.x,
                                it.y
                            )
                            progressSize.forEachIndexed { index, item ->
                                if (clickedAngle <= item) {
                                    if (activePie != index)
                                        activePie = index
                                    onSliceClick(pieChartData.slices[index])
                                    return@detectTapGestures
                                }
                            }
                        }
                    }

            ) {

                var sAngle = pieChartConfig.startAngle

                sweepAngles.forEachIndexed { index, arcProgress ->
                    drawPie(
                        color = pieChartData.slices[index].color,
                        startAngle = sAngle,
                        arcProgress = if (pieChartConfig.isAnimationEnable)
                            arcProgress * pathPortion.value else arcProgress,
                        size = size,
                        padding = padding,
                        isDonut = pieChartData.plotType == PlotType.Donut,
                        strokeWidth = pieChartConfig.strokeWidth,
                        isActive = activePie == index,
                        pieChartConfig = pieChartConfig
                    )
                    sAngle += arcProgress
                }

                if (activePie != -1 && pieChartConfig.percentVisible)
                    drawContext.canvas.nativeCanvas.apply {
                        val percentageText =
                            AnnotatedString("${proportions[activePie].roundToInt()}%")
                        val textHeight = textMeasurer.measure(percentageText).size.height
                        val textWidth = textMeasurer.measure(percentageText).size.width

                        drawText(
                            textMeasurer = textMeasurer,
                            text = percentageText,
                            topLeft = Offset(
                                ((sideSize / 2) - textWidth).toFloat(),
                                ((sideSize / 2) - textHeight).toFloat()
                            ),
                            maxSize = IntSize(1440, 1050),
                            style = TextStyle(
                                color = pieChartConfig.percentColor,
                                fontSize = pieChartConfig.percentageFontSize,
                                textAlign = TextAlign.Center,
                                fontWeight = W700
                            )
                        )
                    }
            }
        }
        if (talkBackEnabled) {
            with(pieChartConfig) {
                AccessibilityBottomSheetDialog(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Color.White,
                    content = {
                        LazyColumn {
                            items(pieChartData.slices.size) { index ->
                                SliceInfo(
                                    pieChartData.slices[index],
                                    proportions[index].roundToInt()
                                )
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
