package com.example.bookforum.ui.databaseUi.userUI.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.BookForumTheme

/*TODO Delete*/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
//            ForumTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.DriveFileRenameOutline, contentDescription = null)
            }
        },
        modifier = modifier
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    BookForumTheme {
        HomeScreen()
    }
}