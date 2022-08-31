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
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.ygraph.components.common.constants.ContainerConstants.DEFAULT_DETECT_DRAG_TIME_OUT
import com.ygraph.components.graphcontainer.gestures.detectDragZoomGesture

/**
 *
 * ScrollableCanvasContainer is a container used to draw any graph which supports scroll,
 * zoom & tap or drag gestures.
 * @param modifier : All modifier related property.
 * @param calculateMaxDistance: callback to calculate the maximum scrolling distance.
 * @param onDraw: draw any canvas inside the onDraw scope using the input params in the lambda fxn
 * @param drawXAndYAxis: draw the X and Y axis along with the drawing area.
 * @param containerBackgroundColor: background color of the whole container.
 * @param layoutDirection: used to define the direction of scroll.
 * @param isTapGestureEnabled : true if tap gesture is needed else false to support drag gesture by default.
 * @param onPointSelected: callback for tap detected along with offset for tap.
 * @param onDragStart: callback to notify if user started dragging.
 * @param onDragEnd: callback to notify if user stopped dragging.
 * @param onDragging: callback when user is continuously drags between points.
 */

@Composable
fun ScrollableCanvasContainer(
    modifier: Modifier,
    calculateMaxDistance: DrawScope.(Float) -> Float,
    onDraw: DrawScope.(Float, Float) -> Unit,
    drawXAndYAxis: @Composable BoxScope.(Float, Float) -> Unit,
    containerBackgroundColor: Color = Color.White,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    isTapGestureEnabled: Boolean = false,
    onPointSelected: (Offset, Float) -> Unit = { _, _ -> },
    isPinchZoomEnabled: Boolean = true,
    onDragStart: (Offset) -> Unit = {},
    onDragEnd: () -> Unit = {},
    onDragging: (change: PointerInputChange, dragAmount: Offset) -> Unit = { _, _ -> },
    onScrolling: () -> Unit = {}
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
        onScrolling()
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
                    // Only one gesture can be supported at a time either tap or drag
                    if (isTapGestureEnabled) {
                        detectTapGestures(onTap = {
                            onPointSelected(it, scrollOffset.value)
                        })
                    } else {
                        detectDragZoomGesture(
                            isZoomAllowed = isPinchZoomEnabled,
                            detectDragTimeOut = DEFAULT_DETECT_DRAG_TIME_OUT,
                            onDragStart = onDragStart,
                            onDragEnd = onDragEnd,
                            onZoom = { zoom ->
                                xZoom.value *= zoom
                            },
                            onDrag = onDragging
                        )
                    }
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
