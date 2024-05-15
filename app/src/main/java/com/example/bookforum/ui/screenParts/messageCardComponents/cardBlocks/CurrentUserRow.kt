package com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bookforum.ui.screenParts.messageCardComponents.MessageButtons

@Composable
fun CurrentUserRow(
    onDeleteClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    messageCard: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = modifier.weight(0.5f))
        MessageButtons(
            onDeleteClick = onDeleteClick,
            onEditButtonClick = onEditButtonClick,
            modifier = modifier
        )
        messageCard()
    }
}