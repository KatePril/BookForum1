package com.example.bookforum.ui.databaseUi.groupUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.GroupMessageRepository
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageCreationUiState
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageDetails
import com.example.bookforum.ui.databaseUi.groupUi.states.group.toGroupMessage
import com.example.bookforum.ui.navigation.destinations.groupDestinations.GroupDestination
import com.example.bookforum.utils.getCurrentTime
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroupViewModel(
    savedStateHandle: SavedStateHandle,
    private val groupMessageRepository: GroupMessageRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[GroupDestination.userIdArg])
    val groupId: Int = checkNotNull(savedStateHandle[GroupDestination.groupIdArg])

    private var usersMap by mutableStateOf(emptyMap<Int, User>())
    private var messagesMap by mutableStateOf(emptyMap<Int, GroupMessage>())

    var messagesList by mutableStateOf(emptyList<GroupMessage>())
        private set
    var groupMessageCreationUiState by mutableStateOf(GroupMessageCreationUiState())
        private set

    init {
        viewModelScope.launch {
            usersMap = groupMessageRepository
                .getGroupUsersByGroupId(groupId)
                .filterNotNull()
                .stateIn(
                    scope = viewModelScope
                ).value.associateBy { it.id }
            fillMessages()
        }
    }

    private suspend fun fillMessages() {
        messagesList = groupMessageRepository
            .getGroupMessagesByGroupId(groupId)
            .filterNotNull().stateIn(
                scope = viewModelScope
            ).value
        messagesMap = messagesList.associateBy { it.id }
    }


    fun updateUiState(groupMessageDetails: GroupMessageDetails) {
        groupMessageCreationUiState = GroupMessageCreationUiState(
            groupMessageDetails = groupMessageDetails.copy(senderId = userId, groupId = groupId),
            isTextValid = validateText(groupMessageDetails)
        )
    }

    private fun validateText(
        groupMessageDetails: GroupMessageDetails = groupMessageCreationUiState.groupMessageDetails
    ): Boolean {
        return with(groupMessageDetails) {
            text.isNotBlank()
        }
    }

    suspend fun saveMessage() {
        if (validateText()) {
            if (groupMessageCreationUiState.groupMessageDetails.id == 0) {
                groupMessageRepository.insertGroupMessage(
                    groupMessageCreationUiState.groupMessageDetails
                        .copy(date = getCurrentTime())
                        .toGroupMessage()
                )
            } else {
                groupMessageRepository.updateGroupMessage(
                    groupMessageCreationUiState.groupMessageDetails
                        .copy(edited = 1)
                        .toGroupMessage()
                )
            }
            updateUiState(groupMessageCreationUiState.groupMessageDetails.copy(text = "", reply = 0))

            fillMessages()
        }
    }

    suspend fun deleteMessage(groupMessage: GroupMessage) {
        groupMessageRepository.deleteGroupMessage(groupMessage)
        fillMessages()
    }

    fun getReplyById(id: Int): GroupMessage? = messagesMap[id]
    fun getUserById(id: Int): User? = usersMap[id]
}