package com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.repositories.LikedPostsRepository
import com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts.FeedDestination
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LikedPostsViewModel(
    savedStateHandle: SavedStateHandle,
    private val likedPostsRepository: LikedPostsRepository
): ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[FeedDestination.userIdArg])

    private val _checkedPostLiveData = MutableLiveData<Int?>()
    val checkedPostLiveData: LiveData<Int?> = _checkedPostLiveData

    fun checkLikedPostExistence(userId: Int, postId: Int) {
        viewModelScope.launch {
            val checkedPost = likedPostsRepository
                .getLikedPostByIds(userId = userId, postId = postId)
                .stateIn(scope = viewModelScope)
                .value

            _checkedPostLiveData.postValue(checkedPost)
        }
    }

    suspend fun updateLikedPost(likedPost: LikedPost) = runBlocking {
        Log.i("UPDATE_LIKE", likedPost.toString())
        if (_checkedPostLiveData.value == null) {
            likedPostsRepository.insertLikedPost(likedPost)
        } else {
            likedPostsRepository.deleteLikedPost(likedPost)
        }
    }
}