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
import com.ygraph.components.bar.piechart.Constants.DEFAULT_PADDING
import com.ygraph.components.bar.piechart.Constants.DEFAULT_SLICE_LABEL_TEXT_SIZE
import com.ygraph.components.bar.piechart.Constants.DEFAULT_START_ANGLE
import com.ygraph.components.bar.piechart.Constants.MINIMUM_PERCENTAGE_FOR_SLICE_LABELS
import com.ygraph.components.bar.piechart.Constants.ONE_HUNDRED
import com.ygraph.components.bar.piechart.Constants.TOTAL_ANGLE
import com.ygraph.components.bar.piechart.models.PieChartData
import com.ygraph.components.piechart.charts.drawPie
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin




/**
 * Compose function used to Draw the Pie Chart.
 * @param modifier : All modifier related property
 * @param pieChartData: data list for the pie chart
 * @param isLegendVisible: Control the legends visibility
 * @param startAngle: Starting angle
 * @param showSliceLabels: Control the labels visibility
 * @param sliceLabelTextSize: Text size of the labels
 * @param sliceLabelTextColor: Text color of the labels
 * @param legendLabelTextColor: Text color of the legend labels
 */
@Composable
fun PieChart(
    modifier: Modifier,
    pieChartData: PieChartData,
    isLegendVisible: Boolean = false,
    startAngle: Float = DEFAULT_START_ANGLE,
    showSliceLabels: Boolean = true,
    sliceLabelTextSize: TextUnit = DEFAULT_SLICE_LABEL_TEXT_SIZE.sp,
    sliceLabelTextColor: Color = White,
    legendLabelTextColor: Color = Black,
    animationDuration: Int = 1000
) {
    // Sum of all the values
    val sumOfValues = pieChartData.totalLength

    // Calculate each proportion value
    val proportions = pieChartData.slices.map {
        it.value * ONE_HUNDRED / sumOfValues
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        TOTAL_ANGLE * it / ONE_HUNDRED
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
                pieChartData = pieChartData,
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
                    1f, animationSpec = tween(animationDuration)
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
                        color = pieChartData.slices[index].color,
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
                            pieChartData.slices[index].label,
                            0,
                            pieChartData.slices[index].label.length,
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
                                pieChartData.slices[index].label,
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
