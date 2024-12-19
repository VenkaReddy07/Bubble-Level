package com.example.bubble_level.utils

import kotlin.math.abs

object OrientationUtils {
    // Thresholds to determine orientation
    private const val FLAT_THRESHOLD = 8.0f
    private const val PERPENDICULAR_THRESHOLD = 2.0f

    // Determines if the device is flat
    fun isFlat(z: Float): Boolean {
        return abs(z) > FLAT_THRESHOLD
    }

    // Determines if the device is perpendicular
    fun isPerpendicular(z: Float): Boolean {
        return abs(z) <= PERPENDICULAR_THRESHOLD
    }
}