package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.viewModels.PasswordChangeViewModel
import com.example.bookforum.ui.screenParts.BackButton

@Composable
fun PasswordChangeScreen(
    navigateBack: (Int) -> Unit,
    viewModel: PasswordChangeViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        BackButton(
            onClick = { navigateBack(viewModel.userId) }
        )
        PasswordChangeForm(
            passwordDetails = viewModel.passwordUiState.passwordDetails,
            onValueChange = viewModel::updateUiState
        )
        ChangePasswordButton(
            viewModel = viewModel,
            navigateBack = navigateBack
        )
    }
}