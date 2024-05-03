package com.example.bookforum.ui.databaseUi.userUI.screens.registration.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.screens.registration.RegistrationForm
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    navigateToFeedPage: (Int) -> Unit,
    onCancelClick: () -> Unit,
    viewModel: RegistrationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    RegistrationBody(
        viewModel = viewModel,
        navigateToFeed = navigateToFeedPage,
        onInputValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveUser()
            }
        },
        onCancelClick = onCancelClick,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun RegistrationBody(
    viewModel: RegistrationViewModel,
    navigateToFeed: (Int) -> Unit,
    onInputValueChange: (UserDetails) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        RegistrationForm(
            userDetails = viewModel.registrationUIState.userDetails,
            userValidationDetails = viewModel.registrationUIState.userValidationDetails,
            onValueChange = onInputValueChange,
            modifier = modifier.fillMaxWidth()
        )
        SaveUserButton(
            viewModel = viewModel,
            navigateToFeedPage = navigateToFeed,
            onSaveClick = onSaveClick
        )
        Button(
            onClick = onCancelClick,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.cancel_action))
        }
    }
}
