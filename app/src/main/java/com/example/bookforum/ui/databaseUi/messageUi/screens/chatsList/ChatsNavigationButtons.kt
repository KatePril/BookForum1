package com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.bookforum.R

@Composable
fun ChatsNavigationButtons(
    navigateToPrivateChats: () -> Unit,
    navigateToGroups: () -> Unit,
    privateChatsColor: Color,
    groupColor: Color,
    modifier: Modifier =Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = navigateToPrivateChats,
            colors = ButtonDefaults.buttonColors(
                containerColor = privateChatsColor
            ),
            modifier = modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.go_to_private_chats_action))
        }
        Button(
            onClick =navigateToGroups,
            colors = ButtonDefaults.buttonColors(
                containerColor = groupColor
            ),
            modifier = modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.go_to_groups_action))
        }
    }
}