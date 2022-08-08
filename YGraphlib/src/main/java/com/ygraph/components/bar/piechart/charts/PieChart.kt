package com.ygraph.components.bar.piechart.charts

import android.graphics.Paint
import android.graphics.Rect
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ygraph.components.piechart.charts.Legends
import com.ygraph.components.piechart.charts.drawPie
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import com.ygraph.components.bar.piechart.Constants.Companion.DEFAULT_PADDING
import com.ygraph.components.bar.piechart.Constants.Companion.DEFAULT_START_ANGLE
import com.ygraph.components.bar.piechart.Constants.Companion.DEFAULT_SLICE_LABEL_TEXT_SIZE
import com.ygraph.components.bar.piechart.Constants.Companion.MINIMUM_PERCENTAGE_FOR_SLICE_LABELS

/**
 * Used to Draw the pie chart
 * All modifier related property [modifier].
 * Value list for the pie chart [values].
 * Colors for the pie chart [colors].
 * Control the legends visibility[isLegendVisible].
 * Label list  [legends].
 * Starting angle [startAngle].
 * Control the labels visibility [showSliceLabels].
 * Text size of the labels [sliceLabelTextSize].
 * Text color of the labels [sliceLabelTextColor].
 * Text color of the legend labels [legendLabelTextColor].
 */
@Composable
fun PieChart(
    modifier: Modifier,
    values: List<Float>,
    colors: List<Color>,
    isLegendVisible: Boolean = false,
    legends: List<String> = emptyList(),
    startAngle: Float = DEFAULT_START_ANGLE,
    showSliceLabels: Boolean = true,
    sliceLabelTextSize: TextUnit = DEFAULT_SLICE_LABEL_TEXT_SIZE.sp,
    sliceLabelTextColor: Color = White,
    legendLabelTextColor: Color = Black,
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
                legend = legends,
                legendTextColor = legendLabelTextColor
            )
        }

        BoxWithConstraints(
            modifier = modifier,
        ) {

            val sideSize = Integer.min(constraints.maxWidth, constraints.maxHeight)
            val padding = (sideSize * DEFAULT_PADDING) / 100f
            val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

            val pathPortion = remember {
                Animatable(initialValue = 0f)
            }

            LaunchedEffect(key1 = Unit) {
                pathPortion.animateTo(
                    1f, animationSpec = tween(1000)
                )
            }

            Canvas(
                modifier = Modifier
                    .width(sideSize.dp)
                    .height(sideSize.dp)
            ) {

                var sAngle = startAngle

                val sliceLabelPaint = Paint().apply {
                    isAntiAlias = true
                    textSize = sliceLabelTextSize.toPx()
                    textAlign = Paint.Align.CENTER
                    color = sliceLabelTextColor.toArgb()
                }

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

                    //  if percentage is less than 5 width of slice will be very small
                    if (showSliceLabels && proportions[index] >= MINIMUM_PERCENTAGE_FOR_SLICE_LABELS) {

                        val arcCenter = sAngle + (arcProgress / 2)

                        //middle point radius is half of the radius of the pie chart
                        val pointRadius = size.width / 4

                        /*Calculate the x & y co-ordinates to show the label/percentage tex
                        * find points using angle and radius
                        *https://en.wikipedia.org/wiki/Polar_coordinate_system#Converting_between_polar_and_Cartesian_coordinates
                        * */

                        val x =
                            (pointRadius * cos(Math.toRadians(arcCenter.toDouble()))) +
                                    size.center.x + padding / 2
                        val y =
                            (pointRadius * sin(Math.toRadians(arcCenter.toDouble()))) +
                                    size.center.y + padding / 2

                        // find the height of text
                        val rect = Rect()
                        sliceLabelPaint.getTextBounds(
                            legends[index],
                            0,
                            legends[index].length,
                            rect
                        )

                        drawIntoCanvas {

                            val finalX = x.toFloat()
                            val finalY = y.toFloat()

                            // rotating canvas to adjust the text alignment
                            it.nativeCanvas.rotate(
                                arcCenter,
                                finalX,
                                finalY

                            )

                            it.nativeCanvas.drawText(
                                legends[index],
                                finalX,
                                finalY + abs(rect.height()) / 2,
                                sliceLabelPaint
                            )
                            // rotating back to the original position
                            it.nativeCanvas.rotate(
                                -arcCenter,
                                finalX,
                                finalY
                            )

                        }
                    }

                    sAngle += arcProgress
                }
            }
        }
    }
}
