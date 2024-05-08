package com.example.bookforum.ui.databaseUi.messageUi.screens.chat

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.ChatBody
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.bars.ChatBottomBar
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.bars.ChatTopBar
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatViewModel
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
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ChatScreenPreview() {
//    BookForumTheme {
//        ChatScreen(navigateToChatsList = {}, quitAccount = {}, navigateToFavouritePosts = {}, navigateToProfile = {})
//    }
//}