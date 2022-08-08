package com.ygraph.components.piechart.charts

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ygraph.components.piechart.utils.convertTouchEventPointToAngle
import kotlin.math.roundToInt

/**
 * All modifier related property [modifier].
 * Value list for the pie chart [values].
 * Colors for the pie chart [colors].
 * StrokeWidth for the pie chart [strokeWidth].
 * StartAngle for the pie chart, from where to start draw [StartAngle].
 * Legends should show or not  [isLegendVisible].
 * Label list  [legends].
 * Percentage text font size  [percentageFontSize].
 * Percentage text visibility [percentVisible].
 * Percentage text color [percentColor].
 */
@Composable
fun DonutPieChart(
    modifier: Modifier,
    values: List<Float>,
    colors: List<Color>,
    strokeWidth: Float = 100f,
    startAngle: Float = 270f,
    isLegendVisible: Boolean = false,
    legends: List<String> = emptyList(),
    percentageFontSize: Dp = 60.dp,
    percentVisible: Boolean = false,
    percentColor: Color = Color.White
) {
    // Sum of all the values
    val sumOfValues = values.sum()

    // Calculate each proportion value
    val proportions = values.map {
        it * 100 / sumOfValues
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }

    val progressSize = mutableListOf<Float>()
    progressSize.add(sweepAngles.first())

    for (x in 1 until sweepAngles.size)
        progressSize.add(sweepAngles[x] + progressSize[x - 1])

    var activePie by rememberSaveable {
        mutableStateOf(-1)
    }


    BoxWithConstraints(modifier = modifier) {

        val sideSize = Integer.min(constraints.maxWidth, constraints.maxHeight)
        val padding = (sideSize * 20) / 100f
        val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

        val pathPortion = remember {
            Animatable(initialValue = 0f)
        }

        LaunchedEffect(key1 = true) {
            pathPortion.animateTo(
                1f, animationSpec = tween(1000)
            )
        }

        if (isLegendVisible) {
            Legends(
                values = values,
                colors = colors,
                legend = legends
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

            var sAngle = startAngle

            sweepAngles.forEachIndexed { index, arcProgress ->
                drawPie(
                    color = colors[index],
                    startAngle = sAngle,
                    arcProgress = arcProgress * pathPortion.value,
                    size = size,
                    padding = padding,
                    isDonut = true,
                    strokeWidth = strokeWidth,
                    isActive = activePie == index
                )
                sAngle += arcProgress
            }

            if (activePie != -1 && percentVisible)
                drawContext.canvas.nativeCanvas.apply {
                    val fontSize = percentageFontSize.toPx()
                    drawText(
                        "${proportions[activePie].roundToInt()}%",
                        (sideSize / 2) + fontSize / 4, (sideSize / 2) + fontSize / 3,
                        Paint().apply {
                            color = percentColor.toArgb()
                            textSize = fontSize
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
        }
    }
}
