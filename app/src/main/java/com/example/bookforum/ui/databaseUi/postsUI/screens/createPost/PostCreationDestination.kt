package com.example.bookforum.ui.databaseUi.postsUI.screens.createPost

import com.example.bookforum.ui.navigation.NavigationDestination

object PostCreationDestination : NavigationDestination {
    override val route = "post_creation_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}