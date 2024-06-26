package com.example.bookforum.ui.databaseUi.userUI.screens.logIn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.screens.components.FullLineButton
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel

@Composable
internal fun LogInButton(
    viewModel: LoginViewModel,
    navigateToPostsDisplayPage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val userUiState = viewModel.getUserUiStateByUsername().collectAsState()

    var isLogInSuccessful by remember { mutableStateOf(false) }
    var wasButtonClicked by remember { mutableStateOf(false) }

    FullLineButton(
        msgTextId = R.string.incorrect_log_in_message,
        buttonTextId = R.string.log_in_action,
        isMsgShown = (wasButtonClicked && !isLogInSuccessful),
        onClick = {
            wasButtonClicked = true
            isLogInSuccessful = viewModel.checkPassword(
                correctPassword = userUiState.value.password,
                salt = userUiState.value.salt
            )
            if (isLogInSuccessful) {
                navigateToPostsDisplayPage(userUiState.value.id)
            }
        },
        enabled = viewModel.userLogInUiState.areInputsValid,
        modifier = modifier
    )
}