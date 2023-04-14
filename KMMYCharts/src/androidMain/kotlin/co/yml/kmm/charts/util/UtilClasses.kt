package co.yml.kmm.charts.util

import android.util.Log

actual class DecimalFormat {
    actual fun format(double: Float): String {
        val df = java.text.DecimalFormat()
        df.isGroupingUsed = false
        df.maximumFractionDigits = 1
        df.isDecimalSeparatorAlwaysShown = false
        return df.format(double)
    }
}

actual class LoggingFile {
    actual fun log(string: String) {
        Log.d("Harshaaaa", string)
    }
}