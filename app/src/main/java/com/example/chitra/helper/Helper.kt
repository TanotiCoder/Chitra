package com.example.chitra.helper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun pointPath(points: List<Offset>) = Path().apply {
    if (points.isNotEmpty()) {
        var oldPoint: Offset? = null
        this.moveTo(points[0].x, points[0].y)
        for (i in 1 until points.size) {
            val point = points[i]
            oldPoint?.let {
                this.lineTo(it.x, it.y)
            }
            oldPoint = point
        }
        oldPoint?.let { this.lineTo(it.x, it.y) }
    }
}


