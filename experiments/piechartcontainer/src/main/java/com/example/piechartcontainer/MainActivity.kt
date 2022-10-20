package com.example.piechartcontainer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piechartcontainer.ui.theme.YChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
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
    YChartsTheme {
        Greeting("Android")
    }
}
