package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookforum.ui.screenParts.ButtonWithIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    navigateToChatsList: () -> Unit,
    quitAccount: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            TitleBody(
                navigateToChatsList = navigateToChatsList,
                quitAccount = quitAccount,
                navigateToFavouritePosts = navigateToFavouritePosts,
                navigateToProfile = navigateToProfile,
                modifier = modifier
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Composable
private fun TitleBody(
    navigateToChatsList: () -> Unit,
    quitAccount: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(end = 16.dp)
    ) {
        ButtonWithIcon(
            imageVector = Icons.Filled.ArrowBack,
            onClick = navigateToChatsList,
            tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
        )
        Spacer(modifier = modifier.weight(1f))
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
}