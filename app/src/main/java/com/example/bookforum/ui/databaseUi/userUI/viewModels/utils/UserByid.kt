package com.example.bookforum.ui.databaseUi.userUI.viewModels.utils

import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.postsUI.states.UserByIdUiState
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun getUserUiStateById(
    userId: Int,
    usersRepository: UsersRepository,
    coroutineScope: CoroutineScope
) : StateFlow<UserByIdUiState> = usersRepository.getUserById(userId)
        .filterNotNull()
        .map { UserByIdUiState(it) }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
            initialValue = UserByIdUiState()
        )