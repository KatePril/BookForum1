package com.example.bookforum.ui.databaseUi.userUI.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.viewModels.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navigateBack: (Int) -> Unit,
    onChangePasswordClick: (Int) -> Unit,
    navigateBackOnDelete: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    ProfileBody(
        userUiState = viewModel.registrationUIState,
        navigateBack = navigateBack,
        onChangePasswordClick = onChangePasswordClick,
        onUpdateClick = {
            coroutineScope.launch {
                viewModel.updateUser()
            }
        },
        onDeleteClick = {
            coroutineScope.launch {
                viewModel.deleteUser()
            }
            navigateBackOnDelete()
        },
        onInputValueChange = viewModel::updateUiState
    )
}