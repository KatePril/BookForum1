package com.example.bookforum.ui.databaseUi.userUI.screens.registration

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel
import com.example.compose.BookForumTheme
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
        UserRegistrationForm(
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

@Composable
private fun UserRegistrationForm(
    userDetails: UserDetails,
    userValidationDetails: UserValidationDetails,
    onValueChange: (UserDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserRegistrationInput(
            value = userDetails.username,
            onValueChange = { onValueChange(userDetails.copy(username = it)) },
            labelText = R.string.username_input_label,
            msgText = R.string.invalid_username,
            isValid = userValidationDetails.isUsernameValid
        )
        UserRegistrationInput(
            value = userDetails.password,
            onValueChange = { onValueChange(userDetails.copy(password = it)) },
            labelText = R.string.password_input_label,
            msgText = R.string.invalid_password,
            isValid = userValidationDetails.isPasswordValid
        )
        UserRegistrationInput(
            value = userDetails.email,
            onValueChange = { onValueChange(userDetails.copy(email = it)) },
            labelText = R.string.email_input_label,
            msgText = R.string.invalid_email,
            isValid = userValidationDetails.isEmailValid
        )
    }
}


@Composable
private fun UserRegistrationInput(
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
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserInputFormPreview() {
    BookForumTheme {
//        RegistrationBody(userUIState = UserUIState(), onUserValueChange = {}, onSaveClick = {})
    }
}