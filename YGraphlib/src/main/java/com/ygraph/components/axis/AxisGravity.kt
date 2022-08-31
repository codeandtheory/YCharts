package com.ygraph.components.axis

/**
 * To specify gravity positions
 */
sealed class AxisGravity {
    object Left : AxisGravity()  // Y-Axis left aligned & X-Axis aligned to bottom
    object Right : AxisGravity() // Y-Axis right aligned & X-Axis aligned to top
}
