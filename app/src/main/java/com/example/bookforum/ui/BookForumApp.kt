package com.example.bookforum.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bookforum.ui.navigation.ForumNavHost

@Composable
fun BookForumApp(navController: NavHostController = rememberNavController()) {
    ForumNavHost(navController = navController)
}