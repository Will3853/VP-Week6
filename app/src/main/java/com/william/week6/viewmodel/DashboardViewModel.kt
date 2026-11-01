package com.william.week6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.william.week6.data.CommunitiesRepository
import com.william.week6.data.ProjectsRepository
import com.william.week6.model.Community
import com.william.week6.model.Project
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DashboardUiState(
    val communityList: List<Community> = emptyList(),
    val projectList: List<Project> = emptyList()
)

class DashboardViewModel(
    private val communitiesRepository: CommunitiesRepository = CommunitiesRepository,
    private val projectRepository: ProjectsRepository = ProjectsRepository
): ViewModel() {
    val uiState: StateFlow<DashboardUiState> = combine(
        communitiesRepository.communities,
        projectRepository.projects
    ) { communities, projects ->
        DashboardUiState(
            communityList = communities,
            projectList = projects
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardUiState(communityList = communitiesRepository.getCommunitiesList())
    )
}
