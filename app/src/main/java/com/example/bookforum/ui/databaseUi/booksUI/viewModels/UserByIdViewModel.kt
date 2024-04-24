package com.example.bookforum.ui.databaseUi.booksUI.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.booksUI.screens.displayPosts.PostsDisplayDestination
import com.example.bookforum.ui.databaseUi.booksUI.states.UserByIdUiState
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

open class UserByIdViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
): ViewModel()  {
    private val userId: Int = checkNotNull(savedStateHandle[PostsDisplayDestination.userIdArg])

    val userUiState: StateFlow<UserByIdUiState> =
        usersRepository.getUserById(userId)
            .filterNotNull()
            .map { UserByIdUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
                initialValue = UserByIdUiState()
            )
}