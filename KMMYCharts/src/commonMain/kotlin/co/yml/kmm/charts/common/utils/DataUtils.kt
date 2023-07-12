package co.yml.kmm.charts.common.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import co.yml.kmm.charts.common.extensions.formatToSinglePrecision
import co.yml.kmm.charts.common.model.LegendLabel
import co.yml.kmm.charts.common.model.Point
import co.yml.kmm.charts.ui.barchart.models.BarData
import co.yml.kmm.charts.ui.barchart.models.GroupBar
import co.yml.kmm.charts.ui.bubblechart.model.Bubble
import co.yml.kmm.charts.ui.bubblechart.model.BubbleGradientType
import co.yml.kmm.charts.ui.bubblechart.model.BubbleStyle
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



    /**
     * Returns list of points
     * @param listSize: Size of total number of points needed.
     * @param start: X values to start from. ex: 50 to 100
     * @param maxRange: Max range of Y values
     */
    fun getBubbleChartDataWithGradientStyle(
        points: List<Point>,
        minDensity: Float = 10F,
        maxDensity: Float = 100F
    ): List<Bubble> {
        val list = arrayListOf<Bubble>()
        points.forEachIndexed { index, point ->
            val bubbleColor1 = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            val bubbleColor2 = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            val bubbleColor3 = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            val bubbleColor4 = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

            when(Random.nextInt(0,5)){
                0->{
                    list.add(
                        Bubble(
                            center = point,
                            density = (minDensity.toInt() until maxDensity.toInt()).random().toFloat(),
                            bubbleStyle = BubbleStyle(gradientColors = listOf(bubbleColor1, bubbleColor2,bubbleColor3,bubbleColor4), useGradience = true, gradientType = BubbleGradientType.RadialGradient)
                        )
                    )
                }
                1->{
                    list.add(
                        Bubble(
                            center = point,
                            density = (minDensity.toInt() until maxDensity.toInt()).random().toFloat(),
                            bubbleStyle = BubbleStyle(gradientColors = listOf(bubbleColor1, bubbleColor2), useGradience = true, gradientType = BubbleGradientType.LinearGradient)
                        )
                    )
                }
                2->{
                    list.add(
                        Bubble(
                            center = point,
                            density = (minDensity.toInt() until maxDensity.toInt()).random().toFloat(),
                            bubbleStyle = BubbleStyle(gradientColors = listOf(bubbleColor1, bubbleColor2), useGradience = true, gradientType = BubbleGradientType.VerticalGradient)
                        )
                    )
                }
                3->{
                    list.add(
                        Bubble(
                            center = point,
                            density = (minDensity.toInt() until maxDensity.toInt()).random().toFloat(),
                            bubbleStyle = BubbleStyle(gradientColors = listOf(bubbleColor1, bubbleColor2), useGradience = true, gradientType = BubbleGradientType.HorizontalGradient)
                        )
                    )
                }
                4->{
                    list.add(
                        Bubble(
                            center = point,
                            density = (minDensity.toInt() until maxDensity.toInt()).random().toFloat(),
                            bubbleStyle = BubbleStyle(gradientColors = listOf(bubbleColor1, bubbleColor2,bubbleColor3,bubbleColor4), useGradience = true, gradientType = BubbleGradientType.HorizontalGradient,tileMode = TileMode.Repeated)
                        )
                    )
                }
                5->{
                    list.add(
                        Bubble(
                            center = point,
                            density = (minDensity.toInt() until maxDensity.toInt()).random().toFloat(),
                            bubbleStyle = BubbleStyle(gradientColors = listOf(bubbleColor1, bubbleColor2,bubbleColor3,bubbleColor4), useGradience = true, gradientType = BubbleGradientType.HorizontalGradient,tileMode = TileMode.Mirror)
                        )
                    )
                }
            }


        }
        return list
    }

    /**
     * Returns list of points
     * @param listSize: Size of total number of points needed.
     * @param start: X values to start from. ex: 50 to 100
     * @param maxRange: Max range of Y values
     */
    fun getRandomPoints(listSize: Int, start: Int = 0, maxRange: Int): List<Point> {
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

}


