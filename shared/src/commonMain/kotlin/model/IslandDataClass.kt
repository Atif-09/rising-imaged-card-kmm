package model

import androidx.compose.ui.graphics.Color

data class IslandDataClass(
    val id: Int,
    val name: String,
    val country: String,
    val color: Color,
    val resImage: String
)
