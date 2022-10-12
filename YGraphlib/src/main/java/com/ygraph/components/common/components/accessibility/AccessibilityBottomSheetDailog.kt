package com.ygraph.components.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AccessibilityBottomSheetDialog(
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    sheetState: ModalBottomSheetState
) {
    val composeScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            Box(
                modifier = modifier
                    .background(backgroundColor)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = {
                            composeScope.launch { sheetState.hide() }
                        }) {
                            Text(
                                text = "Close",
                                modifier = Modifier.semantics {
                                    contentDescription = "Tap to close the dialog"
                                })
                        }
                    }
                    content()
                }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.White,
        sheetElevation = 0.dp
    ) {}
}
