//package co.yml.charts.ui.bargraph
//
//import androidx.compose.ui.geometry.Offset
//import co.yml.charts.axis.DataCategoryOptions
//import co.yml.charts.common.model.Point
//import co.yml.charts.ui.barchart.getDrawHorizontalOffset
//import co.yml.charts.ui.barchart.getDrawOffset
//import co.yml.charts.ui.barchart.getFullBarDetails
//import co.yml.charts.ui.barchart.getMaxScrollDistance
//import co.yml.charts.ui.barchart.models.BarData
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertTrue
//import org.junit.Test
//
//class BarChartTest {
//    @Test
//    fun `Given a container with input values valid scroll offset should be returned`() {
//        // Given
//        val columnWidth = 104f
//        val xMax = 100f
//        val xMin = 0f
//        val xOffset = 200f
//        val xLeft = 10f
//        val paddingRight = 40f
//        val canvasWidth = 500f
//
//        // When
//        val scrollDistance = getMaxScrollDistance(
//            columnWidth,
//            xMax,
//            xMin,
//            xOffset,
//            xLeft,
//            paddingRight,
//            canvasWidth
//        )
//
//        assertTrue(scrollDistance > 0)
//    }
//
//
//    @Test
//    fun `Given a total drawing width lesser than container width container should not be able to scrolled`() {
//        // Given
//        val columnWidth = 20f
//        val xMax = 20f
//        val xMin = 0f
//        val xOffset = 20f
//        val xLeft = 10f
//        val paddingRight = 40f
//        val canvasWidth = 500f
//
//        // When
//        val scrollDistance = getMaxScrollDistance(
//            columnWidth,
//            xMax,
//            xMin,
//            xOffset,
//            xLeft,
//            paddingRight,
//            canvasWidth
//        )
//
//        assertEquals(scrollDistance, 0f)
//    }
//
//    @Test
//    fun `Given a point drawOffset should be positive`() {
//        val point = Point(100f, 20f)
//
//        val drawOffset = getDrawOffset(
//            point = point,
//            xMin = 0f,
//            xOffset = 10f,
//            xLeft = 20f,
//            scrollOffset = 50f,
//            yBottom = 1500f,
//            yOffset = 20f,
//            yMin = 0f,
//            startDrawPadding = 20f,
//            barWidth = 40f,
//            zoomScale = 1f
//        )
//        assertTrue(drawOffset.x > 0 && drawOffset.y > 0)
//    }
//
//
//    @Test
//    fun `Given scroll offset,xleft,xmin are zero drawOffset should be product of xOffset and xValue`() {
//        val point = Point(1f, 20f)
//        val xOffset = 20f
//
//        val drawOffset = getDrawOffset(
//            point = point,
//            xMin = 0f,
//            xOffset = xOffset,
//            xLeft = 0f,
//            scrollOffset = 0f,
//            yBottom = 1500f,
//            yOffset = 20f,
//            yMin = 0f,
//            startDrawPadding = 20f,
//            barWidth = 40f,
//            zoomScale = 1f
//        )
//
//        assertEquals(drawOffset.x, xOffset * point.x)
//    }
//
//    @Test
//    fun `Given a point horizontal drawOffset should be positive`() {
//        val point = Point(250f, 700f)
//
//        val drawOffset = getDrawHorizontalOffset(
//            point = point,
//            xLeft = 250f,
//            scrollOffset = 50f,
//            yBottom = 950f,
//            yOffset = 192.5f,
//            yMin = 0f,
//            yMax = 9f,
//            yStart = 150f,
//            dataCategoryOptions = DataCategoryOptions(),
//            zoomScale = 1f
//        )
//
//        assertTrue(drawOffset.x > 0 && drawOffset.y > 0)
//    }
//
//    @Test
//    fun `Given scroll offset,yStart,ymin are zero drawOffset should be product of yOffset and yValue`() {
//        val point = Point(250f, 700f)
//        val yOffset = 192.5f
//
//        val drawOffset = getDrawHorizontalOffset(
//            point = point,
//            xLeft = 250f,
//            scrollOffset = 0f,
//            yBottom = 950f,
//            yOffset = 192.5f,
//            yMin = 0f,
//            yMax = 9f,
//            yStart = 0f,
//            dataCategoryOptions = DataCategoryOptions(),
//            zoomScale = 1f
//        )
//
//        assertEquals(drawOffset.y, yOffset * point.y)
//    }
//
//    @Test
//    fun `Given individual stacked bar data points return it as full bar points`() {
//        // Given
//        val totalPaddingBtwBars = 10f
//        val yOffset = 50f
//        val yBottom = 100f
//        val xPointOffSet = 100f
//        val barDetails = listOf<BarData>(
//            BarData(Point(50f, 100f)),
//            BarData(Point(50f, 200f)),
//            BarData(Point(50f, 300f))
//        )
//
//        //When
//        val fullBarDetails =
//            getFullBarDetails(barDetails, totalPaddingBtwBars, yOffset, yBottom, xPointOffSet)
//
//        //Then
//        assertEquals(fullBarDetails.first, BarData(Point(50f, 600f)))
//    }
//
//
//    @Test
//    fun `Given individual stacked bar data points return its full bar offset`() {
//        // Given
//        val totalPaddingBtwBars = 10f
//        val yOffset = 5f
//        val yBottom = 1200f
//        val xPointOffSet = 100f
//        val barDetails = listOf<BarData>(
//            BarData(Point(50f, 100f)),
//            BarData(Point(50f, 200f)),
//            BarData(Point(50f, 300f))
//        )
//
//        //When
//        val fullBarDetails =
//            getFullBarDetails(barDetails, totalPaddingBtwBars, yOffset, yBottom, xPointOffSet)
//
//        //Then
//        assertEquals(fullBarDetails.second, Offset(100f, -1810f))
//    }
//}
