package com.example.bookforum.ui.databaseUi.postsUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.postsUI.screens.createPost.PostCreationDestination
import com.example.bookforum.ui.databaseUi.postsUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.postsUI.states.toPost
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserByIdViewModel

class PostCreationViewModel(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository
): UserByIdViewModel(savedStateHandle, PostCreationDestination.userIdArg, usersRepository) {

    var postCreationUiState by mutableStateOf(PostCreationUiState())
    fun updateUiState(postDetails: PostDetails) {
        postCreationUiState = PostCreationUiState(
            postDetails = postDetails,
            areInputsValid = validateInputs(postDetails)
        )
    }

    suspend fun savePost() {
        if (validateInputs()) {
            postsRepository.insertPost(postCreationUiState.postDetails.toPost())
        }
    }

    private fun validateInputs(postDetails: PostDetails = postCreationUiState.postDetails): Boolean {
        return with(postDetails) {
            title.isNotBlank() && author.isNotBlank() && published.isNotBlank() && review.isNotBlank()
        }
    }
}