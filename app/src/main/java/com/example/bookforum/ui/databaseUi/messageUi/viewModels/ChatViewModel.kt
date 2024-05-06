package com.example.bookforum.ui.databaseUi.messageUi.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.MessagesRepository
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageCreationUiState
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.databaseUi.messageUi.states.toMessage
import com.example.bookforum.ui.navigation.destinations.ChatDestination
import com.example.bookforum.utils.getCurrentTime
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(
    savedStateHandle: SavedStateHandle,
    private val messagesRepository: MessagesRepository,
    private val usersRepository: UsersRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[ChatDestination.userIdArg])
    val receiverId: Int = checkNotNull(savedStateHandle[ChatDestination.receiverIdArg])

    lateinit var receiver: User
    lateinit var messagesList: List<Message>
    var messageCreationUiState by mutableStateOf(MessageCreationUiState())

    init {
        viewModelScope.launch {
            receiver = usersRepository
                .getUserById(receiverId)
                .stateIn(
                    scope = viewModelScope
                ).value
            messagesList = messagesRepository
                .getChatMessages(
                    currentUserId = userId,
                    receiverId = receiverId
                )
                .filterNotNull()
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(messageDetails: MessageDetails) {
        messageCreationUiState = MessageCreationUiState(
            messageDetails = messageDetails
                .copy(
                    date = getCurrentTime(),
                    senderId = userId,
                    receiverId = receiverId
                ),
            isTextValid = validateText(messageDetails)
        )
    }

    private fun validateText(
        messageDetails: MessageDetails = messageCreationUiState.messageDetails
    ): Boolean {
        return with(messageDetails) {
            text.isNotBlank()
        }
    }

    suspend fun saveMessage() {
        if (validateText()) {
            messagesRepository.insertMessage(messageCreationUiState.messageDetails.toMessage())
            updateUiState(messageCreationUiState.messageDetails.copy(text = ""))
        }
        messagesList = messagesRepository
            .getChatMessages(
                currentUserId = userId,
                receiverId = receiverId
            )
            .filterNotNull()
            .stateIn(
                scope = viewModelScope
            ).value
    }
}