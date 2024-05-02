package com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange

import com.example.bookforum.ui.navigation.NavigationDestination

object PasswordChangeDestination: NavigationDestination {
    override val route = "change_password_page"
    const val userIdArg = "userId"
    val routeWithArgs = "${route}/{$userIdArg}"
}