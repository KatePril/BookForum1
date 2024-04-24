package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

open class UserByUsernameViewModel(protected val usersRepository: UsersRepository) : ViewModel()  {
    fun getUserUiStateByUsername(username: String) = usersRepository.getUserByUsername(username)
        .map { it?.toDetails()?.let { details -> UserUiState(details) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UserUiState()
        )
}