package com.example.practica1moviles22200222.presentation.componentes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.practica1moviles22200222.data.model.Gender

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterComCalScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<Gender?>(null) }
    var result by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val c1 = Color(0xFF006D46) // AppBar
    val c6 = Color(0xFF2CA880) // Botón
    val c7 = Color(0xFF57BD9E) // Degradado
    val c8 = Color(0xFF7AD3BE) // Tarjeta resultado suave

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Agua", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retroceder",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = c1)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to c7.copy(alpha = 0.20f),
                        1.0f to c6.copy(alpha = 0.10f)
                    )
                )
                .padding(paddingValues)
        ) {
            val scroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = "https://t3.ftcdn.net/jpg/03/01/29/96/240_F_301299617_SOelyJ9cAMQsgSAQfUOoggncgnh43pmM.jpg",
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, Color(0x66006D46))
                                    )
                                )
                        )
                        Text(
                            text = "Hidratación diaria recomendada",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Nombre de la persona") },
                            leadingIcon = { Icon(Icons.Outlined.Badge, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        TextField(
                            value = weight,
                            onValueChange = { weight = it },
                            label = { Text("Peso (kg)") },
                            leadingIcon = { Icon(Icons.Outlined.FitnessCenter, contentDescription = null) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // RadioButtons con la nueva disposición
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text("Género", color = c1)
                            Spacer(Modifier.height(4.dp))

                            // Fila para Masculino y Femenino
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = selectedGender == Gender.MASCULINO,
                                        onClick = { selectedGender = Gender.MASCULINO }
                                    )
                                    Text(Gender.MASCULINO.name)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = selectedGender == Gender.FEMENINO,
                                        onClick = { selectedGender = Gender.FEMENINO }
                                    )
                                    Text(Gender.FEMENINO.name)
                                }
                            }

                            // Fila para Sin Especificar (centrado)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = selectedGender == Gender.SIN_ESPECIFICAR,
                                        onClick = { selectedGender = Gender.SIN_ESPECIFICAR }
                                    )
                                    Text(Gender.SIN_ESPECIFICAR.name)
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp)
                        ) {
                            Button(
                                onClick = {
                                    val weightValue = weight.toFloatOrNull()
                                    if (name.isBlank() || weightValue == null || selectedGender == null) {
                                        Toast.makeText(
                                            context,
                                            "Todos los campos son obligatorios",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@Button
                                    }
                                    if (weightValue <= 4 || weightValue >= 201) {
                                        Toast.makeText(
                                            context,
                                            "El peso debe ser un número positivo entre 5 y 200.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@Button
                                    }
                                    val recommendedLitters =
                                        weightValue * 0.035 * selectedGender!!.factor
                                    result = "%s debe beber aproximadamente %.2f litros de agua al día"
                                        .format(name, recommendedLitters)
                                },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .widthIn(min = 180.dp)
                                    .height(52.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = c6,
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Calcular")
                            }
                        }
                    }
                }

                result?.let { text ->
                    Spacer(Modifier.height(14.dp))
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = c8.copy(alpha = 0.25f)
                        )
                    ) {
                        Text(
                            text = text,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
