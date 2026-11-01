package com.william.week6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.william.week6.data.CommunitiesRepository
import com.william.week6.model.Community
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class CommunitiesUiState(
    val communityList: List<Community> = emptyList(),
)

class CommunitiesViewModel(
    private val communitiesRepository: CommunitiesRepository = CommunitiesRepository
): ViewModel() {
    val uiState: StateFlow<CommunitiesUiState> = communitiesRepository.communities
        .map { list -> CommunitiesUiState(communityList = list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CommunitiesUiState(communityList = communitiesRepository.getCommunitiesList())
        )

    fun joinCommunity(id: Int) {
        communitiesRepository.joinCommunity(id)
    }
}