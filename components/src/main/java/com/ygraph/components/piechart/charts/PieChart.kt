package com.ygraph.components.piechart.charts

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun PieChart(
    modifier: Modifier,
    values: List<Float>,
    colors: List<Color>,
    legends: List<String> = emptyList(),
    isLegendVisible: Boolean = legends.isNotEmpty(),
    startAngle: Float = 270f,
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

    sweepAngles.forEachIndexed { index, _ ->
        progressSize.add(sweepAngles[index] + progressSize[index - 1])
    }

    var activePie by rememberSaveable {
        mutableStateOf(-1)
    }

    BoxWithConstraints(modifier = modifier) {

        val sideSize = Integer.min(constraints.maxWidth, constraints.maxHeight)
        val padding = (sideSize *  20) / 100f
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
                sAngle += arcProgress
            }
        }
    }
}