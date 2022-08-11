package com.ygraph.components.barchart.helper

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.ygraph.components.barchart.model.Bar
import com.ygraph.components.barchart.model.GroupBar

// class will be used to draw bar
class BarDrawer {

    // to draw simple bar
    fun DrawScope.drawBar(
        canvas: Canvas,
        drawableArea: Rect,
        bar: Bar
    ) {
        val barPaint = Paint().apply { color = bar.color }
        canvas.drawRect(drawableArea, barPaint)
    }

    // to draw group bar
    fun DrawScope.drawGroupBar(
        canvas: Canvas,
        groupBar: GroupBar
    ) {
        for (bar in groupBar.data) {
            val barPaint = Paint().apply { color = bar.color }
            canvas.drawRect(bar.drawableArea, barPaint)
        }
    }
}