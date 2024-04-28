package com.example.bookforum.ui.databaseUi.userUI.screens.logIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel
import com.example.bookforum.ui.screenParts.FormInput

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