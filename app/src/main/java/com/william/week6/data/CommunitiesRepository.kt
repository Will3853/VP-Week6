package com.william.week6.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.ui.graphics.Color
import com.william.week6.model.Community
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CommunitiesRepository {
    private val initialCommunities = listOf(
        Community(
            id = 0,
            name = "GDSC",
            category = "Google Developer Student Club",
            memberCount = 142,
            description = "Fokus pada ekosistem Google: Android Compose, Flutter, Firebase, dan Google Cloud Platform.",
            icon = Icons.Default.Code,
            iconBackground = Color(0xFFE0E7FF),
            iconColor = Color(0xFF2563EB),
            joined = false
        ),

        Community(
            id = 1,
            name = "CyberSecurity Guild",
            category = "Security & Pentesting",
            memberCount = 88,
            description = "Eksplorasi ethical hacking, CTF challenges, reverse engineering, dan pertahanan jaringan kampus.",
            icon = Icons.Default.Security,
            iconBackground = Color(0xFFFEE2E2),
            iconColor = Color(0xFFDC2626),
            joined = false
        ),

        Community(
            id = 2,
            name = "AI Society",
            category = "Machine Learning & LLM",
            memberCount = 115,
            description = "Riset terapan deep learning, computer vision, agen cerdas, dan fine-tuning large language models.",
            icon = Icons.Default.AutoAwesome,
            iconBackground = Color(0xFFEDE9FE),
            iconColor = Color(0xFF7C3AED),
            joined = false
        ),

        Community(
            id = 3,
            name = "Mobile Dev Community",
            category = "Native Jetpack Compose & iOS",
            memberCount = 95,
            description = "Komunitas pengembang aplikasi mobile yang berfokus pada Android, Jetpack Compose, dan iOS development.",
            icon = Icons.Default.PhoneAndroid,
            iconBackground = Color(0xFFE0F2FE),
            iconColor = Color(0xFF0284C7),
            joined = false
        ),

        Community(
            id = 4,
            name = "Game Developer Club",
            category = "Game Development & Design",
            memberCount = 76,
            description = "Belajar game development menggunakan Unity, Unreal Engine, game programming, dan game design.",
            icon = Icons.Default.SportsEsports,
            iconBackground = Color(0xFFDCFCE7),
            iconColor = Color(0xFF16A34A),
            joined = false
        ),

        Community(
            id = 5,
            name = "UI/UX Design Society",
            category = "UI/UX & Product Design",
            memberCount = 103,
            description = "Eksplorasi user interface, user experience, prototyping, dan desain produk digital menggunakan Figma.",
            icon = Icons.Default.Palette,
            iconBackground = Color(0xFFFFEDD5),
            iconColor = Color(0xFFEA580C),
            joined = false
        )
    )

    private val _communities = MutableStateFlow(initialCommunities)
    val communities: StateFlow<List<Community>> = _communities.asStateFlow()

    fun joinCommunity(id: Int) {
        _communities.update { list ->
            list.map { community ->
                if (community.id == id) community.copy(joined = true) else community
            }
        }
    }

    fun getCommunitiesList(): List<Community> = _communities.value

    fun getCommunitiesItem(id: Int): Community? = _communities.value.firstOrNull { it.id == id }
}