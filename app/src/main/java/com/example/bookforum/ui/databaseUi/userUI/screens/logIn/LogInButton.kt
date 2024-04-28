package com.example.bookforum.ui.databaseUi.userUI.screens.logIn

import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel

@Composable
internal fun LogInButton(
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