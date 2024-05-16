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