package com.example.bookforum.ui.databaseUi.userUI.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.screenParts.buttons.BackButton
import com.example.bookforum.ui.screenParts.buttons.DeleteButton

@Composable
internal fun ProfileBody(
    userUiState: UserRegistrationUIState,
    navigateBack: (Int) -> Unit,
    onChangePasswordClick: (Int) -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onInputValueChange: (UserDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        BackButton(
            onClick = { navigateBack(userUiState.userDetails.id) }
        )
        ProfileForm(
            userDetails = userUiState.userDetails,
            userValidationDetails = userUiState.userValidationDetails,
            onInputValueChange = onInputValueChange,
            modifier = modifier)
        Button(
            onClick = onUpdateClick,
            enabled = userUiState.userValidationDetails.areInputsValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.update_profile_action))
        }
        Button(
            onClick = { onChangePasswordClick(userUiState.userDetails.id) },
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(stringResource(R.string.change_password_action))
        }
        DeleteButton(
            buttonText = R.string.delete_account_action,
            onDeleteClick = onDeleteClick
        )
    }
}
