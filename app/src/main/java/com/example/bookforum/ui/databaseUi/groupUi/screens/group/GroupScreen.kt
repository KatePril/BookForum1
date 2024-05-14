package com.example.bookforum.ui.databaseUi.groupUi.screens.group

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.bars.GroupBottomBar
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.bars.GroupTopBar
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
    ) { innerPadding ->
        /* TODO Reply Canceller */
    }
}