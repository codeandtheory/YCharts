package com.ygraph.components.common.utils

import com.ygraph.components.common.model.Point
import kotlin.random.Random

object DataUtils {
    // T0D0 pass data through the graph component
    fun getLineChartData(listSize: Int, maxRange: Int): List<Point> {
        val list = arrayListOf<Point>()
        for (index in 0..listSize) {
            list.add(
                Point(
                    index.toFloat(),
                    Random.nextDouble(1.0, maxRange.toDouble()).toFloat()
                )
            )
        }
        return list
    }
}
