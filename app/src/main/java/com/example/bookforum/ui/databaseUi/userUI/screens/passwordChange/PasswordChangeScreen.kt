package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.PasswordDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.PasswordChangeViewModel
import com.example.bookforum.ui.screenParts.BackButton
import com.example.bookforum.ui.screenParts.FormInput
import kotlinx.coroutines.launch

@Composable
fun PasswordChangeScreen(
    navigateBack: (Int) -> Unit,
    viewModel: PasswordChangeViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BackButton(
            onClick = { navigateBack(viewModel.userId) }
        )
        PasswordChangeForm(
            passwordDetails = viewModel.passwordUiState.passwordDetails,
            onValueChange = viewModel::updateUiState
        )
        ChangePasswordButton(
            viewModel = viewModel,
            navigateBack = navigateBack
        )
    }
}

@Composable
internal fun PasswordChangeForm(
    passwordDetails: PasswordDetails,
    onValueChange: (PasswordDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInput(
            value = passwordDetails.oldPassword,
            onValueChange = { onValueChange(passwordDetails.copy(oldPassword = it)) },
            labelText = R.string.old_password_input,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
        FormInput(
            value = passwordDetails.newPassword,
            onValueChange = { onValueChange(passwordDetails.copy(newPassword = it)) },
            labelText = R.string.new_password_input,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
    }
}

@Composable
internal fun ChangePasswordButton(
    viewModel: PasswordChangeViewModel,
    navigateBack: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    var isPasswordCorrect by remember { mutableStateOf(false) }
    var wasButtonClicked by remember { mutableStateOf(false) }

    if (wasButtonClicked && !isPasswordCorrect) {
        Text(
            text = stringResource(R.string.incorrect_old_password_msg),
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
    Button(
        onClick = {
            wasButtonClicked = true
            isPasswordCorrect = viewModel.checkPassword()
            if (isPasswordCorrect) {
                coroutineScope.launch {
                    viewModel.updateUser()
                }
            }
            navigateBack(viewModel.userId)
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.change_password_action))
    }
}