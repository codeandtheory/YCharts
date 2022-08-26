package com.app.ygraphs.ui.compositions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.ygraphs.R
import com.app.ygraphs.ui.theme.YGraphsTheme

@Composable
fun AppBarWithBackButton(title: String, onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = YGraphsTheme.colors.button,
        elevation = 6.dp,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = "Back",
                            tint = Color.Unspecified
                        )
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = YGraphsTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = YGraphsTheme.typography.header
                )
            }
        }
    )
}
