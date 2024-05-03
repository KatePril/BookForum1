package com.example.bookforum.ui.navigation.destinations

import com.example.bookforum.ui.navigation.NavigationDestination

object LikedPostsPageDestination: NavigationDestination {
    override val route = "liked_posts_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}