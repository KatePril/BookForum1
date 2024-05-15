package com.example.bookforum.ui.navigation.destinations.groupDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object GroupDestination: NavigationDestination {
    override val route = "group_page"
    const val userIdArg = "userId"
    const val groupIdArg = "groupIdArg"
    val routeWithArgs = "$route/{$userIdArg}/{$groupIdArg}"
}