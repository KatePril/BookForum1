package com.example.bookforum.ui.databaseUi.userUI.viewModels.utils

import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun getUserUiStateByUsername(
    username: String,
    usersRepository: UsersRepository,
    coroutineScope: CoroutineScope
) = usersRepository.getUserByUsername(username)
    .map { it?.toDetails()?.let { details -> UserUiState(details) } }
    .stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserUiState()
    )