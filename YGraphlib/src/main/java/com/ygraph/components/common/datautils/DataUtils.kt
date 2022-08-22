package com.ygraph.components.common.datautils

import androidx.compose.ui.graphics.Color
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.common.model.Point
import kotlin.random.Random

/**
 * @param listSize Size of the list
 * @param maxRange Maximum range for the values
return the sample bar chart data
 */
fun getBarChartData(listSize: Int, maxRange: Int): List<BarData> {
    val list = arrayListOf<BarData>()
    for (index in 0 until listSize) {
        list.add(
            BarData(
                Point(
                    index.toFloat(),
                    "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat()
                ),
                Color(
                    Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                ),
                "Bar $index"
            )
        )
    }
    return list
}
