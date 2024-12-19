package com.example.bubble_level.composables

import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bubble_level.LevelSensorListener
import com.example.bubble_level.utils.OrientationUtils
import kotlin.math.abs

@Composable
fun LevelApp(sensorListener: LevelSensorListener) {
    val x = sensorListener.x.value
    val y = sensorListener.y.value
    val z = sensorListener.z.value

    // State for calibration offsets
    val calibrationOffsetX = remember { mutableStateOf(0f) }
    val calibrationOffsetY = remember { mutableStateOf(0f) }

    // Determine if the bubble is perfectly level
    val isLevel = OrientationUtils.isFlat(z) &&
            abs(x - calibrationOffsetX.value) < 0.5 &&
            abs(y - calibrationOffsetY.value) < 0.5

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display orientation values
        Text(text = "X: ${"%.2f".format(x)} | Y: ${"%.2f".format(y)} | Z: ${"%.2f".format(z)}", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

        // Feedback text
        Text(
            text = if (isLevel) "Perfectly Level!" else "Adjust to Level",
            style = MaterialTheme.typography.headlineSmall,
            color = if (isLevel) Color.Green else Color.Red
        )

        Spacer(modifier = Modifier.weight(1f))

        // Display the appropriate bubble level
        if (OrientationUtils.isFlat(z)) {
            CircularBubbleLevel(x - calibrationOffsetX.value, y - calibrationOffsetY.value)
        } else if (OrientationUtils.isPerpendicular(z)) {
            SingleAxisBubbleLevel(x - calibrationOffsetX.value)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Calibration button
        Button(
            onClick = {
                calibrationOffsetX.value = x
                calibrationOffsetY.value = y
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Calibrate")

        }
    }
}