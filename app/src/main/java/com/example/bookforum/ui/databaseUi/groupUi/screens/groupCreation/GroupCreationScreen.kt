package com.example.bookforum.ui.databaseUi.groupUi.screens.groupCreation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.groupUi.states.GroupDetails
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupCreationViewModel
import com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList.ChatsList
import com.example.bookforum.ui.screenParts.BackButton
import com.example.bookforum.ui.screenParts.FormInput
import kotlinx.coroutines.launch

@Composable
fun GroupCreationScreen(
    navigateBack: (Int) -> Unit,
    navigateToChat: (Int, Int) -> Unit,
    viewModel: GroupCreationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveGroup()
                    }
                    navigateToChat(viewModel.userId, viewModel.groupId)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save_group_action))
            }
        }
    ) { innerPadding ->
               GroupCreationBody(
                   navigateBack = { navigateBack(viewModel.userId) },
                   groupDetails = viewModel.groupCreationUiState.groupDetails,
                   onValueChange = viewModel::updateUiState,
                   onItemClick = viewModel::addMember,
                   usersList = viewModel.contactsList,
                   modifier = Modifier.padding(innerPadding)
               )
    }
}

@Composable
fun GroupCreationBody(
    navigateBack: () -> Unit,
    groupDetails: GroupDetails,
    onValueChange: (GroupDetails) -> Unit,
    onItemClick: (Int) -> Unit,
    usersList: List<User>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        BackButton(onClick = navigateBack)
        FormInput(
            value = groupDetails.title,
            onValueChange = { onValueChange(groupDetails.copy(title =  it)) },
            labelText = R.string.group_title_input,
            color = MaterialTheme.colorScheme.primary
        )
        ChatsList(
            onItemClick = onItemClick,
            usersList = usersList
        )
    }
}