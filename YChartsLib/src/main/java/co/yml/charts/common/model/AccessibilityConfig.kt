package co.yml.charts.common.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.utils.ChartConstants

/**
 * AccessibilityConfig is a data class to configure all params needed for accessibility service.
 * @param shouldHandleBackWhenTalkBackPopUpShown: True by default to dismiss the accessibility dialog when back pressed else false.
 * @param chartDescription: Description used by accessibility service when tapped on the chart.
 * @param popUpTopRightButtonTitle: Title of the button visible on the accessibility popUp at the top right.
 * @param popUpTopRightButtonDescription: Content description of the button visible on the accessibility popUp at the top right.
 * @param dividerColor: Defines the color for the divider used in the accessibility popUp
 * @param dividerThickness: Defines the thickness for the divider in Dp used in the accessibility popUp
 */
data class AccessibilityConfig(
    val chartDescription: String = ChartConstants.CHART_DESCRIPTION,
    val descriptionTextSize: TextUnit = 14.sp,
    val shouldHandleBackWhenTalkBackPopUpShown: Boolean = true,
    val popUpTopRightButtonTitle: String = ChartConstants.POPUP_TOP_RIGHT_BUTTON_TITLE,
    val popUpTopRightButtonDescription: String = ChartConstants.POPUP_TOP_RIGHT_BUTTON_DESCRIPTION,
    val dividerColor: Color = Color.Gray,
    val dividerThickness: Dp = 2.dp
)

