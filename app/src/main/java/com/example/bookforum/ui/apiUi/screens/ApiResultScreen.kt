package com.example.bookforum.ui.apiUi.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.apiUi.ApiUiState
import com.example.bookforum.ui.apiUi.BooksViewModel
import com.example.bookforum.ui.apiUi.screens.bodyScreens.ApiSearchTextField
import com.example.bookforum.ui.apiUi.screens.bodyScreens.ErrorApiScreen
import com.example.bookforum.ui.apiUi.screens.bodyScreens.LoadingResultScreen
import com.example.bookforum.ui.apiUi.screens.bodyScreens.NoResultsFoundMsg
import com.example.bookforum.ui.apiUi.screens.bodyScreens.SearchResultScreen
import com.example.bookforum.ui.screenParts.ForumTopAppBar
import com.example.compose.BookForumTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ApiResultScreen(
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: BooksViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {

    Scaffold(
        topBar = {
            ForumTopAppBar(
                quitAccount = quitAccount,
                navigateToGlobalPage = { navigateToGlobalPage(viewModel.userId) },
                navigateToFavouritePosts = { navigateToFavouritePosts(viewModel.userId) },
                navigateToProfile = { navigateToProfile(viewModel.userId) }
            )
        }
    ) { innerPadding ->
        ApiResultScreenBody(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
private fun ApiResultScreenBody(
    viewModel: BooksViewModel,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ApiSearchTextField(viewModel)
        when(val uiState: ApiUiState = viewModel.uiState) {
            is ApiUiState.Success -> {
                if (uiState.books != null) {
                    SearchResultScreen(
                        books = uiState.books,
                        modifier = modifier
                    )
                } else {
                    NoResultsFoundMsg(modifier)
                }
            }
            is ApiUiState.Loading -> {
                LoadingResultScreen(modifier)
            }
            is ApiUiState.Error -> {
                ErrorApiScreen(uiState.error.orEmpty(), modifier)
            }
            else -> {}
        }
    }

}
