package com.example.bookforum.ui.databaseUi.booksUI.screens.displayPosts

import com.example.bookforum.ui.apiUi.screens.ApiSearchDestination
import com.example.bookforum.ui.navigation.NavigationDestination

object PostsDisplayDestination : NavigationDestination {
    override val route = "posts_display_page"
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}