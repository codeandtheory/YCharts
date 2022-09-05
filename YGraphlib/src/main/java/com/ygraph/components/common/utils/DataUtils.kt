package com.ygraph.components.common.utils

import androidx.compose.ui.graphics.Color
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.common.model.Point
import kotlin.random.Random

object DataUtils {
    // T0D0 pass data through the graph component
    fun getLineChartData(listSize: Int, start: Int = 0, maxRange: Int): List<Point> {
        val list = arrayListOf<Point>()
        for (index in 0..listSize) {
            list.add(
                Point(
                    index.toFloat(),
                    (start until maxRange).random().toFloat()
                )
            )
        }
        return list
    }

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
                    "Bar$index"
                )
            )
        }
        return list
    }


    /**
     * @param listSize Size of the list
     * @param maxRange Maximum range for the values
    return the sample gradient bar chart data
     */
    fun getGradientBarChartData(listSize: Int, maxRange: Int): List<BarData> {
        val list = arrayListOf<BarData>()
        for (index in 0 until listSize) {
            list.add(
                BarData(
                    point = Point(
                        index.toFloat(),
                        "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat()
                    ),
                    gradientColorList = listOf(
                        Color(
                            Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                        ),
                        Color(
                            Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                        ),
                        Color(
                            Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                        ),
                        Color(
                            Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                        )
                    ),
                    label = "Bar$index"
                )
            )
        }
        return list
    }
    

}
