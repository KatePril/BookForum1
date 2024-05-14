package com.example.bookforum.ui.screenParts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.ui.screenParts.buttons.ButtonWithIcon
import com.example.bookforum.ui.screenParts.buttons.TopBarDefaultButtons
import com.example.bookforum.ui.screenParts.topBars.ForumTopBar
import com.example.bookforum.ui.theme.BookForumTheme

@Composable
fun ForumTopAppBar (
    quitAccount: () -> Unit,
    navigateToGlobalPage: () -> Unit,
    navigateToChatsList: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    ForumTopBar {
        TitleBody(
            quitAccount = quitAccount,
            navigateToGlobalPage = navigateToGlobalPage,
            navigateToChatsList = navigateToChatsList,
            navigateToFavouritePosts = navigateToFavouritePosts,
            navigateToProfile = navigateToProfile,
            modifier = modifier
        )
    }
}

@Composable
private fun TitleBody(
    quitAccount: () -> Unit,
    navigateToGlobalPage: () -> Unit,
    navigateToChatsList: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(end = 16.dp)
    ) {
        Button(
            onClick = navigateToGlobalPage,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = stringResource(R.string.logo),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = modifier.weight(0.5f))
        ButtonWithIcon(
            imageVector = Icons.Filled.Chat,
            onClick = navigateToChatsList,
            tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
        )
        TopBarDefaultButtons(
            quitAccount = quitAccount,
            navigateToFavouritePosts = navigateToFavouritePosts,
            navigateToProfile = navigateToProfile,
            modifier = modifier
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ForumTopAppBarPreview() {
    BookForumTheme {
//        ForumTopAppBar({}, {}, {})
    }
}