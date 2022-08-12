package com.example.piechartcontainer

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YGraphsTheme
import com.ygraph.components.piechart.charts.DonutPieChart
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
                            PieChartData.Slice("Very looooong label", 15f, Color(0xFF58BDFF)),
                            PieChartData.Slice("Android", 35f, Color(0xFF125B7F)),
                            PieChartData.Slice("iOS", 40f, Color(0xFF092D40)),
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
                            sliceLabelEllipsizeAt = TextUtils.TruncateAt.MIDDLE
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
