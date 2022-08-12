package com.ygraph.components.graphcontainer.gestures

import androidx.compose.foundation.gestures.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import kotlin.math.abs

internal suspend fun PointerInputScope.detectDragZoomGesture(
    isZoomAllowed: Boolean = false,
    detectDragTimeOut: Long,
    onDragStart: (Offset) -> Unit = { },
    onDragEnd: () -> Unit = { },
    onZoom: (zoom: Float) -> Unit,
    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
) {
    // T0D0 Need break down the logic to be unit tested
    if (isZoomAllowed) {
        forEachGesture {
            val down = awaitPointerEventScope {
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
            try {
                val drag = awaitLongPressOrCancellation(down, detectDragTimeOut)
                if (drag != null) {
                    onDragStart.invoke(drag.position)
                    awaitPointerEventScope {
                        if (
                            drag(drag.id) {
                                onDrag(it, it.positionChange())
                                if (it.positionChange() != Offset.Zero) it.consume()
                            }
                        ) {
                            // consume up if we quit drag gracefully with the up
                            currentEvent.changes.forEach {
                                if (it.changedToUp()) {
                                    if (it.pressed != it.previousPressed) it.consume()
                                }
                            }
                            onDragEnd()
                        } else {
                            onDragEnd()
                        }
                    }
                }
            } catch (exception: CancellationException) {
                onDragEnd()
                throw exception
            }
        }
    }
}

private suspend fun PointerInputScope.awaitLongPressOrCancellation(
    initialDown: PointerInputChange,
    longPressTimeout: Long
): PointerInputChange? {
    var longPress: PointerInputChange? = null
    var currentDown = initialDown
    return try {
        // wait for first tap up or long press
        withTimeout(longPressTimeout) {
            awaitPointerEventScope {
                var finished = false
                while (!finished) {
                    val event = awaitPointerEvent(PointerEventPass.Main)
                    if (event.changes.all { it.changedToUpIgnoreConsumed() }) {
                        // All pointers are up
                        finished = true
                    }

                    if (
                        event.changes.any {
                            it.isConsumed || it.isOutOfBounds(
                                size,
                                extendedTouchPadding
                            )
                        }
                    ) {
                        finished = true // Canceled
                    }

                    // Check for cancel by position consumption. We can look on the Final pass of
                    // the existing pointer event because it comes after the Main pass we checked
                    // above.
                    val consumeCheck = awaitPointerEvent(PointerEventPass.Final)
                    if (consumeCheck.changes.any { it.isConsumed }) {
                        finished = true
                    }
                    if (!event.isPointerUp(currentDown.id)) {
                        longPress = event.changes.firstOrNull { it.id == currentDown.id }
                    } else {
                        val newPressed = event.changes.firstOrNull { it.pressed }
                        if (newPressed != null) {
                            currentDown = newPressed
                            longPress = currentDown
                        } else {
                            // should technically never happen as we checked it above
                            finished = true
                        }
                    }
                }
            }
        }
        null
    } catch (_: TimeoutCancellationException) {
        longPress ?: initialDown
    }
}

private fun PointerEvent.isPointerUp(pointerId: PointerId): Boolean =
    changes.firstOrNull { it.id == pointerId }?.pressed != true
