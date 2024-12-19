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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.animation.core.animateFloatAsState


@Composable
fun CircularBubbleLevel(x: Float, y: Float) {
    // Smooth aniSingleAxisBubbleLevelmation for bubble movement
    val animatedX by animateFloatAsState(
        targetValue = (x * 20).coerceIn(-150f, 150f),
        animationSpec = tween(durationMillis = 300)
    )
    val animatedY by animateFloatAsState(
        targetValue = (y * 20).coerceIn(-150f, 150f),
        animationSpec = tween(durationMillis = 300)
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasCenter = center

        // Draw gradient-filled outer circle
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.LightGray, Color.DarkGray),
                center = canvasCenter,
                radius = 200f
            ),
            radius = 200f,
            center = canvasCenter,
            style = Stroke(8f)
        )

        // Draw the bubble with shadow
        drawCircle(
            color = Color.Blue,
            radius = 25f,
            center = Offset(canvasCenter.x + animatedX, canvasCenter.y - animatedY)
        )
    }
}