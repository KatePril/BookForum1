package com.example.bookforum.ui.navigation.destinations.groupDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object GroupEditDestination: NavigationDestination {
    override val route = "edit_group_page"
    const val userIdArg = "userId"
    const val groupIdArg = "groupIdArg"
    val routeWithArgs = "$route/{$userIdArg}/{$groupIdArg}"
}