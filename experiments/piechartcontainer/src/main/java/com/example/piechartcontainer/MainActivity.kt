package com.example.piechartcontainer

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.piechart.charts.PieChart
import com.ygraph.components.piechart.models.PieChartConfig
import com.ygraph.components.piechart.models.PieChartData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    
                    val pieChartData = PieChartData(
                        slices = listOf(
                            PieChartData.Slice("MI", 15f, Color(0xFF58BDFF)),
                            PieChartData.Slice("Iphone", 35f, Color(0xFF125B7F)),
                            PieChartData.Slice("Windows", 10f, Color(0xFF092D40)),
                            PieChartData.Slice("Pixel", 10f, Color(0xFF092D10)),
                            PieChartData.Slice("Samsung", 20f, Color(0xFF092D05)),
                            PieChartData.Slice("Oneplus", 0f, Color(0xFF092D20)),
                        )
                    )
                    
                    val pieChartConfig =
                        PieChartConfig(
                            percentVisible = true,
                            strokeWidth = 120f,
                            percentColor = Color.Black,
                            isLegendVisible = pieChartData.legendVisible,
                            legendGridSize = 3,
                            activeSliceAlpha = .9f,
                            isEllipsizeEnabled = true,
                            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE,
                            percentageTypeface = Typeface.defaultFromStyle(Typeface.ITALIC),
                            isAnimationEnable = true,
                            showSliceLabels = true
                        )
                    
                    PieChart(modifier = Modifier.padding(10.dp), pieChartData, pieChartConfig) { slice ->
                        Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YGraphsTheme {
        Greeting("Android")
    }
}
