package com.example.bookforum.ui.databaseUi.booksUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.databaseUi.booksUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.booksUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.booksUI.states.toPost

class PostCreationViewModel(private val postsRepository: PostsRepository) : ViewModel() {
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