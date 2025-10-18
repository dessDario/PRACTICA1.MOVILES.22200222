package com.example.practica1moviles22200222.data.model

enum class ActivityType(val caloriesPerMinute: Int) {
    CORRER(10),
    CAMINAR(5),
    NADAR(8),
    CICLISMO(7),
    YOGA(4)
}