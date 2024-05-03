package com.example.bookforum.ui.databaseUi.commentUi.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.screenParts.FormInputWithMessage
import com.example.compose.BookForumTheme

@Composable
internal fun CommentCreationForm(
    commentCreationUiState: CommentCreationUiState,
    onValueChange: (CommentDetails) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .padding(end = dimensionResource(R.dimen.padding_large))
                .weight(2f)
        ) {
            FormInputWithMessage(
                value = commentCreationUiState.commentDetails.text,
                onValueChange = {onValueChange(commentCreationUiState.commentDetails.copy(text = it))},
                labelText = R.string.comment_input,
                msgText = R.string.invalid_comment,
                color = MaterialTheme.colorScheme.primaryContainer,
                singleLine = false
            )
        }
        Column (
            modifier = modifier
        ) {
            IconButton(
                onClick = onSendClick,
                modifier = modifier
                    .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommentCreationFormPreview() {
    BookForumTheme {
        CommentCreationForm(commentCreationUiState = CommentCreationUiState(), {}, {})
    }
}