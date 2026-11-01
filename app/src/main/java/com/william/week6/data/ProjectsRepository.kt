package com.william.week6.data

import com.william.week6.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.emptyList

object ProjectsRepository {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())

    val projects: StateFlow<List<Project>> = _projects.asStateFlow()
    fun getProject(id: String): Project? {
        return _projects.value.firstOrNull { it.id == id }
    }
    fun add(project: Project) {
        _projects.update { projects ->

            val prefix = when (project.communityId) {
                0 -> "gdsc"
                1 -> "cybr"
                2 -> "ai"
                3 -> "mble"
                4 -> "game"
                5 -> "dsgn"
                else -> "community"
            }

            val highestNumber = projects
                .filter { it.communityId == project.communityId }
                .mapNotNull { existingProject ->
                    existingProject.id
                        .substringAfterLast("-")
                        .toIntOrNull()
                }
                .maxOrNull() ?: 0

            val nextNumber = highestNumber + 1
            val newId = "proj-$prefix-${nextNumber.toString().padStart(2, '0')}"
            projects + project.copy(id = newId)
        }
    }
    fun deleteAt(projectId: String) {
        _projects.update { projects ->
            projects.filterNot { it.id == projectId }
        }
    }

}