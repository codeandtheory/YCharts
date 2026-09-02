# YCharts
This is a fork the YCharts library using Advanced Cubic Bezier algorithm for prettier line charts.
The algorithm is explained in detail at [https://medium.com/mobile-app-development-publication/making-graph-plotting-function-in-jetpack-compose-95c80ee6fc7f](https://medium.com/mobile-app-development-publication/making-graph-plotting-function-in-jetpack-compose-95c80ee6fc7f)
    <div>
    <figure>
      <img width=100 src="https://github.com/codeandtheory/YCharts/assets/6216877/f9bf3760-ca16-4644-8abc-b1326d28b8d3"/>
    </figure>
    </div>
    Advanced Cubic Bezier
    <div>
    <figure>
      <img width=100 src="https://github.com/codeandtheory/YCharts/assets/6216877/12a086eb-3fe0-43e2-b9ed-2e1dafbab8ad"/>
    </figure>
    </div>
    Basic Cubic Bezier

YCharts is a Jetpack-compose based  charts library which enables developers to easily integrate various types of charts/graphs into their existing ui to visually represent statistical data. YCharts supports both cartesian(XY-charts)  and polar charts(Radial charts), which include:

A. Cartesian charts:
  1. Line chart 
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/58352fc4-8039-46e6-9248-1f338efc705d"/>
    </figure>
    </div>

  2. Bar chart
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/5af22ba1-232f-4d1a-8067-c08f56a61b13"/>
    </figure>
    </div>

  3. Wave chart
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/be694054-d5cc-49ec-b7ee-be80c1562df7"/>
    </figure>
    </div>

  4. Bubble chart
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/cb177d4d-3fa8-4171-b3bd-7064458b1964"/>
    </figure>
    </div>
  5. Combined chart
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/809bff58-a857-4ed1-8415-cd1d97be300f"/>
    </figure>
    </div>

B.Radial charts:
  1. Pie chart
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/ac6eb6c7-033d-439b-b416-d6083ba0ee83"/>
    </figure>
    </div>
  2. Donut chart
    <div>
    <figure>
      <img width=100 src="https://github.com/AkYML/YCharts/assets/102035914/3296d983-4a9a-4b91-8b46-49f84a80041c"/>
    </figure>
    </div>




 It comprises two main modules:

- `YChartslib` (Chart components for Jetpack Compose)
- `app` (sample app to showcase chart components)

## Adding YCharts to your project:

You can add the library via Maven:

Gradle:

```groovy
implementation 'co.yml:ycharts:2.1.0'
```

## Modules

The following table outlines the modules included in this library:

| Artifact         | Description                                                                                            |
| ---------------- | ------------------------------------------------------------------------------------------------------ |
| `axis`           | Includes the horizontal and vertical axis components along with the math engines.                      |
| `charts`         | Includes the all the chart components i.e: Line, Bar, Combined, Pie & Donut etc. also the math engines |
| `chartcontainer` | Provides the base container to draw any chart inside it with scroll tap feature etc out of the box.    |
| `piechart`       | Includes all the 360' chart components i.e Pie, Donut charts etc.                                      |

## Sample app

Included in this repository is a [sample app](https://github.com/yml-org/YCharts/tree/main/app) with multiple charts with different styling in each section. Studying the source code of the app will give you a deep understanding of how to use YCharts, including customizing and styling the charts. All of the charts i.e line, bar, groupedBar, pie & donut are implemented in the sample app.

## Examples

Let's see how we can use the chart components and style them with available customization options.

### 1. Line Chart:

- Create list of points with x & y co-ordinates using `Point` data class.

  ```kotlin
  val pointsData: List<Point> =
      listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
  ```

- Initialize X and Y Axis builders using the `AxisData` data class.

  ```kotlin
  val xAxisData = AxisData.Builder()
    .axisStepSize(100.dp)
    .backgroundColor(Color.Blue)
    .steps(pointsData.size - 1)
    .labelData { i -> i.toString() }
    .labelAndAxisLinePadding(15.dp)
    .build()

  val yAxisData = AxisData.Builder()
    .steps(steps)
    .backgroundColor(Color.Red)
    .labelAndAxisLinePadding(20.dp)
    .labelData { i ->
        val yScale = 100 / steps
        (i * yScale).formatToSinglePrecision()
    }.build()
  ```

- Initialize the Line chart data with axis and other line related styling using `LineChartData` data class.

  ```kotlin
  val lineChartData = LineChartData(
    linePlotData = LinePlotData(
      lines = listOf(
        Line(
          dataPoints = pointsData,
          LineStyle(),
          IntersectionPoint(),
          SelectionHighlightPoint(),
          ShadowUnderLine(),
          SelectionHighlightPopUp()
        )
      ),
    ),
    xAxisData = xAxisData,
    yAxisData = yAxisData,
    gridLines = GridLines(),
    backgroundColor = Color.White
  )
  ```

- Finally use the _**`LineChart`**_ Component to render the line chart with the above input params.

  ```kotlin
  LineChart(
    modifier = Modifier
      .fillMaxWidth()
      .height(300.dp),
    lineChartData = lineChartData
  )
  ```

  <figure>
    <div align = "center">
      <img width=317 src="https://github.com/AkYML/YCharts/assets/102035914/58352fc4-8039-46e6-9248-1f338efc705d"/>
      <p> Line chart looks like this! </p>

  <img width=317 src="https://github.com/AkYML/YCharts/assets/102035914/a5d5c153-c2df-43d7-8d05-41548ed64e5c"/>
      <p> Line chart with dots looks like this! </p>

    </div>
  </figure>




### 2. Bar Chart:

- Create list of barChartData using the random generator extension and `BarData` data class.

  ```kotlin
  val barChartData = DataUtils.getBarChartData(barChartListSize, maxRange)
  ```

- Initialize X and Y Axis builders using the `AxisData` data class.

  ```kotlin
  val xAxisData = AxisData.Builder()
  .axisStepSize(30.dp)
  .steps(barChartData.size - 1)
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

- Initialize the Bar chart data with axis and other line related styling using `BarChartData` data class.

  ```kotlin
  val barChartData = BarChartData(
    chartData = barChartData,
    xAxisData = xAxisData,
    yAxisData = yAxisData,
    paddingBetweenBars = 20.dp,
    barWidth = 25.dp
  )
  ```

- Last, use the _**`BarChart`**_ Component to render the bar chart with the above input params.

  ```kotlin
  BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
  ```

  <figure>
    <div align = "center">
      <img width=347 src="https://github.com/AkYML/YCharts/assets/102035914/0d2a6cfd-98c4-46ee-92a7-b88814f8d186" />
      <p> Bar chart looks like this!</p>
      <img width=347 src="https://github.com/AkYML/YCharts/assets/102035914/6dec92d7-95ed-4892-89b7-44a03d0711b9" />
      <p> Horizontal Bar chart looks like this!</p>
  
    </div>
  </figure>


### 3. Grouped Bar Chart:

- Create list of grouped combinations of bar chart data using the random generator extension and `BarPlotData` data class.

  ```kotlin
  val groupBarPlotData = BarPlotData(
    groupBarList = DataUtils.getGroupBarChartData(
      barChartListSize,
      maxRange,
      eachGroupBarSize
    ),
    barColorPaletteList = getColorPaletteList(barSize)
  )
  ```

- Initialize X and Y Axis builders using the `AxisData` data class.

  ```kotlin
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

- Initialize the group bar chart data with axis and other line related styling using `GroupBarChartData` data class.

  ```kotlin
  val groupBarChartData = GroupBarChartData(
    barPlotData = groupBarPlotData,
    xAxisData = xAxisData,
    yAxisData = yAxisData
  )
  ```

- Use the _**`GroupBarChart`**_ Component to render the bar chart with the above input params.

  ```kotlin
  GroupBarChart(modifier = Modifier.height(300.dp), groupBarChartData = groupBarChartData)
  ```

  <figure>
    <div align = "center">
      <img width=338 src="https://github.com/AkYML/YCharts/assets/102035914/55df945d-bcd9-4b08-b47e-5d439a7abf39" />
      <p> Grouped Bar chart looks like this!</p>
      <img width=338 src="https://github.com/AkYML/YCharts/assets/102035914/00af9045-4054-4544-b4c5-93b568df2b85" />
      <p> Stacked Bar chart looks like this!</p>
    </div>
  </figure>

### 4. Pie Chart:

- Create list of slices using the `PieChartData` data class.

  ```kotlin
  val pieChartData = PieChartData(
    slices = listOf(
      PieChartData.Slice("SciFi", 65f, Color(0xFF333333)),
      PieChartData.Slice("Comedy", 35f, Color(0xFF666a86)),
      PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
      PieChartData.Slice("Romance", 40f, Color(0xFFF53844))
    )
  )
  ```

- Initialize the pie chart config with `PieChartConfig` data class in order to achieve styling and configurations related to pie chart.

  ```kotlin
  val pieChartConfig = PieChartConfig(
    percentVisible = true,
    isAnimationEnable = true,
    showSliceLabels = false,
    animationDuration = 1500
  )
  ```

- Finally, use the _**`PieChart`**_ component to render the chart.

  ```kotlin
  PieChart(
    modifier = Modifier
      .width(400.dp)
      .height(400.dp),
    pieChartData,
    pieChartConfig
  )
  ```

  <figure>
    <div align = "center">
      <img width=238 src="https://github.com/AkYML/YCharts/assets/102035914/457b6034-eef0-4f70-bd40-0fc0e950298a" />
      <p>  Pie chart looks like this!</p>

      <img width=238 src="https://github.com/AkYML/YCharts/assets/102035914/ac6eb6c7-033d-439b-b416-d6083ba0ee83" />
      <p>  Pie chart with lables like this!</p>

    </div>
  </figure>






### 5. Donut Chart:

- Similar to pie chart here we need create list of slices using the `PieChartData` data class.

  ```kotlin
  val donutChartData = PieChartData(
    slices = listOf(
      PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
      PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
      PieChartData.Slice("Lenovo", 40f,  Color(0xFFEC9F05)),
      PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
    )
  )
  ```

- Initialize the pie chart config with `PieChartConfig` data class in order to achieve styling and configurations related to pie chart.

  ```kotlin
  val donutChartConfig = PieChartConfig(
    percentVisible = true,
    percentageFontSize = 42.sp,
    strokeWidth = 120f,
    percentColor = Color.Black,
    activeSliceAlpha = .9f,
    isAnimationEnable = true
  )
  ```

- Finally, use the _**`DonutPieChart`**_ component to render the chart.

  ```kotlin
  DonutPieChart(
    modifier = Modifier
      .fillMaxWidth()
      .height(500.dp),
    donutChartData,
    donutChartConfig
  )
  ```

  <figure>
    <div align = "center">
      <img width=233 src="https://github.com/AkYML/YCharts/assets/102035914/3296d983-4a9a-4b91-8b46-49f84a80041c" />
      <p>  Donut chart looks like this!</p>
    </div>
  </figure>




### 6. Combined Chart:

- Similar to line and bar chart we can combine both entities in one chart, just need to initialize the line and bar plot data using the random generator extension and add styling related to individual component.

  ```kotlin
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

- Initialize X and Y Axis builders using the `AxisData` data class.

  ```kotlin
  val xAxisData = AxisData.Builder()
    .axisStepSize(30.dp)
    .steps(maxOf(barChartData.size - 1, lineChartData.size - 1))
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

- Initialize the combined chart config data with `CombinedChartData` data class in order to achieve styling and configurations related to same.

  ```kotlin
  val combinedChartData = CombinedChartData(
    combinedPlotDataList = listOf(barPlotData, linePlotData),
    xAxisData = xAxisData,
    yAxisData = yAxisData
  )
  ```

- Finally, use the _**`CombinedChart`**_ component to render the chart.
  ```kotlin
  CombinedChart(
    modifier = Modifier.height(400.dp),
    combinedCharthData = combinedChartData
  )
  ```
  _Note_ : To show legends infomartion related to bar, `Legends` component can be used.
  <figure>
    <div align = "center">
      <img width=292 src="https://github.com/AkYML/YCharts/assets/102035914/809bff58-a857-4ed1-8415-cd1d97be300f" />
      <p>  Combined bar with line chart looks like this!</p>
    </div>
  </figure>
  <br>



### 7. Wave Chart:

- Wave charts, also referred to as waveform charts or waveform displays, are specialized visualizations used primarily in the field of signal processing and electronics. These charts illustrate the amplitude (vertical axis) and time (horizontal axis) of a waveform.

  ```kotlin
  val waveChartData = WaveChartData(
        wavePlotData = WavePlotData(
            lines = listOf(
                Wave(
                    dataPoints = pointsData,
                    waveStyle = LineStyle(color = Color.Black),
                    selectionHighlightPoint = SelectionHighlightPoint(),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp(),
                    waveFillColor = WaveFillColor(topColor = Color.Green, bottomColor = Color.Red),
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
  ```

- Initialize X and Y Axis builders using the `AxisData` data class.

  ```kotlin
  val xAxisData = AxisData.Builder()
    .axisStepSize(30.dp)
    .steps(maxOf(barChartData.size - 1, lineChartData.size - 1))
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

- Initialize the Wave chart config data with `WaveChartData` data class in order to achieve styling and configurations related to same.

  ```kotlin
  val waveChartData = WaveChartData(
        wavePlotData = WavePlotData(
            lines = listOf(
                Wave(
                    dataPoints = pointsData,
                    waveStyle = LineStyle(color = Color.Black),
                    selectionHighlightPoint = SelectionHighlightPoint(),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp(),
                    waveFillColor = WaveFillColor(topColor = Color.Green, bottomColor = Color.Red),
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
  ```

- Finally, use the _**`WaveChart`**_ component to render the chart.
  ```kotlin
  WaveChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        waveChartData = data
    )
  ```
  _Note_ : To show legends infomartion related to bar, `Legends` component can be used.
  <figure>
    <div align = "center">
      <img width=292 src="https://github.com/AkYML/YCharts/assets/102035914/be694054-d5cc-49ec-b7ee-be80c1562df7" />
      <p>  Wavechart looks like this!</p>
      <img width=292 src="https://github.com/AkYML/YCharts/assets/102035914/2d8b02af-1252-44fb-83e1-06d1f6230733" />
      <p>  Sinewavechart looks like this!</p>
    </div>
  </figure>
  <br>

### 8. Bubble Chart:

- Bubble charts are a variation of the scatter plot, where the data points are represented as bubbles or circles instead of simple dots. The bubbles' size represents the value of a third variable, in addition to the x-axis and y-axis variables. Bubble charts are effective in visualizing and comparing three different dimensions of data simultaneously.
  
  ```kotlin
    val bubbleChartData = BubbleChartData(
        DataUtils.getBubbleChartDataWithGradientStyle(pointsData),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
  ```

- Initialize X and Y Axis builders using the `AxisData` data class.

  ```kotlin
  val xAxisData = AxisData.Builder()
    .axisStepSize(30.dp)
    .steps(maxOf(barChartData.size - 1, lineChartData.size - 1))
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

- Initialize the Bubble chart config data with `BubbleChartData` data class in order to achieve styling and configurations related to same.

  ```kotlin
    val bubbleChartData = BubbleChartData(
        DataUtils.getBubbleChartDataWithGradientStyle(pointsData),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )
    
  ```

- Finally, use the _**`BubbleChart`**_ component to render the chart.
  ```kotlin
    BubbleChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        bubbleChartData = data
    )
  ```
  _Note_ : To show legends infomartion related to bar, `Legends` component can be used.
  <figure>
    <div align = "center">
      <img width=292 src="https://github.com/AkYML/YCharts/assets/102035914/be103f93-8562-4939-add8-1693d76441ef" />
        <p>  Gradient-Bubble chart looks like this!</p>
      <img width=292 src="https://github.com/AkYML/YCharts/assets/102035914/cb177d4d-3fa8-4171-b3bd-7064458b1964" />
        <p>  Bubble chart looks like this!</p>
    </div>
  </figure>
  <br>


## Accessibility Support

To interact with your device with touch and spoken feedback, you can turn on the TalkBack screen reader. TalkBack describes the graphs/charts when tapped on the graph container.
Compose views by deafult supports accessibility services, but for views drawn using canvas has no straight forward approach as of now, hence all our graph components supports an accessibility popup out of the box that will be shown over the graph with tapped on the container, with all the values described in an order to support accessibility services. User will be able to scroll the popup and find all the points, bars, slices or combined values in a descriptive manner, where user can tap and talkback would read it out loud.

<figure>
  <div align = "center">
    <span align = "center" style  = "display:inline-block">
      <img width=250 src="https://user-images.githubusercontent.com/107846675/195833801-24e17e52-d3cd-45e1-9d3e-1d9bab4b0e22.png" />
      <p> fig (a)</p>
    </span>
    <span align = "center"  style = "display:inline-block; padding-left:30px">
      <img width= 250 src="https://user-images.githubusercontent.com/107846675/195833551-28f21bdb-a035-4858-832c-a9a0c4dc9927.png" />
      <p> fig (b)</p>
    </span>
  </div>
</figure>

Here fig(a) represents the line graph with the container being highlighted & fig(b) represents the accessibility sheet with all values laid out in detail so that talkback can describe the graph values.<br><br>
_Note_: All the descriptions that are visible in the accessibility popup can be customized to the required string.

## KMM Support.

- For the ongoing effort to provide multiplatform solution, Ycharts now being written in Compose Multiplatform, adopting KMM has been a game-changer, we hope to achieve good results in this effort with community contribution.
  Currently KMM compatible code can be found at _**`kmm-main`**_ branch

## Changes in version 2.0

- Fix for multiple line graph/chart has been added, now it can be used to plot multiple line graphs/line charts on a single canvas or a single line with multiple colors.
- Background color for Pie chart/Donut chart now can be set via _**`backgroundColor`**_ parameter through PieChartConfig model, this is done to keep the behaviour consistent with current syntax pattern.
- Kotlin-library updates: _**`Kotlin version`**_ has been upgraded to '1.8.10' along with agp, compose-compiler, core-ktx, target sdk, and other compatible library updates.
- Sample App has been updated to showcase more use-cases for each chart type.

## License

```
    Copyright 2022 YCharts

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
