package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.userUI.states.PasswordDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.PasswordChangeViewModel
import com.example.bookforum.ui.screenParts.BackButton
import com.example.bookforum.ui.screenParts.FormInput
import kotlinx.coroutines.launch

@Composable
fun PasswordChangeScreen(
    navigateBack: (Int) -> Unit,
    viewModel: PasswordChangeViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
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