package co.yml.ycharts.app

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
import co.yml.ycharts.app.presentation.*
import co.yml.ycharts.app.ui.theme.YChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    backgroundColor = YChartsTheme.colors.background,
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

                        ChartButton(title = getString(R.string.title_wave_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    WaveChartActivity::class.java
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
                        ChartButton(
                            title = getString(R.string.title_bar_with_line_chart),
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        CombinedLineAndBarChartActivity::class.java
                                    )
                                )
                                addActivityInOutAnim()
                            })


                        ChartButton(
                            title = getString(R.string.bubble_chart),
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        BubbleChartActivity::class.java
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
        backgroundColor = YChartsTheme.colors.button,
        elevation = 6.dp,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = YChartsTheme.colors.text,
                textAlign = TextAlign.Center,
                style = YChartsTheme.typography.header
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
            colors = ButtonDefaults.buttonColors(backgroundColor = YChartsTheme.colors.button)
        ) {
            Text(
                text = title,
                style = YChartsTheme.typography.button,
                color = YChartsTheme.colors.text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChartButton(title = "Chart", onClick = {})
}
