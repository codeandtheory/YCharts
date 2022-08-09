package com.ygraph.components.bar.piechart.charts

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.utils.convertTouchEventPointToAngle
import kotlin.math.roundToInt
import com.ygraph.components.bar.piechart.Constants.DEFAULT_PADDING
import com.ygraph.components.bar.piechart.models.PieChartConfig
import com.ygraph.components.bar.piechart.models.PieChartData
import com.ygraph.components.piechart.charts.drawPie
import com.ygraph.components.piechart.utils.proportion
import com.ygraph.components.piechart.utils.sweepAngles


/**
 * Compose function for Drawing Donut chart
 * @param modifier : All modifier related property
 * @param pieChartData: data list for the pie chart
 * @param pieChartConfig: configuration for the pie chart
 */
@Composable
fun DonutPieChart(
    modifier: Modifier,
    pieChartData: PieChartData,
    pieChartConfig: PieChartConfig
) {
    // Sum of all the values
    val sumOfValues = pieChartData.totalLength

    // Calculate each proportion value
    val proportions = pieChartData.slices.proportion(sumOfValues)

    // Convert each proportions to angle
    val sweepAngles = proportions.sweepAngles()

    val progressSize = mutableListOf<Float>()
    progressSize.add(sweepAngles.first())

    for (x in 1 until sweepAngles.size)
        progressSize.add(sweepAngles[x] + progressSize[x - 1])

    var activePie by rememberSaveable {
        mutableStateOf(-1)
    }


    BoxWithConstraints(modifier = modifier) {

        val sideSize = Integer.min(constraints.maxWidth, constraints.maxHeight)
        val padding = (sideSize * DEFAULT_PADDING) / 100f
        val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

        val pathPortion = remember {
            Animatable(initialValue = 0f)
        }

        LaunchedEffect(key1 = Unit) {
            pathPortion.animateTo(
                1f, animationSpec = tween(pieChartConfig.animationDuration)
            )
        }

        if (pieChartConfig.isLegendVisible) {
            Legends(
                pieChartData = pieChartData,
                legendTextColor = pieChartConfig.legendLabelTextColor
            )
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
                    arcProgress = arcProgress * pathPortion.value,
                    size = size,
                    padding = padding,
                    isDonut = true,
                    strokeWidth = pieChartConfig.strokeWidth,
                    isActive = activePie == index
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
                        }
                    )
                }
        }
    }
}
