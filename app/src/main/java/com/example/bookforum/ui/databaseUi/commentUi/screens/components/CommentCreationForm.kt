package com.example.bookforum.ui.databaseUi.commentUi.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.screenParts.forms.FormInputWithMessage
import com.example.bookforum.ui.screenParts.buttons.SendButton
import com.example.bookforum.ui.theme.BookForumTheme

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
            SendButton(
                onSendClick = onSendClick,
                enabled = commentCreationUiState.isTextValid,
                modifier = modifier
            )
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