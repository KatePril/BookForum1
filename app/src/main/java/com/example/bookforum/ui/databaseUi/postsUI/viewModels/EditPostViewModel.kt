package com.example.bookforum.ui.databaseUi.postsUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.ui.databaseUi.postsUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.postsUI.states.toPost
import com.example.bookforum.ui.navigation.destinations.postDestinations.EditPostDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EditPostViewModel(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[EditPostDestination.userIdArg])
    val postId: Int = checkNotNull(savedStateHandle[EditPostDestination.postIdArg])

    var postUiState by mutableStateOf(PostCreationUiState())
        private set

    init {
        viewModelScope.launch {
            postUiState = postsRepository
                .getPost(postId)
                .filterNotNull()
                .map { PostCreationUiState(it.toDetails(), areInputsValid = true) }
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(postDetails: PostDetails) {
        postUiState = PostCreationUiState(
            postDetails = postDetails,
            areInputsValid = validateInputs(postDetails)
        )
    }

    private fun validateInputs(postDetails: PostDetails = postUiState.postDetails): Boolean {
        return with(postDetails) {
            title.isNotBlank() && author.isNotBlank() && published.isNotBlank() && review.isNotBlank()
        }
    }

    suspend fun updatePost() {
        if (validateInputs()) {
            postsRepository.updatePost(postUiState.postDetails.toPost())
        }
    }

    suspend fun deletePost() {
        postsRepository.deletePost(postUiState.postDetails.toPost())
    }
}