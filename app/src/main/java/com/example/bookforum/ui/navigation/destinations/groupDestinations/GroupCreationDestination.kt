package com.example.bookforum.ui.navigation.destinations.groupDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object GroupCreationDestination: NavigationDestination {
    override val route = "group_creation_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}