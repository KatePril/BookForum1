package com.example.bookforum.ui.databaseUi.booksUI.screens.createPost

import com.example.bookforum.ui.apiUi.screens.ApiSearchDestination
import com.example.bookforum.ui.navigation.NavigationDestination

object PostCreationDestination : NavigationDestination {
    override val route = "post_creation_page"
    const val userIdArg = "userId"
    val routeWithArgs = "${ApiSearchDestination.route}/{$userIdArg}"
}