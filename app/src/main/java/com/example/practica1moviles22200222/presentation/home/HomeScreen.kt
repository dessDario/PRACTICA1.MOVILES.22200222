package com.example.practica1moviles22200222.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido 👋",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Elige una opción",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )

        Spacer(Modifier.height(20.dp))

        // 1) Calculadora de consumo de agua
        NavImageCard(
            title = "Calculadora de consumo de agua",
            imageUrl = "https://t4.ftcdn.net/jpg/14/76/99/65/240_F_1476996529_TtwGE6gVbGngvmz25JuqIxYdiVIoX4se.jpg",
            onClick = { navController.navigate("water_consumption_calculator") }
        )

        Spacer(Modifier.height(14.dp))

        // 2) Registro de actividad física
        NavImageCard(
            title = "Registro de actividad física",
            imageUrl = "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?q=80&w=1200&auto=format&fit=crop",
            onClick = { navController.navigate("physical_activity_log") }
        )

        Spacer(Modifier.height(14.dp))

        // 3) Catálogo de Autos deportivos
        NavImageCard(
            title = "Catálogo de Autos deportivos",
            imageUrl = "https://images.unsplash.com/photo-1511919884226-fd3cad34687c?q=80&w=1200&auto=format&fit=crop",
            onClick = { navController.navigate("sports_car_catalog") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavImageCard(
    title: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(168.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen de fondo (URL)
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Scrim: degradado para legibilidad del texto
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0.0f to Color.Transparent,
                            0.55f to Color(0x66000000),
                            1.0f to Color(0x99000000)
                        )
                    )
            )

            // Título
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}
