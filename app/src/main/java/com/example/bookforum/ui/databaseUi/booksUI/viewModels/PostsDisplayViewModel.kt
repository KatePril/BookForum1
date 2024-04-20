package com.example.bookforum.ui.databaseUi.booksUI.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.TIMEOUT_MILLS
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.databaseUi.booksUI.states.PostsDisplayUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PostsDisplayViewModel(private val postsRepository: PostsRepository): ViewModel() {
    val postsUiState: StateFlow<PostsDisplayUiState> = postsRepository
        .getAllPosts()
        .map { PostsDisplayUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
            initialValue = PostsDisplayUiState()
        )
}