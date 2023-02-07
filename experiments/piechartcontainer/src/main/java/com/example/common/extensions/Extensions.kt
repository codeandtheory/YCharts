package com.example.common.extensions

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.graphics.Rect
import android.text.TextPaint
import android.view.accessibility.AccessibilityManager
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

/**
return the shape that is used to mask a particular area for given leftPadding & rightPadding
 */
internal class RowClipṣ(
    private val leftPadding: Float,
    private val rightPadding: Dp,
    private val topPadding: Float = 0f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            androidx.compose.ui.geometry.Rect(
                leftPadding,
                topPadding,
                size.width - rightPadding.value * density.density,
                size.height
            )
        )
    }
}


/**
return the shape that is used to mask a particular area for given top & bottom
 */
internal class ColumnClip(
    private val leftPadding: Float,
    private val topPadding: Float = 0f,
    private val rightPadding: Float,
    private val bottomPadding: Float
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            androidx.compose.ui.geometry.Rect(
                0f,
                topPadding,
                rightPadding,
                size.height - bottomPadding
            )
        )
    }
}


/**
 * Returns true if accessibility services are enabled else false
 */
@Composable
internal fun Context.collectIsTalkbackEnabledAsState(): State<Boolean> {
    val accessibilityManager =
        this.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?

    fun isTalkbackEnabled(): Boolean {
        val accessibilityServiceInfoList =
            accessibilityManager?.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
        return accessibilityServiceInfoList?.any {
            it.resolveInfo.serviceInfo.processName.equals(TALKBACK_PACKAGE_NAME)
        } ?: false
    }

    val talkbackEnabled = remember { mutableStateOf(isTalkbackEnabled()) }
    val accessibilityManagerEnabled = accessibilityManager?.isEnabled ?: false
    var accessibilityEnabled by remember { mutableStateOf(accessibilityManagerEnabled) }

    accessibilityManager?.addAccessibilityStateChangeListener { accessibilityEnabled = it }

    LaunchedEffect(accessibilityEnabled) {
        talkbackEnabled.value = if (accessibilityEnabled) isTalkbackEnabled() else false
    }
    return talkbackEnabled
}

private const val TALKBACK_PACKAGE_NAME = "com.google.android.marvin.talkback"

fun getMaxElementInXAxis(xMax: Float, xStepSize: Int): Int {
    var reqYLabelsQuo =
        (xMax / xStepSize)
    val reqYLabelsRem = xMax.rem(xStepSize)
    if (reqYLabelsRem > 0f) {
        reqYLabelsQuo += 1
    }
    return reqYLabelsQuo.toInt() * xStepSize
}

/**
 * Return true if the point is selected
 * @param tapOffset Tapped offset
 * @param yOffset in the Y axis
 * @param left left Value
 */
fun Offset.isYAxisTapped(tapOffset: Offset, yOffset: Float, left: Float, tapPadding: Float) =
    (((tapOffset.y) < y + (yOffset + tapPadding) / 2) && ((tapOffset.y) > y - (yOffset + tapPadding) / 2) &&
            ((tapOffset.plus(Offset(tapPadding, 0f))).x > x) && ((tapOffset.x) > left))

/**
 * Returns the background rect for the highlighted text.
 * @param x : X point.
 * @param y: Y point.
 * @param text: Text to be drawn inside the background.
 * @param paint: Background paint.
 */
fun getYaxisTextBackgroundRect(
    x: Float,
    y: Float,
    text: String,
    paint: TextPaint
): Rect {
    val fontMetrics = paint.fontMetrics
    val textLength = paint.measureText(text)
    return Rect(
        (x - (textLength / 2)).toInt(),
        (y + fontMetrics.top).toInt(),
        (x + (textLength / 2)).toInt(),
        (y + fontMetrics.bottom).toInt()
    )
}
