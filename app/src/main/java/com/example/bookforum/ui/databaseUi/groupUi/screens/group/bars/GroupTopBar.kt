package com.example.bookforum.ui.databaseUi.groupUi.screens.group.bars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.ui.screenParts.buttons.ButtonWithIcon
import com.example.bookforum.ui.screenParts.buttons.TopBarDefaultButtons
import com.example.bookforum.ui.screenParts.topBars.ForumTopBar

@Composable
fun GroupTopBar(
    navigateToGroupsList: () -> Unit,
    navigateToGroupSettings: () -> Unit,
    quitAccount: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    ForumTopBar {
        TitleBody(
            navigateToGroupsList = navigateToGroupsList,
            navigateToGroupSettings = navigateToGroupSettings,
            quitAccount = quitAccount,
            navigateToFavouritePosts = navigateToFavouritePosts,
            navigateToProfile = navigateToProfile,
            modifier = modifier
        )
    }
}

@Composable
private fun TitleBody(
    navigateToGroupsList: () -> Unit,
    navigateToGroupSettings: () -> Unit,
    quitAccount: () -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(end = dimensionResource(R.dimen.padding_large))
    ) {
        ButtonWithIcon(
            imageVector = Icons.Filled.ArrowBack,
            onClick = navigateToGroupsList,
            tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
        )
        Spacer(modifier = modifier.weight(1f))
        ButtonWithIcon(
            imageVector = Icons.Filled.Settings,
            onClick = navigateToGroupSettings,
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