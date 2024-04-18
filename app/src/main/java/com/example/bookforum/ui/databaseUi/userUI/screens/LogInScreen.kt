package com.example.bookforum.ui.databaseUi.userUI.screens

import android.util.Log
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInUiState
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserLoginViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UsersViewModel
import com.example.compose.BookForumTheme

@Composable
fun LoginScreen(
    viewModel: UserLoginViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    var isLogInSuccessful by remember { mutableStateOf(false) }
    val userUiState = viewModel.userUiState.collectAsState()
    LogInBody(
        userLogInUiState = viewModel.userLogInUiState,
        onValueChange = viewModel::updateUiState,
        onLogInClick = {
            viewModel.updateUserUiState(viewModel.userLogInUiState.userDetails.username)
//            val correctPassword = viewModel.getUserUiState(viewModel.userLogInUiState.userDetails.username).value?.userDetails?.password
//            Log.i("LOG_IN_RES", correctPassword.toString())
            if (userUiState.value != null) {
                isLogInSuccessful = viewModel.checkPassword(userUiState.value!!.userDetails.password)
            }
        },
        onCreateAccountClick = {},
        isLogInSuccessful = isLogInSuccessful
    )
}

@Composable
fun LogInBody(
    userLogInUiState: UserLogInUiState,
    onValueChange: (UserLogInDetails) -> Unit,
    onLogInClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    isLogInSuccessful: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserLogInForm(
            userLogInDetails = userLogInUiState.userDetails,
            onValueChange = onValueChange
        )
        Text(text = if (isLogInSuccessful) "Success" else "Incorrect username or password")
        Button(
            onClick = onLogInClick,
            enabled = userLogInUiState.areInputsValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.log_in_action))
        }
        Button(
            onClick = onCreateAccountClick,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.create_account_action))
        }
    }

}

@Composable
fun UserLogInForm(
    userLogInDetails: UserLogInDetails,
    onValueChange: (UserLogInDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserLogInInput(
            value = userLogInDetails.username,
            onValueChange = {username: String -> onValueChange(userLogInDetails.copy(username = username))},
            labelText = R.string.username_input_label,
            modifier = modifier
        )
        UserLogInInput(
            value = userLogInDetails.password,
            onValueChange = {password: String -> onValueChange(userLogInDetails.copy(password = password))},
            labelText = R.string.password_input_label,
            modifier = modifier
        )
    }
}

@Composable
fun UserLogInInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserLogInInputPreview() {
    BookForumTheme {
        LoginScreen()
    }
}