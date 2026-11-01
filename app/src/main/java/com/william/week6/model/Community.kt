package com.william.week6.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Community(
    val id: Int,
    val name: String,
    val category: String,
    val memberCount: Int,
    val description: String,
    val icon: ImageVector,
    val iconBackground: Color,
    val iconColor: Color,
    val joined: Boolean
)