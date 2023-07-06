package co.yml.kmm.charts.common.utils

import androidx.compose.ui.graphics.Color
import co.yml.kmm.charts.common.extensions.formatToSinglePrecision
import co.yml.kmm.charts.common.model.LegendLabel
import co.yml.kmm.charts.common.model.Point
import co.yml.kmm.charts.ui.barchart.models.BarData
import co.yml.kmm.charts.ui.barchart.models.GroupBar
import kotlin.random.Random

object DataUtils {
    fun getGroupBarChartData(listSize: Int, maxRange: Int, barSize: Int): List<GroupBar> {
        val list = mutableListOf<GroupBar>()
        for (index in 0 until listSize) {
            val barList = mutableListOf<BarData>()
            for (i in 0 until barSize) {
                val barValue = Random.nextDouble(1.0, maxRange.toDouble()).toFloat()
                barList.add(
                    BarData(
                        Point(
                            index.toFloat(),
                            barValue
                        ),
                        label = "B$i",
                        description = "Bar at $index with label B$i has value ${
                            barValue.formatToSinglePrecision()
                        }"
                    )
                )
            }
            list.add(GroupBar(index.toString(), barList))
        }
        return list
    }

    fun getColorPaletteList(listSize: Int): List<Color> {
        val colorList = mutableListOf<Color>()

        for (index in 0 until listSize) {
            colorList.add(
                Color(
                    (0 until 256).random(), (0 until 256).random(), (0 until 256).random()
                )
            )
        }
        return colorList
    }

    fun getLegendsLabelData(colorPaletteList: List<Color>): List<LegendLabel> {
        val legendLabelList = mutableListOf<LegendLabel>()
        for (index in colorPaletteList.indices) {
            legendLabelList.add(
                LegendLabel(
                    colorPaletteList[index],
                    "B$index"
                )
            )
        }
        return legendLabelList
    }
}