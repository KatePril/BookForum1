package com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.Post
import com.example.bookforum.data.repositories.LikedPostsRepository
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.navigation.destinations.LikedPostsPageDestination
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LikedPostsListViewModel(
    savedStateHandle: SavedStateHandle,
    private val likedPostsRepository: LikedPostsRepository,
    private val postsRepository: PostsRepository
): ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[LikedPostsPageDestination.userIdArg])

    var postsListUiState: List<Post>? = emptyList()

    init {
        viewModelScope.launch {
            val idsList = likedPostsRepository
                .getLikedPosts(userId)
                .stateIn(
                    scope = viewModelScope
                ).value
            if (idsList != null) {
                postsListUiState = idsList.map { getPostById(it).value!! }
            }
        }
    }


    private suspend fun getPostById(id: Int): StateFlow<Post?> = postsRepository
        .getPost(id)
        .stateIn(
            scope = viewModelScope
        )
}