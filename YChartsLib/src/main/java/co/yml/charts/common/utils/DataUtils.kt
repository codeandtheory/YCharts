package co.yml.charts.common.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import co.yml.charts.charts.barchart.models.BarData
import co.yml.charts.charts.barchart.models.GroupBar
import co.yml.charts.charts.piechart.models.PieChartData
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import kotlin.random.Random

object DataUtils {
    // T0D0 pass data through the graph component
    fun getLineChartData(listSize: Int, start: Int = 0, maxRange: Int): List<Point> {
        val list = arrayListOf<Point>()
        for (index in 0 until listSize) {
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

    fun getPieChartData(): PieChartData {
        return PieChartData(
            slices = listOf(
                PieChartData.Slice("SciFi", 15f, Color(0xFF333333)),
                PieChartData.Slice("Comedy", 15f, Color(0xFF666a86)),
                PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
                PieChartData.Slice("Romance", 10f, Color(0xFFE8DDB5)),
                PieChartData.Slice("Action", 20f, Color(0xFFEDAFB8)),
                PieChartData.Slice("Thriller", 100f, Color(0xFFF94892)),
                PieChartData.Slice("Western", 10f, Color(0xFFA675A1)),
                PieChartData.Slice("Fantasy", 10f, Color(0xFF8F3985)),
            ),
            plotType = PlotType.Pie
        )
    }

    fun getPieChartData2(): PieChartData {
        return PieChartData(
            slices = listOf(
                PieChartData.Slice("Android", 30f, Color(0xFF002B5B)),
                PieChartData.Slice("iOS", 30f, Color(0xFF2B4865)),
                PieChartData.Slice("Windows", 15f, Color(0xFF256D85)),
                PieChartData.Slice("Other", 25f, Color(0xFF806D85)),
            ),
            plotType = PlotType.Pie
        )
    }

    fun getDonutChartData(): PieChartData {
        return PieChartData(
            slices = listOf(
                PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
                PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
                PieChartData.Slice("Lenovo", 10f, Color(0xFFA40606)),
                PieChartData.Slice("Asus", 15f, Color(0xFFF53844)),
                PieChartData.Slice("Acer", 10f, Color(0xFFEC9F05)),
                PieChartData.Slice("Apple", 30f, Color(0xFF009FFD)),
            ),
            plotType = PlotType.Donut
        )
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
            val barList = mutableListOf<BarData>()
            for (i in 0 until barSize) {
                val barValue = "%.2f".format(Random.nextDouble(1.0, maxRange.toDouble())).toFloat()
                barList.add(
                    BarData(
                        Point(
                            i.toFloat(),
                            barValue
                        ),
                        label = "B$i",
                        description = "Bar at $i with label B$i has value ${
                            String.format(
                                "%.2f", barValue
                            )
                        }"
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
            list.add(
                Color(
                    (0 until 256).random(), (0 until 256).random(), (0 until 256).random()
                )
            )
        }
        return list
    }

    /**
     * @param colorPaletteList color list for each legend
    return the sample stackLabelList data
     */
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

    fun getLegendsConfigFromPieChartData(pieChartData: PieChartData, gridSize: Int): LegendsConfig {
        val legendsList = mutableListOf<LegendLabel>()
        pieChartData.slices.forEach { slice ->
            legendsList.add(LegendLabel(slice.color, slice.label))
        }
        return LegendsConfig(
            legendLabelList = legendsList,
            gridColumnCount = gridSize,
            legendsArrangement = Arrangement.Start,
            textStyle = TextStyle()
        )
    }
}
