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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserRegistrationViewModel
import com.example.compose.BookForumTheme
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction2

@Composable
fun RegistrationScreen(
    navigateToPostsDisplayPage: (Int) -> Unit,
    viewModel: UserRegistrationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val usersUIState by viewModel.usersUIState.collectAsState()
    viewModel.userRegistrationUIState = viewModel.userRegistrationUIState.copy(usersList = usersUIState.usernameList)
    RegistrationBody(
        userRegistrationUIState = viewModel.userRegistrationUIState,
        usersList = usersUIState.usernameList,
        onUserValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveUser()
            }
            navigateToPostsDisplayPage(viewModel.userRegistrationUIState.userDetails.id)
            /*TODO check if the id passed is correct*/
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun RegistrationBody(
    userRegistrationUIState: UserRegistrationUIState,
    usersList: List<User>,
    onUserValueChange: KFunction2<UserDetails, List<User>, Unit>,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserRegistrationForm(
            userDetails = userRegistrationUIState.userDetails,
            userValidationDetails = userRegistrationUIState.userValidationDetails,
            usersList = usersList,
            onValueChange = onUserValueChange,
            modifier = modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = userRegistrationUIState.userValidationDetails.areInputsValid,
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
    usersList: List<User>,
    onValueChange: KFunction2<UserDetails, List<User>, Unit>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserRegistrationInput(
            value = userDetails.username,
            usersList = usersList,
            onValueChange = { username: String, users: List<User> ->  onValueChange(userDetails.copy(username = username), users) },
            labelText = R.string.username_input_label,
            msgText = R.string.invalid_username,
            isValid = userValidationDetails.isUsernameValid
        )
        UserRegistrationInput(
            value = userDetails.password,
            usersList = usersList,
            onValueChange = { password: String, users: List<User> ->  onValueChange(userDetails.copy(password = password), users) },
            labelText = R.string.password_input_label,
            msgText = R.string.invalid_password,
            isValid = userValidationDetails.isPasswordValid
        )
        UserRegistrationInput(
            value = userDetails.email,
            usersList = usersList,
            onValueChange = { email: String, users: List<User> ->  onValueChange(userDetails.copy(email = email), users) },
            labelText = R.string.email_input_label,
            msgText = R.string.invalid_email,
            isValid = userValidationDetails.isEmailValid
        )
    }
}


@Composable
private fun UserRegistrationInput(
    value: String,
    usersList: List<User>,
    onValueChange: (String, List<User>) -> Unit,
    @StringRes labelText: Int,
    @StringRes msgText: Int,
    isValid: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it, usersList) },
        label = { Text(text = stringResource(labelText)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        modifier = modifier.fillMaxWidth(),
        singleLine = true
    )
    Text(
        text = if (isValid) stringResource(R.string.required_field) else stringResource(msgText),
        modifier = modifier.padding(start = 8.dp),
        color = if (isValid) Color.Black else MaterialTheme.colorScheme.error
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserInputFormPreview() {
    BookForumTheme {
//        RegistrationBody(userUIState = UserUIState(), onUserValueChange = {}, onSaveClick = {})
    }
}
