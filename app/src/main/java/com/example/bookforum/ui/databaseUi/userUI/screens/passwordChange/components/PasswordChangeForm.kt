package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.states.PasswordDetails
import com.example.bookforum.ui.screenParts.forms.FormInput

@Composable
internal fun PasswordChangeForm(
    passwordDetails: PasswordDetails,
    onValueChange: (PasswordDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        FormInput(
            value = passwordDetails.oldPassword,
            onValueChange = { onValueChange(passwordDetails.copy(oldPassword = it)) },
            labelText = R.string.old_password_input,
            color = MaterialTheme.colorScheme.primaryContainer,
            imeAction = ImeAction.Next,
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