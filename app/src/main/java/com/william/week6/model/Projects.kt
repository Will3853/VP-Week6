package com.william.week6.model

import android.media.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Project(
    val id: String,
    val communityId: Int,
    val name: String,
    val description: String,
    val techStack: List<String>,
    val status: String,
    val icon: ImageVector,
    val iconBackground: Color,
    val iconColor: Color
)