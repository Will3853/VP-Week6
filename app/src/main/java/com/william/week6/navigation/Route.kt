package com.william.week6.navigation

import kotlinx.serialization.Serializable

    @Serializable
    object DashBoardRoute

    @Serializable
    object CommunitiesRoute

    @Serializable
    data class ProjectRoute(val projectId: Int)
