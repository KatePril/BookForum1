package com.example.bookforum.ui.screenParts.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopBarDefaultButtons(
    quitAccount: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonWithIcon(
        imageVector = Icons.Filled.Favorite,
        onClick = navigateToFavouritePosts,
        tint = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier
    )
    ButtonWithIcon(
        imageVector = Icons.Filled.Face,
        onClick = navigateToProfile,
        tint = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier
    )
    ButtonWithIcon(
        imageVector = Icons.Filled.ExitToApp,
        onClick = quitAccount,
        tint = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier
    )
}