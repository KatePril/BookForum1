package com.example.bookforum.ui.databaseUi.userUI.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.screenParts.FormInputWithMessage

@Composable
internal fun RegistrationForm(
    userDetails: UserDetails,
    userValidationDetails: UserValidationDetails,
    onValueChange: (UserDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInputWithMessage(
            value = userDetails.username,
            onValueChange = { onValueChange(userDetails.copy(username = it)) },
            labelText = R.string.username_input_label,
            msgText = R.string.invalid_username,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            isValid = userValidationDetails.isUsernameValid
        )
        FormInputWithMessage(
            value = userDetails.password,
            onValueChange = { onValueChange(userDetails.copy(password = it)) },
            labelText = R.string.password_input_label,
            msgText = R.string.invalid_password,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            isValid = userValidationDetails.isPasswordValid
        )
        FormInputWithMessage(
            value = userDetails.email,
            onValueChange = { onValueChange(userDetails.copy(email = it)) },
            labelText = R.string.email_input_label,
            msgText = R.string.invalid_email,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            isValid = userValidationDetails.isEmailValid
        )
    }
}