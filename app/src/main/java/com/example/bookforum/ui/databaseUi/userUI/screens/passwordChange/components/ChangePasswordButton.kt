package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.bookforum.R
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

    if (wasButtonClicked && !isPasswordCorrect) {
        Text(
            text = stringResource(R.string.incorrect_old_password_msg),
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
    Button(
        onClick = {
            wasButtonClicked = true
            isPasswordCorrect = viewModel.checkPassword()
            if (isPasswordCorrect) {
                coroutineScope.launch {
                    viewModel.updateUser()
                }
            }
            navigateBack(viewModel.userId)
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.change_password_action))
    }
}