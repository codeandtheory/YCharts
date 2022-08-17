package com.ygraph.components.piechart.charts

import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import com.ygraph.components.common.extensions.getTextHeight
import com.ygraph.components.piechart.PieChartConstants.MINIMUM_PERCENTAGE_FOR_SLICE_LABELS
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData
import com.ygraph.components.piechart.utils.convertTouchEventPointToAngle
import com.ygraph.components.piechart.utils.getSliceCenterPoints
import com.ygraph.components.piechart.utils.proportion
import com.ygraph.components.piechart.utils.sweepAngles
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * Compose function used to Draw the Pie Chart.
 * @param modifier : All modifier related property
 * @param pieChartData: data list for the pie chart
 * @param pieChartConfig: configuration for the pie chart
 * @param onSliceClick(pieChartData.Slice)->Unit: The event that captures the click
 */
@Composable
fun PieChart(
    modifier: Modifier,
    pieChartData: PieChartData,
    pieChartConfig: PieChartConfig,
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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (pieChartConfig.isLegendVisible) {
            Legends(
                pieChartData = pieChartData,
                pieChartConfig = pieChartConfig,
            )
        }

        BoxWithConstraints(
            modifier = modifier.aspectRatio(1f),
        ) {

            val sideSize = Integer.min(constraints.maxWidth, constraints.maxHeight)
            val padding = (sideSize * pieChartConfig.chartPadding) / 100f
            val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

            val pathPortion = remember {
                Animatable(initialValue = 0f)
            }
            if (pieChartConfig.isAnimationEnable){
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

                val sliceLabelPaint = TextPaint().apply {
                    isAntiAlias = true
                    textSize = pieChartConfig.sliceLabelTextSize.toPx()
                    textAlign = Paint.Align.CENTER
                    color = pieChartConfig.sliceLabelTextColor.toArgb()
                    typeface = pieChartConfig.sliceLabelTypeface
                }

                sweepAngles.forEachIndexed { index, arcProgress ->
                    drawPie(
                        color = pieChartData.slices[index].color,
                        startAngle = sAngle,
                        arcProgress = if (pieChartConfig.isAnimationEnable)
                            arcProgress * pathPortion.value else arcProgress,
                        size = size,
                        padding = padding,
                        isDonut = false,
                        isActive = activePie == index,
                        pieChartConfig = pieChartConfig
                    )

                    //  if percentage is less than 5 width of slice will be very small
                    if (pieChartConfig.showSliceLabels && proportions[index] >= MINIMUM_PERCENTAGE_FOR_SLICE_LABELS) {

                        val (arcCenter, x, y) = getSliceCenterPoints(sAngle, arcProgress, size, padding)

                        // find the height of text
                       val height=  pieChartData.slices[index].label.getTextHeight(sliceLabelPaint)

                        var label = pieChartData.slices[index].label

                        val ellipsizedText by lazy {
                            TextUtils.ellipsize(
                                label,
                                sliceLabelPaint,
                                pieChartConfig.sliceMinTextWidthToEllipsize.toPx(),
                                pieChartConfig.sliceLabelEllipsizeAt
                            ).toString()
                        }

                        drawIntoCanvas {
                            it.nativeCanvas.withRotation(
                                arcCenter,
                                x,
                                y
                            ) {
                                if (pieChartConfig.percentVisible) {
                                    label = "$label ${proportions[index].roundToInt()}%"
                                }
                                it.nativeCanvas.drawText(
                                    if (pieChartConfig.isEllipsizeEnabled)
                                        ellipsizedText
                                    else label,
                                    x,
                                    y + abs(height) / 2,
                                    sliceLabelPaint
                                )
                            }
                        }
                    }

                    sAngle += arcProgress
                }
            }
        }
    }
}
