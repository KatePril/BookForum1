package com.example.bookforum.ui.databaseUi.userUI.screens.logIn

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel
import com.example.bookforum.ui.screenParts.FormInput
import com.example.compose.BookForumTheme

@Composable
fun LoginScreen(
    navigateToRegistration: () -> Unit,
    navigateToPostsDisplayPage: (Int) -> Unit,
    viewModel: LoginViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    LogInBody(
        viewModel = viewModel,
        onValueChange = viewModel::updateUiState,
        onCreateAccountClick = navigateToRegistration,
        navigateToPostsDisplayPage = navigateToPostsDisplayPage
    )
}

@Composable
private fun LogInBody(
    viewModel: LoginViewModel,
    onValueChange: (UserLogInDetails) -> Unit,
    onCreateAccountClick: () -> Unit,
    navigateToPostsDisplayPage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserLogInForm(
            userLogInDetails = viewModel.userLogInUiState.userDetails,
            onValueChange = onValueChange
        )
        LogInButton(
            navigateToPostsDisplayPage = navigateToPostsDisplayPage,
            viewModel = viewModel
        )
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
private fun LogInButton(
    viewModel: LoginViewModel,
    navigateToPostsDisplayPage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val userUiState = viewModel.getUserUiStateByUsername(viewModel.userLogInUiState.userDetails.username).collectAsState()

    var isLogInSuccessful by remember { mutableStateOf(false) }
    var wasButtonClicked by remember { mutableStateOf(false) }
    
    if (wasButtonClicked && !isLogInSuccessful) {
        Text(
            text = stringResource(R.string.incorrect_log_in_message),
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
    Button(
        onClick = {
            wasButtonClicked = true
            if (userUiState.value != null) {
                isLogInSuccessful = viewModel.checkPassword(userUiState.value!!.userDetails.password)
                if (isLogInSuccessful) {
                    navigateToPostsDisplayPage(userUiState.value!!.userDetails.id)
                }
            }
        },
        enabled = viewModel.userLogInUiState.areInputsValid,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.log_in_action))
    }
}

@Composable
private fun UserLogInForm(
    userLogInDetails: UserLogInDetails,
    onValueChange: (UserLogInDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInput(
            value = userLogInDetails.username,
            onValueChange = {username: String -> onValueChange(userLogInDetails.copy(username = username))},
            labelText = R.string.username_input_label,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
        FormInput(
            value = userLogInDetails.password,
            onValueChange = {password: String -> onValueChange(userLogInDetails.copy(password = password))},
            labelText = R.string.password_input_label,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserLogInInputPreview() {
    BookForumTheme {
//        LoginScreen()
    }
}