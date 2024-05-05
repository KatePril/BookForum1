package com.example.bookforum.ui.databaseUi.postsUI.screens.feed.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.entities.Post
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels.LikedPostsViewModel
import kotlinx.coroutines.launch

@Composable
internal fun PostItem(
    userId: Int,
    onCommentsButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    post: Post,
    modifier: Modifier = Modifier,
    likedPostsViewModel: LikedPostsViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    likedPostsViewModel.checkLikedPostExistence(userId, post.id)
    val liked by likedPostsViewModel.checkedPostFlow.collectAsState()
    Log.i("LIKED", liked.toString())
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.default_elevation)
        )
    ) {
        Column(
            modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
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
                    liked = liked != null,
                    isAuthor = userId == post.userId,
                    onLikeButtonClick = {
                        coroutineScope.launch {
                            likedPostsViewModel
                                .updateLikedPost(
                                    LikedPost(
                                    id = liked ?: 0,
                                    userId = userId,
                                    postId = post.id
                                )
                            )
                            Log.i("LIKED_UPDATED", liked.toString())
                        }
                    },
                    onCommentsButtonClick = onCommentsButtonClick,
                    onEditButtonClick = onEditButtonClick,
                    onExpandButtonClick = { expanded = !expanded },
                    modifier = modifier.weight(1f)
                )
            }
            if (expanded) {
                Text(
                    text = post.review,
                    modifier = modifier.padding(top = dimensionResource(R.dimen.padding_large))
                )
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun PostItemPreview() {
//    BookForumTheme {
//        PostItem(
//            post = Post(
//                id = 1,
////                title = "A Game of Thrones",
//                title = "Harry Potter and the Sorcerer's Stone",
//                author = "George R. R. Martin",
//                published = "01.08.1996",
//                review = "\"Game of Thrones\" is a captivating introduction to Martin's epic saga," +
//                        "blending elements of fantasy, political intrigue, and human drama into " +
//                        "a spellbinding tale that will leave readers eager for more." +
//                        "Whether you're a fan of fantasy or simply enjoy a compelling story, " +
//                        "this book is sure to captivate and enthrall.",
//                userId = 0
//            ),
//            onCommentsButtonClick = {},
//            userId = 0
//        )
//    }
//}