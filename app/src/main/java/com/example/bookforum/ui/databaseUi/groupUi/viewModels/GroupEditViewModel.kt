package com.example.bookforum.ui.databaseUi.groupUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.utils.MemberRight
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.GroupMembersRepository
import com.example.bookforum.ui.navigation.destinations.groupDestinations.GroupEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroupEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val groupMembersRepository: GroupMembersRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[GroupEditDestination.userIdArg]) 

    val groupId: Int = checkNotNull(savedStateHandle[GroupEditDestination.groupIdArg])

    var groupMembersList by mutableStateOf(emptyList<GroupMember>())
    private var usersMap by mutableStateOf(emptyMap<Int, User>())
    var currentUserRights by mutableStateOf(0)

    init {
        viewModelScope.launch {
            updateUsers()
            currentUserRights = groupMembersRepository
                .getGroupMemberByUserId(userId)
                .filterNotNull()
                .stateIn(
                    scope = viewModelScope
                ).value.isAdmin
        }
    }

    private suspend fun updateUsers() {
        groupMembersList = groupMembersRepository
            .getGroupMembersByGroupId(groupId, userId)
            .filterNotNull()
            .stateIn(scope = viewModelScope)
            .value
        usersMap = groupMembersRepository
            .getUsersByGroupId(groupId)
            .filterNotNull()
            .stateIn(
                scope = viewModelScope
            ).value.associateBy { it.id }
    }
    suspend fun deleteMember(groupMember: GroupMember) {
        groupMembersRepository.deleteGroupMember(groupMember)
        updateUsers()
    }

    suspend fun updateRights(groupMember: GroupMember) {
        if (groupMember.isAdmin != MemberRight.OWNER.value) {
            groupMembersRepository.updateGroupMember(
                groupMember.copy(
                    isAdmin = if (groupMember.isAdmin == MemberRight.NOT_ADMIN.value) {
                        MemberRight.ADMIN.value
                    } else {
                        MemberRight.NOT_ADMIN.value
                    }
                )
            )
        }
    }

    suspend fun leaveGroup() {
        groupMembersRepository.deleteGroupMember(
            groupMembersRepository
                .getGroupMemberByUserId(userId)
                .filterNotNull()
                .stateIn(
                    scope = viewModelScope
                ).value
        )
    }

    fun getUser(id: Int): User? = usersMap[id]
}