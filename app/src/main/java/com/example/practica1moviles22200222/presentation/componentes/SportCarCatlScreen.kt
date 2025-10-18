package com.example.practica1moviles22200222.presentation.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

data class SportCar(val brand: String, val model: String, val approximatePrice: Double, val imageUrl: String)

val sportCars = listOf(
    SportCar("Ferrari", "488 Pista", 330000.0, "https://www.kindpng.com/picc/m/598-5980597_488-pista-spider-thumba-ferrari-488-pista-png.png"),
    SportCar("Lamborghini", "Huracan EVO", 261274.0, "https://t3.ftcdn.net/jpg/04/67/39/40/240_F_467394066_K0vSWoawutP0HY9HgqESi6DGvDoQO1nv.jpg"),
    SportCar("Porsche", "911 Carrera S", 117100.0, "https://t4.ftcdn.net/jpg/16/02/76/13/240_F_1602761301_SyDs3whSxyaVfg3mKU0t1EVIYnRgDbpj.jpg"),
    SportCar("McLaren", "720S", 299000.0, "https://cdn.motor1.com/images/mgl/zxxA6/s3/mclaren-720s-by-novitec.jpg"),
    SportCar("Audi", "R8", 142700.0, "https://cdn.motor1.com/images/mgl/L998W/s3/audi-r8-v10-performance-rwd.jpg")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportCarCatlScreen(navController: NavController) {
    val totalCost = sportCars.sumOf { it.approximatePrice }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Autos Deportivos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retroceder"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(sportCars) { car ->
                    Card(modifier = Modifier.padding(bottom = 16.dp)) {
                        Column {
                            Image(
                                painter = rememberAsyncImagePainter(car.imageUrl),
                                contentDescription = "${car.brand} ${car.model}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "${car.brand} ${car.model}",
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Precio: $${"%,.2f".format(car.approximatePrice)}",
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            )
                        }
                    }
                }
            }
            Text(
                text = "Costo Total: $${"%,.2f".format(totalCost)}",
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}