# YGraphs

YGraphs is a light and extensible chart library for Jetpack Compose system. It comprises two main modules:

-   `YGraphlib` (Graph components for Jetpack Compose)
-   `app` (sample app to showcase graph components)

## Modules

The following table outlines the modules included in this library:

| Artifact     | Description                                                                                   |
| ------------ | --------------------------------------------------------------------------------------------- |
| `axis`       | Includes the horizontal and vertical axis components along with the math engines. |
| `graph`       | Includes the all the graph components i.e: Line, Bar etc. also the math engines  |
| `graphcontainer` | Provides the base container to draw any graph inside it with scroll tap feature etc out of the box.                                 |
| `piechart` | Includes all the 360' graph components i.e Pie, Donut charts etc.  |

## Sample app

Included in this repository is a [sample app](https://github.com/yml-org/YGraphs/tree/main/app) with multiple graphs with different styling in each section. Studying the source code of the app will give you a deep understanding of how to use YGraphs, including customizing and styling the graphs. All of the graphs i.e line, bar, groupedBar, pie & donut are implemented in the sample app.

## Basic example

Let's see how we can use the graph components and style them with available customization options.

1. **Line Graph:**
   * Create list of points with x & y co-ordinates using `Point` data class.
   ```
   val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
   ```
   * Initialize X and Y Axis builders using the `AxisData` data class.
   ```
      val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()
      
     val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).formatToSinglePrecision()
        }.build()
   ```
   * Initialize the Line graph data with axis and other line related styling using `LineGraphData` data class.
   ```
   val lineGraphData = LineGraphData(
        line = Line(
            dataPoints = pointsData,
            LineStyle(),
            IntersectionPoint(),
            SelectionHighlightPoint(),
            ShadowUnderLine(),
            SelectionHighlightPopUp()
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
   ```
   * Finally use the _**`LineGraph`**_ Component to render the line graph with the above input
     params.
   ``` 
   LineGraph(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineGraphData = lineGraphData
    )
   ```
   <figure>
   <div align = "center">
    <img width=317 src="https://user-images.githubusercontent.com/107846675/189699724-c8064338-8894-45e2-b60b-3d830123e813.png" />
    <p> Line graph looks like this!</p>
    </div>
    </figure>

2. **Bar Graph:**
   * Create list of barGraphData using the random generator extension and  `BarData` data class

     ```
     val barGraphData = DataUtils.getBarChartData(barGraphListSize, maxRange)
     ```

   * Initialize X and Y Axis builders using the `AxisData` data class.
   ```
        val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barGraphData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barData[index].label }
        .build()
      
     val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
   ```
   * Initialize the Bar graph data with axis and other line related styling using `BarGraphData`
     data class.
   ```
    val barGraphData = BarGraphData(
        graphData = barGraphData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        paddingBetweenBars = 20.dp,
        barWidth = 25.dp
    )
   ```
   * Last, use the _**`BarGraph`**_ Component to render the bar graph with the above input params.
   ``` 
   BarGraph(modifier = Modifier.height(350.dp), barGraphData = barGraphData)
   ```
   <figure>
   <div align = "center">
    <img width=347 src="https://user-images.githubusercontent.com/107846675/189836621-eeac8181-f7de-491e-8fd8-7e1276e2fb9f.png" />
    <p> Bar graph looks like this!</p>
    </div>
    </figure>

3. **Grouped Bar Graph:**
   * Create list of grouped combinations of bar graph data using the random generator extension
     and  `GroupBarGraphData` data class

     ```
     val groupBarData = DataUtils.getGroupBarChartData(barGraphListSize, maxRange, eachGroupBarSize)
     ```

   * Initialize X and Y Axis builders using the `AxisData` data class.
   ```
           val xAxisData = AxisData.Builder()
               .axisStepSize(30.dp)
               .steps(groupBarData.size - 1)
               .bottomPadding(40.dp)
               .labelData { index -> groupBarData[index].label }
               .build()
      
    val yAxisData = AxisData.Builder()
               .steps(yStepSize)
               .labelAndAxisLinePadding(20.dp)
               .axisOffset(20.dp)
               .labelData { index -> (index * (maxRange / yStepSize)).toString() }
               .build()
   ```
   * Initialize the group bar graph data with axis and other line related styling
     using `GroupBarGraphData`
     data class.
   ```
     val groupBarGraphData = GroupBarGraphData(
                        groupedBarList = groupBarData,
                        xAxisData = xAxisData,
                        yAxisData = yAxisData,
                        stackLabelConfig = StackLabelConfig(
                            stackLabelList = DataUtils.getStackLabelData(barSize)
                        )
                    )
   ```
   * Use the _**`GroupBarGraph`**_ Component to render the bar graph with the above input params.
   ``` 
   GroupBarGraph(modifier = Modifier.height(300.dp), groupBarGraphData = groupBarGraphData)
   ```
   <figure>
   <div align = "center">
    <img width=338 src="https://user-images.githubusercontent.com/107846675/189845009-6ef2ccc6-3c75-446e-9273-fe1b1e1e94d0.png" />
    <p> Grouped Bar graph looks like this!</p>
    </div>
    </figure>

4. **Pie Chart:**
   * Create list of slices using the `PieChartData` data class.

    ```
       val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("SciFi", 65f, Color(0xFF333333)),
            PieChartData.Slice("Comedy", 35f, Color(0xFF666a86)),
            PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
            PieChartData.Slice("Romance", 40f, Color(0xFFF53844))
        )
      )
     ```

   * Initialize the pie chart config with `PieChartConfig` data class inorder to achieve styling and
     configurations related to pie chart
    ```
      val pieChartConfig = PieChartConfig(
        percentVisible = true,
        isLegendVisible = true,
        legendGridSize = 4,
        isAnimationEnable = true,
        showSliceLabels = false,
        animationDuration = 1500
    )
   ```
   * Finally, use the _**`PieChart`**_ component to render the graph.
   ```  
   PieChart(modifier = Modifier
            .width(400.dp)
            .height(400.dp),
        pieChartData,
        pieChartConfig
    ) 
   ```
     <figure>
   <div align = "center">
    <img width=238 src="https://user-images.githubusercontent.com/107846675/189865767-377f3b12-1568-46c9-a248-6454ed9bb96e.png" />
    <p>  Pie chart looks like this!</p>
    </div>
    </figure>

5. **Donut Chart:**
   * Similar to pie chart here we need create list of slices using the `PieChartData` data class.

    ```
        val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
            PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
            PieChartData.Slice("Lenovo", 40f,  Color(0xFFEC9F05)),
            PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
        )
      )
     ```
   * Initialize the pie chart config with `PieChartConfig` data class inorder to achieve styling and
     configurations related to pie chart
    ```
    val donutChartConfig = PieChartConfig(
        percentVisible = true,
        percentageFontSize = 42.sp,
        strokeWidth = 120f,
        percentColor = Color.Black,
        isLegendVisible = true,
        legendGridSize = 4,
        activeSliceAlpha = .9f,
        isAnimationEnable = true
    )
   ```
   * Finally, use the _**`DonutPieChart`**_ component to render the graph.
   ```  
   DonutPieChart( modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        donutChartData,
        donutChartConfig
    ) 
   ```
     <figure>
   <div align = "center">
    <img width=233 src="https://user-images.githubusercontent.com/107846675/189868490-cbaecf87-2beb-4788-ba8e-f57b667cbf10.png" />
    <p>  Donut chart looks like this!</p>
    </div>
    </figure>    
    
6. **Combined Graph:**

   * Similar to line and bar graph we can combine both entities in one graph, just need  to initialize the line and bar plot data using the random generator extension and add styling related to individual component.

    ```
      val linePlotData = LinePlotData(
          lines = listOf(
            Line(
                DataUtils.getLineChartData(listSize, maxRange = 100),
                lineStyle = LineStyle(color = Color.Blue),
                intersectionPoint = IntersectionPoint(),
                selectionHighlightPoint = SelectionHighlightPoint(),
                selectionHighlightPopUp = SelectionHighlightPopUp()
            ),
            Line(
                DataUtils.getLineChartData(listSize, maxRange),
                lineStyle = LineStyle(color = Color.Black),
                intersectionPoint = IntersectionPoint(),
                selectionHighlightPoint = SelectionHighlightPoint(),
                selectionHighlightPopUp = SelectionHighlightPopUp()
            )
        )
    )
    
    val barPlotData = BarPlotData(
        groupBarList = DataUtils.getGroupBarChartData(listSize, maxValueRange, barSize),
        barStyle = BarStyle(barWidth = 35.dp),
        barColorPaletteList = colorPaletteList
     )
    ```

 * Initialize X and Y Axis builders using the `AxisData` data class.

     ```
    val xAxisData = AxisData.Builder()
       .axisStepSize(30.dp)
       .steps(maxOf(barGraphData.size - 1, lineGraphData.size - 1))
       .bottomPadding(40.dp)
       .labelData { index -> index.toString() }
       .build()
   val yAxisData = AxisData.Builder()
       .steps(yStepSize)
       .labelAndAxisLinePadding(20.dp)
       .axisOffset(20.dp)
       .labelData { index -> (index * (maxRange / yStepSize)).toString() }
       .build()
       
     ```
     
   * Initialize the combined graph config data with `CombinedGraphData` data class inorder to achieve styling and configurations related to same.
       ```
  val combinedGraphData = CombinedGraphData(
        combinedPlotDataList = listOf(barPlotData, linePlotData),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
     ```
   * Finally, use the _**`CombinedGraph`**_ component to render the graph.
      ```  
     CombinedGraph(
            modifier = Modifier.height(400.dp),
            combinedGraphData = combinedGraphData
        )
     ```
    _Note_ : To show legends infomartion related to bar, `Legends` component can be used.
   <figure>
  <div align = "center">
   <img width=292 src="https://user-images.githubusercontent.com/107846675/192773924-74421edd-7314-4b44-bdb1-13aaf3598796.png" />
   <p>  Combined bar with line graph looks like this!</p>
   </div>
   </figure>

## License

```
    Copyright 2022 YGraphs

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```

