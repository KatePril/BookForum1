package com.example.bookforum.ui.databaseUi.messageUi.screens.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.ChatBody
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.bars.ChatBottomBar
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.bars.ChatTopBar
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.ReplyCanceller
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatViewModel
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    navigateToChatsList: (Int) -> Unit,
    quitAccount: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: ChatViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ChatTopBar(
                navigateToChatsList = { navigateToChatsList(viewModel.userId) },
                quitAccount = { quitAccount(viewModel.userId) },
                navigateToFavouritePosts = { navigateToFavouritePosts(viewModel.userId) },
                navigateToProfile = { navigateToProfile(viewModel.userId) },
            )
        },
        bottomBar = {
            BottomAppBar {
                ChatBottomBar(
                    getMessageById = viewModel::getReplyById,
                    getSenderById = viewModel::getReplySender,
                    messageCreationUiState = viewModel.messageCreationUiState,
                    onValueChange = viewModel::updateUiState,
                    onSendClick = {
                        coroutineScope.launch {
                            viewModel.saveMessage()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        ChatBody(
            onMessageClick = {
                 viewModel.updateUiState(
                     viewModel.messageCreationUiState.messageDetails.copy(reply = it)
                 )
            },
            onDeleteClick = {
                coroutineScope.launch {
                    viewModel.deleteMessage(it)
                }
            },
            onEditButtonClick = viewModel::updateUiState,
            receiver = viewModel.receiver,
            messagesList = viewModel.messagesList,
            contentPadding = innerPadding,
            getMessageById = viewModel::getReplyById,
            getSenderById = viewModel::getReplySender
        )

        if (viewModel.messageCreationUiState.messageDetails.reply != 0) {
            ReplyCanceller(
                getReplyById = viewModel::getReplyById,
                getReplySender = viewModel::getReplySender,
                updateUiState = viewModel::updateUiState,
                messageDetails = viewModel.messageCreationUiState.messageDetails
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ChatScreenPreview() {
//    BookForumTheme {
//        ChatScreen(navigateToChatsList = {}, quitAccount = {}, navigateToFavouritePosts = {}, navigateToProfile = {})
//    }
//}