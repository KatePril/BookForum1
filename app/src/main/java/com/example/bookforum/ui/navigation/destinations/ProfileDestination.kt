package com.example.bookforum.ui.navigation.destinations

import com.example.bookforum.ui.navigation.NavigationDestination

object ProfileDestination: NavigationDestination {
    override val route = "profile_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}