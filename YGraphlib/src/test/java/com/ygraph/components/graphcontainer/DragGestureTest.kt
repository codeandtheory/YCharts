package com.ygraph.components.graphcontainer

import androidx.compose.ui.input.pointer.*
import com.ygraph.components.graphcontainer.gestures.awaitLongPressOrCancellation
import com.ygraph.components.graphcontainer.gestures.isPointerUp
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DragGestureTest {

    private val pointerInputChange1 = mockk<PointerInputChange>(relaxed = true)
    private val pointerId = mockk<PointerId>(relaxed = true)
    private val pointerScope = mockk<PointerInputScope>(relaxed = true)

    private val pointerEvent = mockk<PointerEvent>()

    @Test
    fun `Given selected pointerID should assert that pointer points up direction`() {
        // Given
        every { pointerInputChange1.id }.returns(pointerId)
        every { pointerInputChange1.pressed }.returns(false)
        every { pointerEvent.changes }.returns(
            listOf(
                pointerInputChange1
            )
        )
        // When
        val isPointerUp = pointerEvent.isPointerUp(pointerId = pointerId)

        // Then
        assertTrue(isPointerUp)
    }

    @Test
    fun `Given not selected pointerID should assert that pointer does not points up direction`() {
        // Given
        every { pointerInputChange1.id }.returns(pointerId)
        every { pointerInputChange1.pressed }.returns(true)
        every { pointerEvent.changes }.returns(
            listOf(
                pointerInputChange1
            )
        )

        // When
        val isPointerUp = pointerEvent.isPointerUp(pointerId = pointerId)

        // Then
        assertFalse(isPointerUp)
    }

    @Test
    fun `Given valid longPressTimeout should provide a point which should not be null `() =
        runBlocking {

            // Given
            coEvery {
                pointerScope.awaitPointerEventScope {
                    awaitPointerEvent(
                        PointerEventPass.Main
                    )
                }
            }.returns(pointerEvent)

            // When
            val pointerInputChange =
                pointerScope.awaitLongPressOrCancellation(initialDown = pointerInputChange1, 0L)
            // Then
            assertNotNull(pointerInputChange)
        }

}
