package com.ygraph.components.common.model

/**
 * PlotData is a base class to all the types of available graphs
 * @param plotType: Type of the graph given in [PlotType]
 */
interface PlotData {
    val plotType: PlotType
}

/**
 * PlotType is a sealed class to define the types of supported graphs/plots
 */
sealed interface PlotType {
    object Line : PlotType
    object Bar : PlotType
    object Pie : PlotType
    object Donut : PlotType
}
