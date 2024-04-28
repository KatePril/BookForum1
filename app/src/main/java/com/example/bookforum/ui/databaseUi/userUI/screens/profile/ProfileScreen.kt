package com.example.bookforum.ui.databaseUi.userUI.screens.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
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
            navigateBack(viewModel.registrationUIState.userDetails.id)
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
            onClick = onClick,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Composable
private fun ProfileForm(
    userDetails: UserDetails,
    userValidationDetails: UserValidationDetails,
    onInputValueChange: (UserDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        ProfileInput(
            value = userDetails.username,
            onValueChange = { onInputValueChange(userDetails.copy(username = it)) },
            labelText = R.string.username_profile_input,
            msgText = R.string.invalid_username,
            isValid = userValidationDetails.isUsernameValid
        )
        ProfileInput(
            value = userDetails.email,
            onValueChange = { onInputValueChange(userDetails.copy(email = it)) },
            labelText = R.string.email_input_label,
            msgText = R.string.invalid_email,
            isValid = userValidationDetails.isEmailValid
        )
        ProfileInput(
            value = userDetails.password,
            onValueChange = { onInputValueChange(userDetails.copy(password = it)) },
            labelText = R.string.password_input_label,
            msgText = R.string.invalid_password,
            isValid = userValidationDetails.isPasswordValid
        )
    }
}

@Composable
private fun ProfileInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    @StringRes msgText: Int,
    isValid: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(labelText)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier.fillMaxWidth(),
        singleLine = true
    )
    if (!isValid) {
        Text(
            text = stringResource(msgText),
            modifier = modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.error
        )
    }
}


@Composable
private fun DeleteButton(
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isAlertDialogOpened by remember {  mutableStateOf(false) }

    Button(
        onClick = { isAlertDialogOpened = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ),
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.delete_account_action))
    }

    if (isAlertDialogOpened) {
        AlertDeleteDialog(
            onDismissClick = { isAlertDialogOpened = false },
            onConfirmClick = {
                onDeleteClick()
                isAlertDialogOpened = false
            }
        )
    }
}

@Composable
private fun AlertDeleteDialog(
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null
            )
        },
        title = {
            Text(text = stringResource(R.string.delete_alert_title))
        },
        text = {
            Text(text = stringResource(R.string.delete_alert_text))
        },
        onDismissRequest = onConfirmClick,
        confirmButton = {
            TextButton(
                onClick = onConfirmClick
            ) {
                Text(text = stringResource(R.string.confirm_delete_action))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissClick
            ) {
                Text(text = stringResource(R.string.dismiss_delete_action))
            }
        }
    )
}
