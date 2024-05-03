package com.example.bookforum.ui.databaseUi.likedPostsUI.screens

import com.example.bookforum.ui.navigation.NavigationDestination

object LikedPostsPageDestination: NavigationDestination {
    override val route = "liked_posts_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}