package com.app.ygraphs

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ygraphs.presentation.BarChartActivity
import com.app.ygraphs.presentation.DonutChartActivity
import com.app.ygraphs.presentation.LineChartActivity
import com.app.ygraphs.presentation.PieChartActivity
import com.app.ygraphs.ui.theme.YGraphsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGraphsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YGraphsTheme.colors.background,
                    topBar = { AppBar() })
                {
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
                            addActivityInOutAnim()
                        })
                        ChartButton(title = getString(R.string.title_line_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    LineChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })
                        ChartButton(title = getString(R.string.title_pie_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PieChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })
                        ChartButton(title = getString(R.string.title_donut_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DonutChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })
                    }
                }
            }
        }
    }


    private fun addActivityInOutAnim() {
        overridePendingTransition(
            R.anim.move_right_in_activity,
            R.anim.move_left_out_activity
        )
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = YGraphsTheme.colors.button,
        elevation = 6.dp,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = YGraphsTheme.colors.text,
                textAlign = TextAlign.Center,
                style = YGraphsTheme.typography.header
            )
        }
    )
}

@Composable
private fun ChartButton(title: String, onClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp)
                .fillMaxWidth()
                .height(50.dp), onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = YGraphsTheme.colors.button)
        ) {
            Text(
                text = title,
                style = YGraphsTheme.typography.button,
                color = YGraphsTheme.colors.text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChartButton(title = "Chart", onClick = {})
}
