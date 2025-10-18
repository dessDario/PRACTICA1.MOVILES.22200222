package com.example.practica1moviles22200222.data.model

enum class ActivityType(val caloriesPerMinute: Int, val imageUrl: String) {
    CORRER(
        10,
        "https://images.unsplash.com/photo-1541534401786-204b8a203f62?q=80&w=1974&auto=format&fit=crop"
    ),
    CAMINAR(
        5,
        "https://images.unsplash.com/photo-1551643519-2df0a4240828?q=80&w=1974&auto=format&fit=crop"
    ),
    NADAR(
        8,
        "https://images.unsplash.com/photo-1530549387789-4c1017266635?q=80&w=2070&auto=format&fit=crop"
    ),
    CICLISMO(
        7,
        "https://images.unsplash.com/photo-1499858273932-e5cbf10a623f?q=80&w=2070&auto=format&fit=crop"
    ),
    YOGA(
        4,
        "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?q=80&w=2120&auto=format&fit=crop"
    )
}
