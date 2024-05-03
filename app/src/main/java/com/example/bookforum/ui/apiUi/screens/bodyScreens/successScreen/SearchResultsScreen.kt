package com.example.bookforum.ui.apiUi.screens.bodyScreens.successScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.bookforum.R
import com.example.bookforum.network.apiObjects.Book

@Composable
fun SearchResultScreen(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    if (books.isEmpty()) {
        NoResultsFoundMsg(modifier)
    } else {
        LazyColumn {
            items(books) { book ->
                BookCard(book = book, modifier = modifier)
            }
        }
    }
}

@Composable
fun NoResultsFoundMsg(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.no_results_found),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.padding_large))
    )
}

