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
import com.example.bookforum.ui.navigation.destinations.CommentPageDestination
import com.example.bookforum.ui.databaseUi.commentUi.screens.CommentsScreen
import com.example.bookforum.ui.navigation.destinations.LikedPostsPageDestination
import com.example.bookforum.ui.databaseUi.likedPostsUI.screens.LikedPostsScreen
import com.example.bookforum.ui.navigation.destinations.PostCreationDestination
import com.example.bookforum.ui.databaseUi.postsUI.screens.createPost.PostCreationScreen
import com.example.bookforum.ui.navigation.destinations.FeedDestination
import com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts.FeedScreen
import com.example.bookforum.ui.databaseUi.postsUI.screens.editPost.EditPostScreen
import com.example.bookforum.ui.navigation.destinations.ProfileDestination
import com.example.bookforum.ui.databaseUi.userUI.screens.profile.ProfileScreen
import com.example.bookforum.ui.navigation.destinations.LogInDestination
import com.example.bookforum.ui.databaseUi.userUI.screens.logIn.LoginScreen
import com.example.bookforum.ui.navigation.destinations.PasswordChangeDestination
import com.example.bookforum.ui.databaseUi.userUI.screens.passwordChange.PasswordChangeScreen
import com.example.bookforum.ui.navigation.destinations.RegistrationDestination
import com.example.bookforum.ui.databaseUi.userUI.screens.registration.RegistrationScreen
import com.example.bookforum.ui.navigation.destinations.ChatDestination
import com.example.bookforum.ui.navigation.destinations.ChatsListDestination
import com.example.bookforum.ui.navigation.destinations.EditPostDestination

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
            LoginScreen(
                navigateToRegistration = { navController.navigate(RegistrationDestination.route) },
                navigateToPostsDisplayPage = {
                    navController.navigate("${FeedDestination.route}/$it")
                }
            )
        }
        composable(route = RegistrationDestination.route) {
            RegistrationScreen(
                navigateToFeedPage = {
                    navController.navigate("${FeedDestination.route}/$it")
                },
                onCancelClick = { navController.navigate(LogInDestination.route) }
            )
        }
        composable(
            route = ProfileDestination.routeWithArgs,
            arguments = listOf(navArgument(ProfileDestination.userIdArg){
                type = NavType.IntType
            })
        ) {
            ProfileScreen(
                navigateBack = { navController.navigate("${FeedDestination.route}/$it") },
                onChangePasswordClick = { navController.navigate("${PasswordChangeDestination.route}/$it")},
                navigateBackOnDelete = { navController.navigate(LogInDestination.route) }
            )
        }
        composable(
            route = PasswordChangeDestination.routeWithArgs,
            arguments = listOf(navArgument(PasswordChangeDestination.userIdArg){
                type = NavType.IntType
            })
        ) {
            PasswordChangeScreen(
                navigateBack = { navController.navigate("${ProfileDestination.route}/$it") }
            )
        }
        composable(
            route = FeedDestination.routeWithArgs,
            arguments = listOf(navArgument(FeedDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            FeedScreen(
                onCommentsButtonClick = { userId: Int, postId: Int ->
                    navController.navigate("${CommentPageDestination.route}/$userId/$postId")
                },
                onEditButtonClick = { userId: Int, postId: Int ->
                    navController.navigate("${EditPostDestination.route}/$userId/$postId")
                },
                quitAccount = { navController.navigate(LogInDestination.route) },
                navigateToGlobalPage = {
                    navController.navigate("${ApiSearchDestination.route}/$it")
                },
                navigateToPostCreation = {
                    navController.navigate("${PostCreationDestination.route}/$it")
                },
                navigateToFavouritePosts = {
                    navController.navigate("${LikedPostsPageDestination.route}/$it")
                },
                navigateToProfile = { navController.navigate("${ProfileDestination.route}/$it") }
            )
        }
        composable(
            route = PostCreationDestination.routeWithArgs,
            arguments = listOf(navArgument(PostCreationDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            PostCreationScreen(
                navigateToFeed = {
                    navController.navigate("${FeedDestination.route}/$it")
                }
            )
        }
        composable(
            route = LikedPostsPageDestination.routeWithArgs,
            arguments = listOf(navArgument(FeedDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            LikedPostsScreen(
                onCommentsButtonClick = { userId: Int, postId: Int ->
                    navController.navigate("${CommentPageDestination.route}/$userId/$postId")
                },
                onEditButtonClick = { userId: Int, postId: Int ->
                    navController.navigate("${EditPostDestination.route}/$userId/$postId")
                },
                quitAccount = { navController.navigate(LogInDestination.route) },
                navigateToGlobalPage = {
                    navController.navigate("${ApiSearchDestination.route}/$it")
                },
                navigateToPostCreation = {
                    navController.navigate("${PostCreationDestination.route}/$it")
                },
                navigateToFavouritePosts = {
                    navController.navigate("${LikedPostsPageDestination.route}/$it")
                },
                navigateToProfile = { navController.navigate("${ProfileDestination.route}/$it") }
            )
        }
        composable(
            route = ApiSearchDestination.routeWithArgs,
            arguments = listOf(navArgument(ApiSearchDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            ApiResultScreen(
                quitAccount = { navController.navigate(LogInDestination.route) },
                navigateToGlobalPage = { navController.navigate("${FeedDestination.route}/$it") },
                navigateToFavouritePosts = {
                    navController.navigate("${LikedPostsPageDestination.route}/$it")
                },
                navigateToProfile = { navController.navigate("${ProfileDestination.route}/$it") }
            )
        }
        composable(
            route = CommentPageDestination.routeWithArgs,
            arguments = listOf(navArgument(CommentPageDestination.userIdArg) {
                type = NavType.IntType
            }, navArgument(CommentPageDestination.postIdArg) {
                type = NavType.IntType
            })
        ) {
            CommentsScreen(
                navigateToFeed = { navController.navigate("${FeedDestination.route}/$it") }
            )
        }
        composable(
            route = EditPostDestination.routeWithArgs,
            arguments = listOf(navArgument(EditPostDestination.userIdArg) {
                type = NavType.IntType
            }, navArgument(EditPostDestination.postIdArg) {
                type = NavType.IntType
            })
        ) {
            EditPostScreen(
                navigateBack = { navController.navigate("${FeedDestination.route}/$it") }
            )
        }
        composable(
            route = ChatsListDestination.routeWithArgs,
            arguments = listOf(navArgument(ChatsListDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            /*TODO create screen*/
        }
        composable(
            route = ChatDestination.routeWithArgs,
            arguments = listOf(navArgument(ChatDestination.userIdArg){
                type = NavType.IntType
            }, navArgument(ChatDestination.receiverIdArg) {
                type = NavType.IntType
            })
        ) {
            /*TODO create screen*/
        }
    }
}