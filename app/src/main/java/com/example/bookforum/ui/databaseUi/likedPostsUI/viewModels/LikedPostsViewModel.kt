package com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.repositories.LikedPostsRepository
import com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts.FeedDestination
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class LikedPostsViewModel(
    savedStateHandle: SavedStateHandle,
    private val likedPostsRepository: LikedPostsRepository
): ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[FeedDestination.userIdArg])
    fun checkCommentExistence(userId: Int, postId: Int): Int? {
        val likedPostUiState : StateFlow<Int?> = likedPostsRepository
            .getLikedPostByIds(userId =userId, postId = postId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
                initialValue = null
            )
        return likedPostUiState.value
    }

    suspend fun updateLikedPost(likedPost: LikedPost) {
        if (checkCommentExistence(likedPost.userId, likedPost.postId) == null) {
            likedPostsRepository.deleteLikedPost(likedPost)
        } else {
            likedPostsRepository.insertLikedPost(likedPost)
        }
    }
}