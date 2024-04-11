package com.example.bookforum.ui.apiScreens.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.ui.ApiUiState
import com.example.compose.BookForumTheme

@Composable
fun ApiResultScreen(
    uiState: ApiUiState,
    modifier: Modifier = Modifier
) {
    ApiSearchTextField(modifier)
    when(uiState) {
        is ApiUiState.Success -> {
            if (uiState.books != null) {
                SearchResultScreen(
                    books = uiState.books,
                    modifier = modifier
                )
            } else {
                Text(text = "No results found")
            }
        }
        is ApiUiState.Loading -> {
            LoadingResultScreen(modifier)
        }
        is ApiUiState.Error -> {
            ErrorApiScreen(uiState.error.orEmpty(), modifier)
        }
    }
}

@Composable
fun ApiSearchTextField(modifier: Modifier = Modifier) {
    var queryInput by remember { mutableStateOf("") }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        TextField(
            value = queryInput,
            onValueChange = { queryInput = it },
            modifier = modifier.height(55.dp)
        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                bottomEnd = 16.dp
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ApiResultScreenPreview() {
    BookForumTheme {
        ApiResultScreen(ApiUiState.Loading)
    }
}