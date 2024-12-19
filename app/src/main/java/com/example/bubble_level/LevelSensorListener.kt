package com.example.bubble_level

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.compose.runtime.mutableStateOf

class LevelSensorListener : SensorEventListener {
    // Observable state variables for accelerometer values
    val x = mutableStateOf(0f)
    val y = mutableStateOf(0f)
    val z = mutableStateOf(0f)

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            x.value = it.values[0]  // Acceleration in X-axis
            y.value = it.values[1]  // Acceleration in Y-axis
            z.value = it.values[2]  // Acceleration in Z-axis
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No-op: We are not concerned with accuracy changes for now
    }
}