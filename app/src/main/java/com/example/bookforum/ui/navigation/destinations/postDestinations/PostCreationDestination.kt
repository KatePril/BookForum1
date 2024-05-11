package com.example.bookforum.ui.navigation.destinations.postDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object PostCreationDestination : NavigationDestination {
    override val route = "post_creation_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}