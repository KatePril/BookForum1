package com.example.bookforum.ui.databaseUi.userUI.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.viewModels.ProfileViewModel
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navigateBack: (Int) -> Unit,
    navigateBackOnDelete: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    ProfileBody(
        userUiState = viewModel.registrationUIState,
        navigateBack = navigateBack,
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

@Composable
private fun ProfileBody(
    userUiState: UserRegistrationUIState,
    navigateBack: (Int) -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onInputValueChange: (UserDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        BackButton(
            onClick = { navigateBack(userUiState.userDetails.id) }
        )
        ProfileForm(
            userDetails = userUiState.userDetails,
            userValidationDetails = userUiState.userValidationDetails,
            onInputValueChange = onInputValueChange,
            modifier = modifier)
        Button(
            onClick = onUpdateClick,
            enabled = userUiState.userValidationDetails.areInputsValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.update_profile_action))
        }
        DeleteButton(
            onDeleteClick = onDeleteClick
        )
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        ButtonWithIcon(
            imageVector = Icons.Filled.ArrowBack,
            onClick = onClick
        )
    }
}