package com.ygraph.components.barchart.models

import androidx.compose.ui.graphics.Color


/**
 * Data class for individual bar.
 * @param x : x Axis point
 * @param y: y Axis point
 * @param color: Color of a bar
 * @param label: label of a bar
 */
data class BarData(val x: Float, val y: Float, val color: Color = Color.Red, val label: String)
