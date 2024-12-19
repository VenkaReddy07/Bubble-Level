package com.example.bubble_level.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
@Composable
fun SingleAxisBubbleLevel(x: Float) {
    // Smooth animation for bubble movement
    val animatedX by animateFloatAsState(
        targetValue = (x * 20).coerceIn(-150f, 150f),
        animationSpec = tween(durationMillis = 300)
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasCenter = center

        // Draw gradient horizontal line
        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(Color.LightGray, Color.DarkGray)
            ),
            start = Offset(canvasCenter.x - 200f, canvasCenter.y),
            end = Offset(canvasCenter.x + 200f, canvasCenter.y),
            strokeWidth = 8f
        )

        // Draw the bubble with shadow
        drawCircle(
            color = Color.Red,
            radius = 25f,
            center = Offset(canvasCenter.x + animatedX, canvasCenter.y)
        )
    }
}