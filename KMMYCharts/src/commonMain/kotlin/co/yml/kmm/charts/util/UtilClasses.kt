package co.yml.kmm.charts.util

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class DecimalFormat() {
    fun format(double: Float) : String
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class LoggingFile() {
    fun log(string: String)
}