package com.example.bookforum.ui.navigation.destinations.groupDestination

import com.example.bookforum.ui.navigation.NavigationDestination

object GroupsListDestination: NavigationDestination {
    override val route = "groups_list_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}