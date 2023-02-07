package com.example.common.util

import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import com.example.barchart.model.BarChartType
import kotlin.random.Random

object DataUtils {

    /**
     * Return the sample bar chart data
     * @param listSize Size of the list
     * @param maxRange Maximum range for the values
     */
    fun getBarChartData(listSize: Int, maxRange: Int, barChartType: BarChartType): List<BarData> {
        val list = arrayListOf<BarData>()
        for (index in 0 until listSize) {
            val point = when (barChartType) {
                BarChartType.VERTICAL -> {
                    Point(
                        index.toFloat(),
//                        "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat()
                        (index + 1) * 1f
                    )
                }
                BarChartType.HORIZONTAL -> {
                    Point(
//                        "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat(),
                        (index + 1) * 1f,
                        index.toFloat()
                    )
                }
            }

            list.add(
                BarData(
                    point,
                    Color(
                        Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                    ),
                    "Bar$index",
                )
            )
        }
        return list
    }
}