package com.example.bookforum.ui.databaseUi.userUI.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    navigateToFeedPage: (Int) -> Unit,
    onCancelClick: () -> Unit,
    viewModel: RegistrationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    RegistrationBody(
        viewModel = viewModel,
        navigateToFeed = navigateToFeedPage,
        onInputValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveUser()
            }
        },
        onCancelClick = onCancelClick,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun RegistrationBody(
    viewModel: RegistrationViewModel,
    navigateToFeed: (Int) -> Unit,
    onInputValueChange: (UserDetails) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        RegistrationForm(
            userDetails = viewModel.registrationUIState.userDetails,
            userValidationDetails = viewModel.registrationUIState.userValidationDetails,
            onValueChange = onInputValueChange,
            modifier = modifier.fillMaxWidth()
        )
        SaveUserButton(
            viewModel = viewModel,
            navigateToFeedPage = navigateToFeed,
            onSaveClick = onSaveClick
        )
        Button(
            onClick = onCancelClick,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.cancel_action))
        }
    }
}

@Composable
private fun SaveUserButton(
    viewModel: RegistrationViewModel,
    navigateToFeedPage: (Int) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userUiState = viewModel.getUserUiStateByUsername(viewModel.registrationUIState.userDetails.username).collectAsState()

    var isSaved by remember { mutableStateOf(false) }

    if (isSaved) {
        Button(
            onClick = {
                if (userUiState.value != null) {
                    navigateToFeedPage(userUiState.value!!.userDetails.id)
                }
            },
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.continue_action))
        }
    } else {
        Button(
            onClick = {
                onSaveClick()
                isSaved = true
            },
            enabled = viewModel.registrationUIState.userValidationDetails.areInputsValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.sign_up_action))
        }
    }
}

