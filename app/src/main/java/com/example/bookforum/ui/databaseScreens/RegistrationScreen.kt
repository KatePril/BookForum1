package com.example.bookforum.ui.databaseScreens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.compose.BookForumTheme
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    viewModel: UserRegistrationViewModel = viewModel(factory = ForumViewModelProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()
    RegistrationBody(
        userUIState = viewModel.userUIState,
        onUserValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveUser()
            }
        },
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    )
}

@Composable
fun RegistrationBody(
    userUIState: UserUIState,
    onUserValueChange: (UserDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserInputForm(
            userDetails = userUIState.userDetails,
            onValueChange = onUserValueChange,
            modifier = modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = userUIState.isInputValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.sign_up_action))
        }
    }
}

@Composable
fun UserInputForm(
    userDetails: UserDetails,
    modifier: Modifier = Modifier,
    onValueChange: (UserDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        UserInput(
            value = userDetails.username,
            onValueChange = {username: String ->  onValueChange(userDetails.copy(username = username)) },
            labelText = R.string.username_input_label,
            enabled = enabled
        )
        UserInput(
            value = userDetails.password,
            onValueChange = {password: String ->  onValueChange(userDetails.copy(password = password)) },
            labelText = R.string.password_input_label,
            enabled = enabled
        )
        UserInput(
            value = userDetails.email,
            onValueChange = {email: String ->  onValueChange(userDetails.copy(email = email)) },
            labelText = R.string.email_input_label,
            enabled = enabled
        )
    }
}


@Composable
fun UserInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(labelText)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true
    )
    if (enabled) {
        Text(
            text = stringResource(R.string.required_field),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserInputFormPreview() {
    BookForumTheme {
        RegistrationBody(userUIState = UserUIState(), onUserValueChange = {}, onSaveClick = {})
    }
}
