package com.example.practica1moviles22200222.presentation.componentes

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica1moviles22200222.data.model.ActivityType
import com.example.practica1moviles22200222.data.model.Intensity

data class RegisteredActivity(val type: ActivityType, val duration: Int, val intensity: Intensity, val caloriesBurned: Double)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysiActivityLogScreen(navController: NavController) {
    var selectedActivityType by remember { mutableStateOf<ActivityType?>(null) }
    var duration by remember { mutableStateOf("") }
    var selectedIntensity by remember { mutableStateOf<Intensity?>(null) }
    var registeredActivities by remember { mutableStateOf(listOf<RegisteredActivity>()) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Actividad Física") },
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
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
        ) {

            Box {
                TextButton(onClick = { expanded = true }) {
                    Text(selectedActivityType?.name ?: "Seleccione una actividad")
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    ActivityType.entries.forEach { activityType ->
                        DropdownMenuItem(text = { Text(activityType.name) }, onClick = {
                            selectedActivityType = activityType
                            expanded = false
                        })
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

            Row(Modifier.padding(vertical = 8.dp)) {
                Intensity.entries.forEach { intensity ->
                    RadioButton(
                        selected = selectedIntensity == intensity,
                        onClick = { selectedIntensity = intensity }
                    )
                    Text(intensity.name)
                }
            }

            Button(onClick = {
                val durationValue = duration.toIntOrNull()

                if (selectedActivityType == null || durationValue == null || selectedIntensity == null) {
                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (durationValue <= 0) {
                    Toast.makeText(context, "La duración en minutos debe ser entera positiva.", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }

                val caloriesBurned = selectedActivityType!!.caloriesPerMinute * durationValue * selectedIntensity!!.factor
                val newActivity = RegisteredActivity(selectedActivityType!!, durationValue, selectedIntensity!!, caloriesBurned)
                registeredActivities = registeredActivities + newActivity

            }) {
                Text("Registrar actividad")
            }

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(registeredActivities) {
                    Text("Actividad: ${it.type.name}, Duración: ${it.duration} min, Intensidad: ${it.intensity.name}, Calorías: %.2f".format(it.caloriesBurned))
                }
            }
        }
    }
}
