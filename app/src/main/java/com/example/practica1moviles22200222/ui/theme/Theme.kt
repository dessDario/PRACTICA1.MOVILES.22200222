package com.example.practica1moviles22200222.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val C1 = Color(0xFF006D46)
val C2 = Color(0xFF00774F)
val C3 = Color(0xFF008259)
val C4 = Color(0xFF008D62)
val C5 = Color(0xFF00986C)
val C6 = Color(0xFF2CA880)
val C7 = Color(0xFF57BD9E)
val C8 = Color(0xFF7AD3BE)
val C9 = Color(0xFF9CE9DE)
val C10= Color(0xFFBDFFFF)

private val DarkColorScheme = darkColorScheme(
    primary = C1,
    secondary = C2,
    tertiary = C3,
    background = C10,
    surface = C9,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = C1,
    onSurface = C2
)

private val LightColorScheme = lightColorScheme(
    primary = C5,
    secondary = C6,
    tertiary = C7,
    background = Color.White,
    surface = C10,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun PRACTICA1MOVILES22200222Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}