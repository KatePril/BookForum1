package com.example.bookforum.ui.databaseUi.postsUI.screens.profile

import com.example.bookforum.ui.databaseUi.postsUI.screens.createPost.PostCreationDestination
import com.example.bookforum.ui.navigation.NavigationDestination

object ProfileDestination: NavigationDestination {
    override val route = "profile_page"
    const val userIdArg = "userId"
    val routeWithArgs = "${PostCreationDestination.route}/{$userIdArg}"
}