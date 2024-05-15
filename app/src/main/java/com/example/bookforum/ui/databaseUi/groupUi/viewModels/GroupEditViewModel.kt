package com.example.bookforum.ui.databaseUi.groupUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.GroupMembersRepository
import com.example.bookforum.ui.navigation.destinations.groupDestinations.GroupEditDestination

class GroupEditViewModel(
    savedStateHandle: SavedStateHandle,
    groupMembersRepository: GroupMembersRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[GroupEditDestination.userIdArg]) 

    val groupId: Int = checkNotNull(savedStateHandle[GroupEditDestination.groupIdArg])

    var groupMembersList by mutableStateOf(emptyList<GroupMember>())
    var usersMap by mutableStateOf(emptyMap<Int, User>())
}