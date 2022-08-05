package com.ygraph.components.piechart.charts

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PieChart(
    modifier: Modifier,
    values: List<Float>,
    colors: List<Color>,
    isLegendVisible: Boolean = false,
    legends: List<String> = emptyList(),
    startAngle: Float = 270f,
    showSliceLabels: Boolean = true,
    sliceLabelTextSize: TextUnit = 12.sp,
    sliceLabelTextColor: Color = White,
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


    val activePie by rememberSaveable {
        mutableStateOf(-1)
    }
    Column(
        modifier = modifier,
    ) {

        if (isLegendVisible) {
            Legends(
                values = values,
                colors = colors,
                legend = legends
            )
        }

        BoxWithConstraints(
            modifier = modifier,
        ) {

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

            Canvas(
                modifier = Modifier
                    .width(sideSize.dp)
                    .height(sideSize.dp)
                    .pointerInput(true) {
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
                        isDonut = false,
                        isActive = activePie == index
                    )

                    if (showSliceLabels) {

                        val arcCenter = sAngle + (arcProgress / 2)

                        //middle point radius is half of the radius of the pie chart
                        val pointRadius = size.width / 4

                        /*Calculate the x & y co-ordinates to show the label/percentage tex
                        * find points using angle and radius
                        *https://en.wikipedia.org/wiki/Polar_coordinate_system#Converting_between_polar_and_Cartesian_coordinates
                        * */

                        val x =
                            (pointRadius * cos(Math.toRadians(arcCenter.toDouble()))) +
                                    size.center.x +padding/2
                        val y =
                            (pointRadius * sin(Math.toRadians(arcCenter.toDouble()))) +
                                    size.center.y +padding/2


                        drawIntoCanvas {

                            it.nativeCanvas.rotate(sAngle + (arcProgress / 2))
                            it.nativeCanvas.drawText(
                                legends[index],
                                x.toFloat(),
                                y.toFloat(),
                                Paint().apply {
                                    isAntiAlias = true
                                    textSize = sliceLabelTextSize.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = sliceLabelTextColor.toArgb()
                                }
                            )
                        }
                    }

                    sAngle += arcProgress
                }


            }
        }

    }
}