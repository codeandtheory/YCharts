package co.yml.kmm.charts.util

actual class DecimalFormat {
    actual fun format(double: Float): String {
        val df = java.text.DecimalFormat()
        df.isGroupingUsed = false
        df.maximumFractionDigits = 1
        df.isDecimalSeparatorAlwaysShown = false
        return df.format(double)
    }
}