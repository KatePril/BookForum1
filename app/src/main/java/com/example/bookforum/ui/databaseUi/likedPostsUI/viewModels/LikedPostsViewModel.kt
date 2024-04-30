package com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels

import android.util.Log
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
import kotlinx.coroutines.launch

class LikedPostsViewModel(
    savedStateHandle: SavedStateHandle,
    private val likedPostsRepository: LikedPostsRepository
): ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[FeedDestination.userIdArg])

    private var checkedPost: Int? = null
    fun checkLikedPostExistence(userId: Int, postId: Int): Int? {
        viewModelScope.launch {
            checkedPost = likedPostsRepository
            .getLikedPostByIds(userId =userId, postId = postId)
            .stateIn(
                scope = viewModelScope
            ).value
        }
        Log.i("CHECKED_POST", checkedPost.toString())
        return checkedPost
    }



//    = likedPostsRepository
//            .getLikedPostByIds(userId =userId, postId = postId)
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
//                initialValue = null
//            ).value

    suspend fun updateLikedPost(likedPost: LikedPost) {
        Log.i("UPDATE_LIKE", likedPost.toString())
        if (checkLikedPostExistence(likedPost.userId, likedPost.postId) == null) {
            likedPostsRepository.insertLikedPost(likedPost)
        } else {
            likedPostsRepository.deleteLikedPost(likedPost)
        }
    }
}