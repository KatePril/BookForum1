package com.example.bookforum.ui.navigation.destinations.apiDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object ApiSearchDestination : NavigationDestination {
    override val route = "api_search_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}