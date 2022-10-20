package co.yml.charts.chartcontainer.gestures

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateCentroidSize
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.positionChanged
import kotlin.math.abs

/**
 * Gesture support to detect and filter pointer scopes to give a zoom start callback
 * @param isZoomAllowed: True if user is allowed to zoom.
 * @param onZoom: Callback when zoom gesture is detected.
 */
internal suspend fun PointerInputScope.detectZoomGesture(
    isZoomAllowed: Boolean = true,
    onZoom: (zoom: Float) -> Unit
) {
    if (isZoomAllowed) {
        forEachGesture {
            awaitPointerEventScope {
                awaitFirstDown(requireUnconsumed = false)
            }
            awaitPointerEventScope {
                var zoom = 1f
                var pastTouchSlop = false
                val touchSlop = viewConfiguration.touchSlop

                do {
                    val event = awaitPointerEvent()
                    val canceled = event.changes.any { it.isConsumed }
                    if (event.changes.size == 1) break
                    else if (event.changes.size == 2) {
                        if (isZoomAllowed) {
                            if (!canceled) {
                                val zoomChange = event.calculateZoom()
                                if (!pastTouchSlop) {
                                    zoom *= zoomChange
                                    val centroidSize =
                                        event.calculateCentroidSize(useCurrent = false)
                                    val zoomMotion = abs(1 - zoom) * centroidSize
                                    if (zoomMotion > touchSlop) pastTouchSlop = true
                                }
                                if (pastTouchSlop) {
                                    if (zoomChange != 1f) onZoom(zoomChange)
                                    event.changes.forEach { if (it.positionChanged()) it.consume() }
                                }
                            }
                        }
                    } else break
                } while (!canceled && event.changes.any { it.pressed })
            }
        }
    }
}
