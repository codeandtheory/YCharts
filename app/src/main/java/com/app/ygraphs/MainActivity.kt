package com.app.ygraphs

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ygraphs.presentation.BarChartActivity
import com.app.ygraphs.presentation.DonutChartActivity
import com.app.ygraphs.presentation.LineChartActivity
import com.app.ygraphs.presentation.PieChartActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    AppBar()
                }) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ChartButton(title = getString(R.string.title_bar_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    BarChartActivity::class.java
                                )
                            )
                        })
                        ChartButton(title = getString(R.string.title_line_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    LineChartActivity::class.java
                                )
                            )
                        })
                        ChartButton(title = getString(R.string.title_pie_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PieChartActivity::class.java
                                )
                            )
                        })
                        ChartButton(title = getString(R.string.title_donut_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DonutChartActivity::class.java
                                )
                            )
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.Black,
        elevation = 6.dp,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        }
    )
}

@Composable
private fun ChartButton(title: String, onClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp)
                .fillMaxWidth()
                .height(50.dp), onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text(text = title, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChartButton(title = "Chart", onClick = {})
}
