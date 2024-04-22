package com.example.bookforum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookforum.ui.apiUi.screens.ApiResultScreen
import com.example.bookforum.ui.apiUi.screens.ApiSearchDestination
import com.example.bookforum.ui.databaseUi.booksUI.screens.createPost.PostCreationDestination
import com.example.bookforum.ui.databaseUi.booksUI.screens.createPost.PostCreationScreen
import com.example.bookforum.ui.databaseUi.booksUI.screens.displayPosts.PostsDisplayDestination
import com.example.bookforum.ui.databaseUi.booksUI.screens.displayPosts.PostsDisplayScreen
import com.example.bookforum.ui.databaseUi.userUI.screens.logIn.LogInDestination
import com.example.bookforum.ui.databaseUi.userUI.screens.logIn.LoginScreen
import com.example.bookforum.ui.databaseUi.userUI.screens.registration.RegistrationDestination
import com.example.bookforum.ui.databaseUi.userUI.screens.registration.RegistrationScreen

@Composable
fun ForumNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LogInDestination.route,
        modifier = modifier
    ) {
        composable(route = LogInDestination.route) {
            LoginScreen()
        }
        composable(route = RegistrationDestination.route) {
            RegistrationScreen()
        }
        composable(
            route = PostsDisplayDestination.route,
            arguments = listOf(navArgument(PostsDisplayDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            PostsDisplayScreen()
        }
        composable(
            route = PostCreationDestination.route,
            arguments = listOf(navArgument(PostCreationDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            PostCreationScreen()
        }
        composable(
            route = ApiSearchDestination.route,
            arguments = listOf(navArgument(ApiSearchDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            ApiResultScreen()
        }
    }
}