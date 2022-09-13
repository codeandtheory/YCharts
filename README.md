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
   * Finally use the Line Graph Component to render the line graph with the above input params.
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
   * Last, use the Bar Graph Component to render the bar graph with the above input params.
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
   * Use the Group Bar Graph Component to render the bar graph with the above input params.
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
