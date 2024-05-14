package com.example.bookforum.ui.databaseUi.commentUi.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.screenParts.forms.FormInputWithMessage
import com.example.bookforum.ui.screenParts.forms.TextInputForm
import com.example.bookforum.ui.theme.BookForumTheme

@Composable
internal fun CommentCreationForm(
    commentCreationUiState: CommentCreationUiState,
    onValueChange: (CommentDetails) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextInputForm(
        enabled = commentCreationUiState.isTextValid,
        onSendClick = onSendClick,
        modifier = modifier
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommentCreationFormPreview() {
    BookForumTheme {
        CommentCreationForm(commentCreationUiState = CommentCreationUiState(), {}, {})
    }
}