package com.example.bookforum.ui.databaseUi.postsUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.Post
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.navigation.destinations.postDestinations.FeedDestination
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedViewModel(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[FeedDestination.userIdArg])

    var postsList by mutableStateOf(emptyList<Post>())
        private set

    init {
        viewModelScope.launch {
            postsList = postsRepository
                .getAllPosts()
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

}

