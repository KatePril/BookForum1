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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel
import com.example.bookforum.ui.screenParts.forms.FormInput

@Composable
fun LoginScreen(
    navigateToRegistration: () -> Unit,
    navigateToPostsDisplayPage: (Int) -> Unit,
    viewModel: LoginViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    LogInBody(
        viewModel = viewModel,
        onCreateAccountClick = navigateToRegistration,
        navigateToPostsDisplayPage = navigateToPostsDisplayPage
    )
}

@Composable
private fun LogInBody(
    viewModel: LoginViewModel,
    onCreateAccountClick: () -> Unit,
    navigateToPostsDisplayPage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        LogInForm(
            userLogInDetails = viewModel.userLogInUiState.userDetails,
            onValueChange = viewModel::updateUiState
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
private fun LogInForm(
    userLogInDetails: UserLogInDetails,
    onValueChange: (UserLogInDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        FormInput(
            value = userLogInDetails.username,
            onValueChange = { onValueChange(userLogInDetails.copy(username = it))},
            labelText = R.string.username_input_label,
            color = MaterialTheme.colorScheme.primaryContainer,
            imeAction = ImeAction.Next,
            modifier = modifier
        )
        FormInput(
            value = userLogInDetails.password,
            onValueChange = { onValueChange(userLogInDetails.copy(password = it))},
            labelText = R.string.password_input_label,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
    }
}