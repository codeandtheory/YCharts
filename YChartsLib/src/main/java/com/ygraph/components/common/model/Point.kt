package com.ygraph.components.common.model

/**
 *
 * Point data class is used for holding the point on the graph.
 * @param x: x co-ordinate value in the graph
 * @param y: y co-ordinate value in the graph
 * @param description: Description given to describe the value of the point for accessibility service
 * Used this class over PointF as its a java class and unable to mock the same
 */
data class Point(
    val x: Float,
    val y: Float,
    val description: String = "Value of point is ${String.format("%.2f", y)}"
)

