package com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts

import com.example.bookforum.ui.navigation.NavigationDestination

object FeedDestination : NavigationDestination {
    override val route = "feed_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}