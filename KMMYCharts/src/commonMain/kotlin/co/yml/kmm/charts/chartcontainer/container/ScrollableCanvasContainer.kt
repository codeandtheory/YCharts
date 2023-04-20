package co.yml.kmm.charts.chartcontainer.container

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.round
import co.yml.kmm.charts.chartcontainer.gestures.detectZoomGesture
import co.yml.kmm.charts.util.LoggingFile

/**
 *
 * ScrollableCanvasContainer is a container used to draw any graph which supports scroll,
 * zoom & tap or drag gestures.
 * @param modifier : All modifier related property.
 * @param calculateMaxDistance: callback to calculate the maximum scrolling distance.
 * @param onDraw: Draw any canvas inside the onDraw scope using the input params in the lambda fxn
 * @param drawXAndYAxis: Draw the X and Y axis along with the drawing area.
 * @param containerBackgroundColor: Background color of the whole container.
 * @param isPinchZoomEnabled: True if user can zoom in and out else false
 * @param layoutDirection: Used to define the direction of scroll.
 * @param onPointClicked: Callback for tap detected along with offset for tap.
 * @param onScroll: Callback when user starts scrolling the graph.
 * @param onZoomInAndOut: Callback when user starts zoomIn and Out w.r.t to the graph
 * @param scrollOrientation: Used to define the scroll orientation
 */

@Composable
internal fun ScrollableCanvasContainer(
    modifier: Modifier,
    calculateMaxDistance: DrawScope.(Float) -> Float,
    onDraw: DrawScope.(Float, Float) -> Unit,
    drawXAndYAxis: @Composable BoxScope.(Float, Float) -> Unit,
    containerBackgroundColor: Color = Color.White,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    onPointClicked: (Offset, Float) -> Unit = { _, _ -> },
    isPinchZoomEnabled: Boolean = true,
    onScroll: () -> Unit = {},
    onZoomInAndOut: () -> Unit = {},
    scrollOrientation: Orientation = Orientation.Horizontal
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

    var offset by remember { mutableStateOf(IntOffset.Zero) }
    var prevZoom by remember { mutableStateOf(1f) }
    var prevOffset by remember { mutableStateOf(Offset.Zero) }

    val scale = remember { mutableStateOf(1f) }
    val minScale = 1f
    val maxScale = 3f

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
                    state = scrollState, scrollOrientation, enabled = true
                )
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        LoggingFile().log("Harshaaa - $it value")
                        onPointClicked(it, scrollOffset.value)
                    })
                }
               .pointerInput(Unit) {
                // Listen for the zoom gesture events
                detectTransformGestures { _, _, zoom, _ ->
                    xZoom.value *= zoom
                }
            }
                ,onDraw = {
                    maxScrollOffset.value = calculateMaxDistance(xZoom.value)
                    onDraw(scrollOffset.value, xZoom.value)
                })
            drawXAndYAxis(scrollOffset.value, xZoom.value)
        }
    }
}

/**
 * Returns the scroll state within the start and computed max scrollOffset & filters invalid scroll states.
 * @param currentScrollOffset: Current scroll offset when user trying to scroll the canvas.
 * @param computedMaxScrollOffset: Maximum calculated scroll offset for given data set.
 */
fun checkAndGetMaxScrollOffset(currentScrollOffset: Float, computedMaxScrollOffset: Float): Float {
    return when {
        currentScrollOffset < 0f -> 0f
        currentScrollOffset > computedMaxScrollOffset -> computedMaxScrollOffset
        else -> currentScrollOffset
    }
}
