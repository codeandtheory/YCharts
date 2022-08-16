package com.app.chartcontainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.chartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.axis.AxisConfig
import com.ygraph.components.axis.Gravity
import com.ygraph.components.axis.YAxis
import com.ygraph.components.axis.YAxisData
import com.ygraph.components.graphcontainer.container.ScrollableCanvasContainer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Yellow)
                ) {
                    // Left Aligned Yaxis inside scrollable container
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        val leftAlignYAxisData = YAxisData.Builder()
                            .modifier(Modifier.height(250.dp))
                            .yMaxValue(800f)
                            .yStepValue(90f)
                            .bottomPadding(10.dp)
                            .axisPos(Gravity.LEFT)
                            .axisLabelFontSize(14.sp)
                            .yLabelData { index -> ((90f).toInt() * index).toString() }
                            .textLabelPadding(20.dp)
                            .yAxisOffset(20.dp)
                            .axisConfig(
                                AxisConfig(
                                    shouldEllipsizeLabelEnd = true,
                                    minTextWidthToEllipsize = 40.dp
                                )
                            )
                            .yLabelData { index -> "$index Thousand" }
                            .build()
                        ScrollableCanvasContainer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            calculateMaxDistance = { xZoom -> size.width * 3 },
                            drawXAndYAxis = { scrollOffset, xZoom ->
                                YAxis(yAxisData = leftAlignYAxisData)
                            },
                            onDraw = { xZoom, scrollOffset ->
                                // Draw random shapes to make it scrollable
                                val drawWidth = size.width * 4
                                drawRoundRect(
                                    color = Color.Blue,
                                    topLeft = Offset(
                                        x = 0f - scrollOffset, y = 0f
                                    ),
                                    size = Size(drawWidth, size.height - 40.dp.toPx()),
                                    cornerRadius = CornerRadius(15f, 15f),
                                    style = Fill
                                )

                                drawLine(
                                    Color.White,
                                    Offset(10f - scrollOffset, 0f),
                                    Offset(drawWidth, size.height),
                                    strokeWidth = 20f
                                )
                                drawLine(
                                    Color.Yellow,
                                    Offset(10f - scrollOffset, size.height),
                                    Offset(drawWidth, 0f),
                                    strokeWidth = 20f
                                )
                            }
                        )
                    }

                    // Right Aligned Yaxis
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        val rightAlignYAxisData = YAxisData.Builder()
                            .modifier(Modifier.height(300.dp))
                            .yMaxValue(1000f)
                            .yStepValue(100f)
                            .bottomPadding(10.dp)
                            .axisPos(Gravity.RIGHT)
                            .axisLabelFontSize(14.sp)
                            .yLabelData { index -> ((100f).toInt() * index).toString() }
                            .textLabelPadding(20.dp)
                            .yAxisOffset(20.dp)
                            .yLabelData { index -> index.toString() + "Thousand" }
                            .build()
                        YAxis(yAxisData = rightAlignYAxisData)
                    }
                }
            }
        }
    }
}
