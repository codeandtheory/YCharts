package com.ygraph.components.piechart.charts

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.ygraph.components.common.extensions.collectIsTalkbackEnabledAsState
import com.ygraph.components.common.model.PlotType
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData
import com.ygraph.components.piechart.utils.convertTouchEventPointToAngle
import com.ygraph.components.piechart.utils.proportion
import com.ygraph.components.piechart.utils.sweepAngles
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


/**
 * Compose function for Drawing Donut chart
 * @param modifier : All modifier related property
 * @param pieChartData: data list for the pie chart
 * @param pieChartConfig: configuration for the pie chart
 * @param onSliceClick(pieChartData.Slice)->Unit: The event that captures the click
 */
@Composable
fun DonutPieChart(
    modifier: Modifier,
    pieChartData: PieChartData,
    pieChartConfig: PieChartConfig,
    onSliceClick: (PieChartData.Slice) -> Unit = {}
) {
    val talkbackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()
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

    var activePie by rememberSaveable { mutableStateOf(-1) }
    val focusRequesterContainer = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    var isContainerFocused by remember { mutableStateOf(false) }
    var containerFocusedText by remember { mutableStateOf("") }
    val focusRequesterNextBtn = remember { FocusRequester() }
    val focusRequesterPrevBtn = remember { FocusRequester() }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (pieChartConfig.isLegendVisible) {
            Legends(
                pieChartData = pieChartData,
                pieChartConfig = pieChartConfig
            )
        }

        BoxWithConstraints(modifier = modifier
            .aspectRatio(1f)
            .focusRequester(focusRequesterContainer)
            .focusable()
            .semantics {
                contentDescription = if (isContainerFocused) containerFocusedText else ""
            }) {

            val sideSize = Integer.min(constraints.maxWidth, constraints.maxHeight)
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
                        val fontSize = pieChartConfig.percentageFontSize.toPx()
                        drawText(
                            "${proportions[activePie].roundToInt()}%",
                            (sideSize / 2) + fontSize / 4, (sideSize / 2) + fontSize / 3,
                            Paint().apply {
                                color = pieChartConfig.percentColor.toArgb()
                                textSize = fontSize
                                textAlign = Paint.Align.CENTER
                                typeface = pieChartConfig.percentageTypeface

                            }
                        )
                    }
            }
        }
    }
    if (talkbackEnabled) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(modifier = Modifier
                .height(40.dp)
                .focusRequester(focusRequesterPrevBtn)
                .focusable(), onClick = {
                if (activePie <= 0) {
                    activePie = pieChartData.slices.size - 1
                } else {
                    activePie--
                }
                coroutineScope.launch {
                    isContainerFocused = true
                    containerFocusedText =
                        pieChartConfig.chartAccessibilityConfig.contentDescription(
                            activePie,
                            pieChartData.slices.size,
                            proportions[activePie].roundToInt(),
                            pieChartData.slices[activePie]
                        )
                    focusRequesterContainer.requestFocus()
                    // T0D0 : Add callback to notify container description is announced completely
                    // For now handled using delay, need to be removed
                    delay(5000)
                    isContainerFocused = false
                    focusRequesterPrevBtn.requestFocus()
                }
            }) {
                Text(text = pieChartConfig.chartAccessibilityConfig.prevButtonText)
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = {
                    if (activePie == pieChartData.slices.size - 1) {
                        activePie = 0
                    } else {
                        activePie++
                    }
                    coroutineScope.launch {
                        isContainerFocused = true
                        containerFocusedText =
                            pieChartConfig.chartAccessibilityConfig.contentDescription(
                                activePie,
                                pieChartData.slices.size,
                                proportions[activePie].roundToInt(),
                                pieChartData.slices[activePie]
                            )
                        focusRequesterContainer.requestFocus()
                        // T0D0 : Add callback to notify container description is announced completely
                        // For now handled using delay, need to be removed
                        delay(5000)
                        isContainerFocused = false
                        focusRequesterNextBtn.requestFocus()
                    }
                }, modifier = Modifier
                    .height(40.dp)
                    .focusRequester(focusRequesterNextBtn)
                    .focusable()
            ) {
                Text(text = pieChartConfig.chartAccessibilityConfig.nextButtonText)
            }
        }
        LaunchedEffect(key1 = Unit, block = {
            // T0D0: To be removed once we have support to get callback on announcement completely
            delay(1000)
            focusRequesterNextBtn.requestFocus()
        })
    }
}
