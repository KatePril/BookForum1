package com.example.bookforum.ui.databaseUi.groupUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.repositories.GroupMembersRepository
import com.example.bookforum.data.repositories.GroupsRepository
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.groupUi.states.GroupCreationUiState
import com.example.bookforum.ui.databaseUi.groupUi.states.GroupDetails
import com.example.bookforum.ui.databaseUi.groupUi.states.toGroup
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatsListViewModel

class GroupCreationViewModel(
    savedStateHandle: SavedStateHandle,
    private val groupsRepository: GroupsRepository,
    private val groupMembersRepository: GroupMembersRepository,
    val usersRepository: UsersRepository
): ChatsListViewModel(
    savedStateHandle = savedStateHandle,
    usersRepository = usersRepository
){
    private var groupMembers by mutableStateOf(emptySet<Int>())
    var groupCreationUiState by mutableStateOf(GroupCreationUiState())
    var groupId = 0

    fun addMember(memberId: Int) {
        groupMembers += memberId
    }

    fun updateUiState(groupDetails: GroupDetails) {
        groupCreationUiState = GroupCreationUiState(
            groupDetails = groupDetails,
            isTitleValid = validateTitle(groupDetails)
        )
    }

    suspend fun saveGroup() {
        if (validateTitle()) {
            groupId = groupsRepository
                .insertGroup(groupCreationUiState.groupDetails.toGroup()).toInt()
            groupMembersRepository.insertGroupMember(
                GroupMember(
                    id = 0,
                    groupId = groupId,
                    userId = userId,
                    isAdmin = 1
                )
            )
            for (id in groupMembers) {
                groupMembersRepository.insertGroupMember(
                    GroupMember(
                        id = 0,
                        groupId = groupId,
                        userId = id,
                        isAdmin = 0
                    )
                )
            }
        }
    }

    private fun validateTitle(groupDetails: GroupDetails = groupCreationUiState.groupDetails): Boolean {
        return with(groupDetails) {
            title.isNotBlank()
        }
    }
}