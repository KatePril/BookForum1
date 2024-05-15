package com.example.bookforum.ui.databaseUi.groupUi.screens.editGroup

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.groupUi.screens.components.BottomBarButton
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.bars.GroupTopBar
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupEditViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditGroupScreen(
    navigateToGroup: (Int) -> Unit,
    navigateToGroupSettings: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    navigateToGroupsList: (Int) -> Unit,
    viewModel: GroupEditViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            GroupTopBar(
                navigateToGroupsList = { navigateToGroup(viewModel.userId) },
                navigateToGroupSettings = {
                    navigateToGroupSettings(viewModel.userId, viewModel.groupId)
                },
                quitAccount = quitAccount,
                navigateToFavouritePosts = { navigateToFavouritePosts(viewModel.userId) },
                navigateToProfile = { navigateToProfile(viewModel.userId) }
            )
        },
        bottomBar = {
            BottomBarButton(
                onCLick = {
                    coroutineScope.launch {
                        viewModel.leaveGroup()
                    }
                    navigateToGroupsList(viewModel.userId)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = null
                )
            }
        }
    ) {
//        Icons.Filled.AddModerator for edit rights
//        Icons.Filled.ExitToApp for delete user
    }
}