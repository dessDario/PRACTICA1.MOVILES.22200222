package com.example.practica1moviles22200222.presentation.componentes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.practica1moviles22200222.data.model.ActivityType
import com.example.practica1moviles22200222.data.model.Intensity

data class RegisteredActivity(
    val type: ActivityType,
    val duration: Int,
    val intensity: Intensity,
    val caloriesBurned: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysiActivityLogScreen(navController: NavController) {
    var selectedActivityType by remember { mutableStateOf<ActivityType?>(null) }
    var duration by remember { mutableStateOf("") }
    var selectedIntensity by remember { mutableStateOf<Intensity?>(null) }
    var registeredActivities by remember { mutableStateOf(listOf<RegisteredActivity>()) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val c1 = Color(0xFF006D46)
    val c6 = Color(0xFF2CA880)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Actividad Física", color = Color.White) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // --- Selector de actividad mejorado ---
                    Box {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = true }
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedActivityType?.name ?: "Seleccione una actividad",
                                color = if (selectedActivityType == null) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Desplegar")
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth(0.85f)
                        ) {
                            ActivityType.entries.forEach { activityType ->
                                DropdownMenuItem(
                                    text = { Text(activityType.name) },
                                    onClick = {
                                        selectedActivityType = activityType
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    TextField(
                        value = duration,
                        onValueChange = { duration = it },
                        label = { Text("Duración (minutos)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Intensity.entries.forEach { intensity ->
                            RadioButton(
                                selected = selectedIntensity == intensity,
                                onClick = { selectedIntensity = intensity }
                            )
                            Text(intensity.name)
                        }
                    }

                    Button(
                        onClick = {
                            val durationValue = duration.toIntOrNull()

                            if (selectedActivityType == null || durationValue == null || selectedIntensity == null) {
                                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            if (durationValue <= 0) {
                                Toast.makeText(context, "La duración debe ser un número entero positivo.", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            val caloriesBurned = selectedActivityType!!.caloriesPerMinute * durationValue * selectedIntensity!!.factor
                            val newActivity = RegisteredActivity(selectedActivityType!!, durationValue, selectedIntensity!!, caloriesBurned)
                            registeredActivities = registeredActivities + newActivity
                        },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = c6)
                    ) {
                        Text("Registrar actividad")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(registeredActivities) { activity ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = activity.type.imageUrl,
                                contentDescription = "Imagen de ${activity.type.name}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                            )
                            Spacer(Modifier.padding(horizontal = 8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = activity.type.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                                Text("Duración: ${activity.duration} min")
                                Text("Intensidad: ${activity.intensity.name}")
                            }
                            Text(
                                text = "%.1f\nKcal".format(activity.caloriesBurned),
                                color = c1,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
