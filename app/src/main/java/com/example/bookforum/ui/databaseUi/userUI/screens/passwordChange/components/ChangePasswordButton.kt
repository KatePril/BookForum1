package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.screens.components.FullLineButton
import com.example.bookforum.ui.databaseUi.userUI.viewModels.PasswordChangeViewModel
import kotlinx.coroutines.launch

@Composable
internal fun ChangePasswordButton(
    viewModel: PasswordChangeViewModel,
    navigateBack: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    var isPasswordCorrect by remember { mutableStateOf(false) }
    var wasButtonClicked by remember { mutableStateOf(false) }

    FullLineButton(
        msgTextId = R.string.incorrect_old_password_msg,
        buttonTextId = R.string.change_password_action,
        isMsgShown = (wasButtonClicked && !isPasswordCorrect),
        onClick = {
            wasButtonClicked = true
            isPasswordCorrect = viewModel.checkPassword()
            if (isPasswordCorrect) {
                coroutineScope.launch {
                    viewModel.updateUser()
                }
                navigateBack(viewModel.userId)
            }
        },
        enabled = viewModel.passwordUiState.areInputsValid,
        modifier = modifier
    )
}