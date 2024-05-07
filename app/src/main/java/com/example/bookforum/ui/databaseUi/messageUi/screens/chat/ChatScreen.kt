package com.example.bookforum.ui.databaseUi.messageUi.screens.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.ChatBody
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.ChatBottomBar
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.ChatTopBar
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatViewModel
import com.example.bookforum.ui.screenParts.FormInputWithMessage
import com.example.bookforum.ui.theme.BookForumTheme
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
            receiver = viewModel.receiver,
            messagesList = viewModel.messagesList,
            contentPadding = innerPadding
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    BookForumTheme {
        ChatScreen(navigateToChatsList = {}, quitAccount = {}, navigateToFavouritePosts = {}, navigateToProfile = {})
    }
}