package com.example.practica1moviles22200222.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica1moviles22200222.presentation.componentes.PhysiActivityLogScreen
import com.example.practica1moviles22200222.presentation.componentes.SportCarCatlScreen
import com.example.practica1moviles22200222.presentation.componentes.WaterComCalScreen
import com.example.practica1moviles22200222.presentation.home.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController)
        }
        composable("water_consumption_calculator"){
            WaterComCalScreen(navController)
        }
        composable("physical_activity_log"){
            PhysiActivityLogScreen(navController)
        }
        composable("sports_car_catalog"){
            SportCarCatlScreen(navController)
        }
    }
}