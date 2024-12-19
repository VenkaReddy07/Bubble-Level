package com.example.bubble_level

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bubble_level.composables.LevelApp

class MainActivity : ComponentActivity() {
    private lateinit var sensorManager: SensorManager
    private lateinit var levelSensorListener: LevelSensorListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize sensor manager and listener
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        levelSensorListener = LevelSensorListener()

        setContent {
            LevelApp(sensorListener = levelSensorListener)
        }
    }

    override fun onResume() {
        super.onResume()
        // Register the accelerometer sensor listener
        sensorManager.registerListener(
            levelSensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI
        )
    }

    override fun onPause() {
        super.onPause()
        // Unregister the sensor listener to save battery
        sensorManager.unregisterListener(levelSensorListener)
    }
}
