package com.example.bookforum.ui.navigation.destinations

import com.example.bookforum.ui.navigation.NavigationDestination

object FeedDestination : NavigationDestination {
    override val route = "feed_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}