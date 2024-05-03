package com.example.bookforum.ui.apiUi.screens.bodyScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.ui.apiUi.BooksViewModel

@Composable
internal fun ApiSearchTextField(
    booksViewModel: BooksViewModel,
    modifier: Modifier = Modifier
) {
    var queryInput by remember { mutableStateOf("") }
    Spacer(
        modifier = modifier.height(100.dp)
    )
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = queryInput,
            onValueChange = { queryInput = it },
            modifier = modifier.height(55.dp)
        )
        Button(
            onClick = { booksViewModel.getBooks(queryInput) },
            shape = RoundedCornerShape(
                topEnd = dimensionResource(R.dimen.padding_large),
                bottomEnd = dimensionResource(R.dimen.padding_large)
            ),
            modifier = modifier.height(55.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        }
    }
}