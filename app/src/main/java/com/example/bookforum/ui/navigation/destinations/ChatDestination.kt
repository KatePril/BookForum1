package com.example.bookforum.ui.navigation.destinations

import com.example.bookforum.ui.navigation.NavigationDestination

object ChatDestination : NavigationDestination {
    override val route = "chat_page"
    const val userIdArg = "userId"
    const val receiverIdArg = "receiverIdArg"
    val routeWithArgs = "$route/{$userIdArg}/{$receiverIdArg}"
}