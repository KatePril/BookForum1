package com.example.bookforum.ui.databaseUi.commentUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.Comment
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.databaseUi.commentUi.states.toComment
import com.example.bookforum.ui.navigation.destinations.CommentPageDestination
import com.example.bookforum.utils.getCurrentTime
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommentViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository,
    private val commentsRepository: CommentsRepository
) : ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[CommentPageDestination.userIdArg])
    private val postId: Int = checkNotNull(savedStateHandle[CommentPageDestination.postIdArg])

    var commentCreationUiState by mutableStateOf(CommentCreationUiState())

    var commentsUiState by mutableStateOf(emptyList<Comment>())

    var usersHashMap by mutableStateOf(emptyMap<Int, User>())

    init {
        viewModelScope.launch {
            commentsUiState = getCommentsList()
            fillUsers()
        }
    }

    private suspend fun getCommentsList(): List<Comment> = commentsRepository
        .getCommentsByPost(postId)
        .filterNotNull()
        .stateIn(
            scope = viewModelScope
        ).value

    private suspend fun fillUsers() {
        usersHashMap = emptyMap()
        for (comment in commentsUiState) {
            val user = usersRepository
                .getUserById(comment.userId)
                .filterNotNull()
                .stateIn(scope = viewModelScope)
                .value
            usersHashMap += Pair(user.id, user)
        }
    }

    fun updateUiState(commentDetails: CommentDetails) {
        commentCreationUiState = CommentCreationUiState(
            commentDetails = commentDetails
                .copy(
                    date = getCurrentTime(),
                    userId = userId,
                    postId = postId
                ),
            isTextValid = validateText(commentDetails)
        )
    }

    private fun validateText(
        commentDetails: CommentDetails = commentCreationUiState.commentDetails
    ): Boolean {
        return with(commentDetails) {
            text.isNotBlank()
        }
    }

    suspend fun saveComment() {
        if (validateText()) {
            commentsRepository.insertComment(commentCreationUiState.commentDetails.toComment())
            updateUiState(commentCreationUiState.commentDetails.copy(text = ""))
            commentsUiState = getCommentsList()
            fillUsers()
        }
    }
}