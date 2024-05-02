package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.states.PasswordDetails
import com.example.bookforum.ui.screenParts.FormInput

@Composable
internal fun PasswordChangeForm(
    passwordDetails: PasswordDetails,
    onValueChange: (PasswordDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInput(
            value = passwordDetails.oldPassword,
            onValueChange = { onValueChange(passwordDetails.copy(oldPassword = it)) },
            labelText = R.string.old_password_input,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
        FormInput(
            value = passwordDetails.newPassword,
            onValueChange = { onValueChange(passwordDetails.copy(newPassword = it)) },
            labelText = R.string.new_password_input,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
        )
    }
}