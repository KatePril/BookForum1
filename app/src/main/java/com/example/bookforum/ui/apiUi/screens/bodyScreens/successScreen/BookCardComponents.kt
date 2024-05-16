package com.example.bookforum.ui.apiUi.screens.bodyScreens.successScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookforum.R
import com.example.bookforum.network.apiObjects.Book

@Composable
internal fun BookApiPhoto(imgLink: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgLink)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        modifier = modifier.padding(end = dimensionResource(R.dimen.padding_large))
    )
}

@Composable
internal fun BookApiItemInfo(
    book: Book,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        Text(
            text = book.title,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = book.author,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = book.year,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
internal fun BookApiDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_large),
            end = dimensionResource(R.dimen.padding_large),
            bottom = dimensionResource(R.dimen.padding_large)
        )
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}