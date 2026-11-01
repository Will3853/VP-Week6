package com.william.week6.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.william.week6.data.CommunitiesRepository
import com.william.week6.data.ProjectsRepository
import com.william.week6.model.Community
import com.william.week6.model.Project
import com.william.week6.navigation.ProjectRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class ProjectUiState(
    val projectList: List<Project> = emptyList(),
    val community: Community? = null,

    val name: String = "",
    val description: String = "",
    val techStack: List<String> = emptyList(),

    val showError: Boolean = false,
    val isSaved: Boolean = false
)
{
    val nameError: Boolean
        get() = showError && name.isBlank()

    val descriptionError: Boolean
        get() = showError && description.isBlank()

    val techStackError: Boolean
        get() = showError && techStack.isEmpty()
}

class ProjectViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val projectIcons = listOf(
        Icons.Filled.Android to (Color(0xFF4169D8) to Color(0xFFE3ECFF)),
        Icons.Filled.Code to (Color(0xFF35B66A) to Color(0xFFE2F8E9)),
        Icons.Filled.Cloud to (Color(0xFF8052D6) to Color(0xFFEDE6FF)),
        Icons.Filled.Language to (Color(0xFFE59A35) to Color(0xFFFFF1DC)),
        Icons.Filled.School to (Color(0xFFE45D72) to Color(0xFFFFE8EC))
    )
    private val _uiState = MutableStateFlow(ProjectUiState())
    private val projectRepository: ProjectsRepository = ProjectsRepository
    private val communityId: Int = runCatching {
        savedStateHandle.toRoute<ProjectRoute>().projectId
    }.getOrDefault(-1)

    val uiState: StateFlow<ProjectUiState> = combine(
        projectRepository.projects,
        _uiState
    ) { projects, formState ->

        formState.copy(
            community = CommunitiesRepository.getCommunitiesItem(communityId),
            projectList = projects
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value.copy(
            community = CommunitiesRepository.getCommunitiesItem(communityId),
            projectList = projectRepository.projects.value
        )
    )

    fun onNameChange(value: String) {
        _uiState.update {
            it.copy(name = value)
        }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update {
            it.copy(description = value)
        }
    }

    fun onTechStackChange(value: String) {
        _uiState.update { state ->
            state.copy(
                techStack = if (value in state.techStack) {
                    state.techStack - value
                } else {
                    state.techStack + value
                }
            )
        }
    }

    fun onSubmit() {
        val state = _uiState.value
        val style = projectIcons.random()
        if (
            state.name.isBlank() || state.description.isBlank() || state.techStack.isEmpty()
        ) {
            _uiState.update {
                it.copy(showError = true)
            }
            return
        }

        projectRepository.add(
            Project(
                id = "",
                communityId = communityId,
                name = state.name.trim(),
                description = state.description.trim(),
                techStack = state.techStack,
                status = "In Pogress",
                icon = style.first,
                iconColor = style.second.first,
                iconBackground = style.second.second
            )
        )

        _uiState.update {
            it.copy(
                name = "",
                description = "",
                techStack = emptyList(),
                showError = false,
                isSaved = true
            )
        }
    }

    fun resetForm() {
        _uiState.update {
            it.copy(
                name = "",
                description = "",
                techStack = emptyList(),
                showError = false,
                isSaved = false
            )
        }
    }

    fun onDeleteProject(projectId: String) {
        projectRepository.deleteAt(projectId)
    }
}