package com.example.bookforum.ui.databaseUi.messageUi.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.navigation.destinations.messageDestinations.ChatsListDestination
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatsListViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[ChatsListDestination.userIdArg])

    var contactsList = emptyList<User>()

    init {
        viewModelScope.launch {
            contactsList = usersRepository
                .getUserByNotId(userId)
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

}