package com.ygraph.components.graphcontainer.container

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.ygraph.components.graphcontainer.gestures.detectZoomGesture

/**
 *
 * ScrollableCanvasContainer is a container used to draw any graph which supports scroll,
 * zoom & tap or drag gestures.
 * @param modifier : All modifier related property.
 * @param calculateMaxDistance: callback to calculate the maximum scrolling distance.
 * @param onDraw: Draw any canvas inside the onDraw scope using the input params in the lambda fxn
 * @param drawXAndYAxis: Draw the X and Y axis along with the drawing area.
 * @param containerBackgroundColor: Background color of the whole container.
 * @param layoutDirection: Used to define the direction of scroll.
 * @param onPointClicked: Callback for tap detected along with offset for tap.
 * @param onScroll: Callback when user starts scrolling the graph.
 */

@Composable
fun ScrollableCanvasContainer(
    modifier: Modifier,
    calculateMaxDistance: DrawScope.(Float) -> Float,
    onDraw: DrawScope.(Float, Float) -> Unit,
    drawXAndYAxis: @Composable BoxScope.(Float, Float) -> Unit,
    containerBackgroundColor: Color = Color.White,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    onPointClicked: (Offset, Float) -> Unit = { _, _ -> },
    isPinchZoomEnabled: Boolean = true,
    onScroll: () -> Unit = {},
    onZoomInAndOut: () -> Unit = {}
) {
    val scrollOffset = remember { mutableStateOf(0f) }
    val maxScrollOffset = remember { mutableStateOf(0f) }
    val xZoom = remember { mutableStateOf(1f) }
    val scrollState = rememberScrollableState { delta ->
        scrollOffset.value -= delta
        scrollOffset.value = checkAndGetMaxScrollOffset(
            scrollOffset.value,
            maxScrollOffset.value
        )
        delta
    }

    if (scrollState.isScrollInProgress){
        onScroll()
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection,
    ) {
        Box(
            modifier = modifier.clipToBounds(),
        ) {
            Canvas(modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight()
                .fillMaxWidth()
                .background(containerBackgroundColor)
                .scrollable(
                    state = scrollState, Orientation.Horizontal, enabled = true
                )
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onPointClicked(it, scrollOffset.value)
                    })
                }
                .pointerInput(Unit) {
                    detectZoomGesture(
                        isZoomAllowed = isPinchZoomEnabled,
                        onZoom = { zoom ->
                            xZoom.value *= zoom
                            onZoomInAndOut()
                        }
                    )
                },
                onDraw = {
                    maxScrollOffset.value = calculateMaxDistance(xZoom.value)
                    onDraw(scrollOffset.value, xZoom.value)
                })
            drawXAndYAxis(scrollOffset.value, xZoom.value)
        }
    }
}

fun checkAndGetMaxScrollOffset(currentScrollOffset: Float, computedMaxScrollOffset: Float): Float {
    return when {
        currentScrollOffset < 0f -> 0f
        currentScrollOffset > computedMaxScrollOffset -> computedMaxScrollOffset
        else -> currentScrollOffset
    }
}
