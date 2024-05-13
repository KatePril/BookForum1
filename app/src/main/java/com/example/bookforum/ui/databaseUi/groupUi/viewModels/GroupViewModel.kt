package com.example.bookforum.ui.databaseUi.groupUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.GroupMembersRepository
import com.example.bookforum.data.repositories.GroupMessageRepository
import com.example.bookforum.ui.navigation.destinations.groupDestination.GroupDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroupViewModel(
    savedStateHandle: SavedStateHandle,
    private val groupMessageRepository: GroupMessageRepository,
    private val groupMembersRepository: GroupMembersRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[GroupDestination.userIdArg])
    val groupId: Int = checkNotNull(savedStateHandle[GroupDestination.groupIdArg])
    var usersMap by mutableStateOf(emptyMap<Int, User>())
    var messagesList by mutableStateOf(emptyList<GroupMessage>())
    init {
        viewModelScope.launch {
            usersMap = groupMembersRepository
                .getUsersByGroupId(groupId)
                .filterNotNull()
                .stateIn(
                    scope = viewModelScope
                ).value.associateBy { it.id }
            messagesList = groupMessageRepository
                .getGroupMessagesByGroupId(groupId)
                .filterNotNull().stateIn(
                    scope = viewModelScope
                ).value
        }

    }
}