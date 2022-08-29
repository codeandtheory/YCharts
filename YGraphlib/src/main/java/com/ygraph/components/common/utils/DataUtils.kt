package com.ygraph.components.common.utils

import androidx.compose.ui.graphics.Color
import com.ygraph.components.barchart.models.Bar
import com.ygraph.components.barchart.models.BarData
import com.ygraph.components.barchart.models.GroupBar
import com.ygraph.components.barchart.models.GroupBarChartData
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
                    (0 until maxRange).random().toFloat()
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


    /**
     * @param listSize Size of the list
     * @param maxRange Maximum range for the values
     * @param barSize size of bars in one group
    return the sample gradient bar chart data
     */
    fun getGroupBarChartData(listSize: Int, maxRange: Int, barSize: Int): List<GroupBar> {
        val list = mutableListOf<GroupBar>()
        for (index in 0 until listSize) {
            val barList = mutableListOf<Bar>()
            for (i in 0 until barSize) {
                barList.add(
                    Bar(
                        "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat(),
                        null,
                        "${index}B$i"
                    )
                )
            }
            list.add(GroupBar(index.toString(), barList))
        }
        return list
    }

    /**
     * @param listSize Size of the list
    return the sample color list
     */
    fun getColorList(listSize: Int): List<Color> {
        val list = mutableListOf<Color>()
        for (index in 0 until listSize) {
            list.add(Color(
                (0 until 256).random(), (0 until 256).random(), (0 until 256).random()
            ))
        }
        return list
    }
}
