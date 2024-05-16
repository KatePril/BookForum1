package com.example.bookforum.ui.databaseUi.userUI.screens.registration.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel

@Composable
internal fun SaveUserButton(
    viewModel: RegistrationViewModel,
    navigateToFeedPage: (Int) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isSaved by remember { mutableStateOf(false) }

    if (isSaved) {
        Button(
            onClick = { navigateToFeedPage(viewModel.userId) },
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
