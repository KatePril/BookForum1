package com.example.bookforum.ui.navigation.destinations

import com.example.bookforum.ui.navigation.NavigationDestination

object CommentPageDestination : NavigationDestination {
    override val route = "comments_page"
    const val userIdArg = "userId"
    const val postIdArg = "postId"
    val routeWithArgs = "$route/{$userIdArg}/{$postIdArg}"
}