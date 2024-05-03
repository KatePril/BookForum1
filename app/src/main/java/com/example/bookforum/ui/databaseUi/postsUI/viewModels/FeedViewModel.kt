package com.example.bookforum.ui.databaseUi.postsUI.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts.FeedDestination
import com.example.bookforum.ui.databaseUi.postsUI.states.PostsDisplayUiState
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FeedViewModel(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[FeedDestination.userIdArg])

    val postsUiState: StateFlow<PostsDisplayUiState> = postsRepository
        .getAllPosts()
        .map { PostsDisplayUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
            initialValue = PostsDisplayUiState()
        )

}

