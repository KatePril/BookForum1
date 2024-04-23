package com.example.bookforum.ui.databaseUi.booksUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.booksUI.screens.displayPosts.PostsDisplayDestination
import com.example.bookforum.ui.databaseUi.booksUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.booksUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.booksUI.states.UserByIdUiState
import com.example.bookforum.ui.databaseUi.booksUI.states.toPost
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PostCreationViewModel(
    savedStateHandle: SavedStateHandle,
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle[PostsDisplayDestination.userIdArg])

    var postCreationUiState by mutableStateOf(PostCreationUiState())

    val userUiState: StateFlow<UserByIdUiState> =
        usersRepository.getUserById(userId)
            .filterNotNull()
            .map { UserByIdUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
                initialValue = UserByIdUiState()
            )

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