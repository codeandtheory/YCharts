package com.ygraph.components.graphcontainer

import com.ygraph.components.graphcontainer.container.checkAndGetMaxScrollOffset
import org.junit.Assert.assertEquals
import org.junit.Test

class ScrollableCanvasContainerTest {

    @Test
    fun `Given negative scroll offset canvas should not scroll`() {
        // Given
        val scrollOffset = -20f
        val maxScrollOffset = 200f

        // When
        val calculatedScrollOffset = checkAndGetMaxScrollOffset(scrollOffset, maxScrollOffset)

        // Then
        assertEquals(calculatedScrollOffset, 0f)
    }

    @Test
    fun `Given scroll offset canvas should support scroll if within maxScrollOffset`() {
        // Given
        val scrollOffset = 20f
        val maxScrollOffset = 200f

        // When
        val calculatedScrollOffset = checkAndGetMaxScrollOffset(scrollOffset, maxScrollOffset)

        // Then
        assertEquals(calculatedScrollOffset, 20f)
    }


    @Test
    fun `Given scroll offset more then maxScrollOffset then scroll offset should be reset to same`() {
        // Given
        val scrollOffset = 220f
        val maxScrollOffset = 200f

        // When
        val calculatedScrollOffset = checkAndGetMaxScrollOffset(scrollOffset, maxScrollOffset)

        // Then
        assertEquals(calculatedScrollOffset, maxScrollOffset)
    }
}
