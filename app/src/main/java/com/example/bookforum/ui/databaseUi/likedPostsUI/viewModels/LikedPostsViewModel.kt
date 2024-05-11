package com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.repositories.LikedPostsRepository
import com.example.bookforum.ui.navigation.destinations.postDestinations.FeedDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LikedPostsViewModel(
    savedStateHandle: SavedStateHandle,
    private val likedPostsRepository: LikedPostsRepository
): ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[FeedDestination.userIdArg])
    /*TODO fix flows*/
    private val _checkedPostFlow = MutableStateFlow<Int?>(null)
    val checkedPostFlow: StateFlow<Int?> get() = _checkedPostFlow.asStateFlow()

    fun checkLikedPostExistence(userId: Int, postId: Int) {
        viewModelScope.launch {
            val checkedPost = likedPostsRepository
                .getLikedPostByIds(userId = userId, postId = postId)
                .stateIn(
                    scope = viewModelScope
                ).value

            _checkedPostFlow.update { checkedPost }
        }
    }

    suspend fun updateLikedPost(likedPost: LikedPost) {
        Log.i("UPDATE_LIKE", likedPost.toString())
        if (checkedPostFlow.value == null) {
            Log.i("ACTION", "insert")
            likedPostsRepository.insertLikedPost(likedPost)
        } else {
            Log.i("ACTION", "delete")
            likedPostsRepository.deleteLikedPost(likedPost)
        }
        checkLikedPostExistence(likedPost.userId, likedPost.postId)
    }
}