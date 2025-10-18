package com.example.practica1moviles22200222.data.model

enum class ActivityType(val caloriesPerMinute: Int, val imageUrl: String) {
    CORRER(
        10,
        "https://t3.ftcdn.net/jpg/14/38/19/64/240_F_1438196458_CQVOuz86J9Ex5psKUQoxOJbFk9dZsX2t.jpg"
    ),
    CAMINAR(
        5,
        "https://t3.ftcdn.net/jpg/02/94/18/10/240_F_294181088_yxiWWPpmcNAl31qjRFd84rRdefZNSQaG.jpg"
    ),
    NADAR(
        8,
        "https://images.unsplash.com/photo-1530549387789-4c1017266635?q=80&w=2070&auto=format&fit=crop"
    ),
    CICLISMO(
        7,
        "https://t3.ftcdn.net/jpg/00/43/99/16/240_F_43991659_Lp7AGLuQKpHj3HzBsyinHDkDCBLpQpsc.jpg"
    ),
    YOGA(
        4,
        "https://t3.ftcdn.net/jpg/01/59/47/24/240_F_159472423_kPS5tQLHJnipimJgdHw250rJ9wioTMs3.jpg"
    )
}
