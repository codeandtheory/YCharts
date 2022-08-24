package com.example.piechartcontainer

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
                    modifier = Modifier,
                    color = MaterialTheme.colors.background
                ) {

                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Charts")
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(), onClick = {
                            startActivity(
                                Intent(
                                this@MainActivity,
                                BarChartActivity::class.java
                            )
                            )
                        }) {
                            Text(text = "Bar Chart")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(), onClick = {
                            startActivity(
                                Intent(
                                this@MainActivity,
                                PieChartActivity::class.java
                            )
                            )
                        }) {
                            Text(text = "Pie Chart")
                        }
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
