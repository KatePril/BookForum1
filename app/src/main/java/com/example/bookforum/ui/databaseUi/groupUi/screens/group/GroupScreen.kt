package com.example.bookforum.ui.databaseUi.groupUi.screens.group

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.GroupBody
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.GroupReplyCanceller
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.bars.GroupBottomBar
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.bars.GroupTopBar
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupScreen(
    navigateToGroupsList: (Int) -> Unit,
    navigateToGroupSettings: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: GroupViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            GroupTopBar(
                navigateToGroupsList = { navigateToGroupsList(viewModel.userId) },
                navigateToGroupSettings = {
                    navigateToGroupSettings(viewModel.userId, viewModel.groupId)
                },
                quitAccount = quitAccount,
                navigateToFavouritePosts = { navigateToFavouritePosts(viewModel.userId) },
                navigateToProfile = { navigateToProfile(viewModel.userId) }
            )
        },
        bottomBar = {
            BottomAppBar {
                GroupBottomBar(
                    groupMessageCreationUiState = viewModel.groupMessageCreationUiState,
                    onValueChange = viewModel::updateUiState,
                    onSendClick = {
                        coroutineScope.launch {
                            viewModel.saveMessage()
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        GroupBody(
            currentUserId = viewModel.userId,
            getMessageById = viewModel::getReplyById,
            getUserById = viewModel::getUserById,
            onMessageClick = {
                viewModel.updateUiState(
                    viewModel.groupMessageCreationUiState.groupMessageDetails.copy(reply = it)
                )
            },
            onDeleteClick = {
                coroutineScope.launch {
                    viewModel.deleteMessage(it)
                }
            },
            onEditClick = viewModel::updateUiState,
            messagesList = viewModel.messagesList,
            contentPadding = innerPadding
        )

        if (viewModel.groupMessageCreationUiState.groupMessageDetails.reply != 0) {
            GroupReplyCanceller(
                getReplyById = viewModel::getReplyById,
                getReplySender = viewModel::getUserById,
                updateUiState = viewModel::updateUiState,
                groupMessageDetails = viewModel.groupMessageCreationUiState.groupMessageDetails
            )
        }
    }
}