package com.ygraph.components.barchart

sealed class BarChartType {
    object GroupBarChart : BarChartType()
    object BarChart : BarChartType()
}