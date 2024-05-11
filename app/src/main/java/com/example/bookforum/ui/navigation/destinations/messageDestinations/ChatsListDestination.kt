package com.example.bookforum.ui.navigation.destinations.messageDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object ChatsListDestination : NavigationDestination {
    override val route = "chats_list_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}