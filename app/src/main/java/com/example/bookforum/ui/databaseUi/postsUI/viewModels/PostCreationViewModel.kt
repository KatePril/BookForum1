package com.example.bookforum.ui.databaseUi.postsUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.databaseUi.postsUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.postsUI.states.toPost
import com.example.bookforum.ui.navigation.destinations.postDestinations.PostCreationDestination

class PostCreationViewModel(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[PostCreationDestination.userIdArg])

    var postCreationUiState by mutableStateOf(PostCreationUiState())
    fun updateUiState(postDetails: PostDetails) {
        postCreationUiState = PostCreationUiState(
            postDetails = postDetails.copy(userId = userId),
            areInputsValid = validateInputs(postDetails)
        )
    }

    private fun validateInputs(postDetails: PostDetails = postCreationUiState.postDetails): Boolean {
        return with(postDetails) {
            title.isNotBlank() && author.isNotBlank() && published.isNotBlank() && review.isNotBlank()
        }
    }

    suspend fun savePost() {
        if (validateInputs()) {
            postsRepository.insertPost(postCreationUiState.postDetails.toPost())
        }
    }
}