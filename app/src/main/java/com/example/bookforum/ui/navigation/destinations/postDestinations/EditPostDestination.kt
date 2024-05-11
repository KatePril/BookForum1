package com.example.bookforum.ui.navigation.destinations.postDestinations

import com.example.bookforum.ui.navigation.NavigationDestination

object EditPostDestination : NavigationDestination {
    override val route = "edit_post_page"
    const val userIdArg = "userId"
    const val postIdArg = "postId"
    val routeWithArgs = "$route/{$userIdArg}/{$postIdArg}"
}