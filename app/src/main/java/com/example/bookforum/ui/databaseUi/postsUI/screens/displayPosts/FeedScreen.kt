package com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.Post
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.FeedViewModel
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import com.example.bookforum.ui.screenParts.ExpandButton
import com.example.bookforum.ui.screenParts.ForumTopAppBar
import com.example.compose.BookForumTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedScreen(
    navigateToGlobalPage: (Int) -> Unit,
    navigateToPostCreation: (Int) -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: () -> Unit,
    viewModel: FeedViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val postsUiState by viewModel.postsUiState.collectAsState()
    val userUiState = viewModel.userUiState.collectAsState()

    Scaffold(
        topBar = {
            ForumTopAppBar(
                navigateToGlobalPage = { navigateToGlobalPage(userUiState.value.user.id) },
                navigateToFavouritePosts = navigateToFavouritePosts,
                navigateToProfile = navigateToProfile
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToPostCreation(userUiState.value.user.id) }
            ) {
                Icon(
                    imageVector = Icons.Filled.DriveFileRenameOutline,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier
    ) { innerPadding ->
        Text(text = userUiState.value.user.id.toString())
        PostsDisplayBody(
            postsList = postsUiState.postsList,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun PostsDisplayBody(
    postsList: List<Post>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp),
    ) {
        if (postsList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_posts_msg),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = modifier.padding(contentPadding)
            )
        } else {
            PostsList(
                postsList = postsList,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
private fun PostsList(
    postsList: List<Post>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(postsList) { post ->
            PostItem(
                post = post,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun PostItem(
    post: Post,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var liked by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Row(
                modifier = modifier
            ) {
                PostInfo(
                    post = post,
                    modifier = modifier.weight(2f)
                )

                PostButtons(
                    expanded = expanded,
                    liked = liked,
                    onLikeButtonClick = { /*TODO*/ },
                    onCommentsButtonClick = { /*TODO*/ },
                    onExpandButtonClick = { expanded = !expanded },
                    modifier = modifier.weight(1f)
                )
            }
            if (expanded) {
                Text(
                    text = post.review,
                    modifier = modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun PostButtons(
    expanded: Boolean,
    liked: Boolean,
    onLikeButtonClick: () -> Unit,
    onCommentsButtonClick: () -> Unit,
    onExpandButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonWithIcon(
            imageVector = if (liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            onClick = onLikeButtonClick,
            modifier = modifier
        )
        ButtonWithIcon(
            imageVector = Icons.Filled.Comment,
            onClick = onCommentsButtonClick,
            modifier = modifier
        )
        ExpandButton(
            expanded = expanded,
            onClick = onExpandButtonClick,
            modifier = modifier
        )
    }
}

@Composable
fun PostInfo(
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "By ${post.author}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Published: ${post.published}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PostItemPreview() {
    BookForumTheme {
        PostItem(
            post = Post(
                id = 1,
//                title = "A Game of Thrones",
                title = "Harry Potter and the Sorcerer's Stone",
                author = "George R. R. Martin",
                published = "01.08.1996",
                review = "\"Game of Thrones\" is a captivating introduction to Martin's epic saga," +
                        "blending elements of fantasy, political intrigue, and human drama into " +
                        "a spellbinding tale that will leave readers eager for more." +
                        "Whether you're a fan of fantasy or simply enjoy a compelling story, " +
                        "this book is sure to captivate and enthrall."
            )
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmptyPostsDisplayBodyPreview() {
    BookForumTheme {
        PostsDisplayBody(postsList = listOf())
    }
}