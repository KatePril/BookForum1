package com.example.bookforum.ui.apiScreens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.model.BookApiObject
import com.example.compose.BookForumTheme

@Composable
fun SearchResultScreen(
    books: List<BookApiObject>,
    modifier: Modifier = Modifier
) {
    
}

@Composable
fun BookApiObjectCard(
    book: BookApiObject,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Row (
            modifier = modifier.padding(16.dp)
        ) {
            BookApiItemInfo(book = book, modifier = modifier)
            Spacer(modifier = modifier.weight(1f))
            BookApiItemButton(expanded = expanded, onClick = { })
        }

    }
}

@Composable
private fun BookApiItemInfo(
    book: BookApiObject,
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
private fun BookApiItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookApiObjectCardPreview() {
    BookForumTheme {
        BookApiObjectCard(
            book = BookApiObject(
                ISBN = "1597775088,9781597775083",
                author = "Stephen W Hawking",
                description = "\"The Theory of Everything\" is a unique opportunity to explore the cosmos " +
                        "with the greatest mind since Einstein. Based on a series of lectures given at Cambridge University, " +
                        "Professor Hawking's work introduced \"the history of ideas about the universe\" as well as today's " +
                        "most important scientific theories about time, space, and the cosmos in a clear, easy-to-understand way.",
                imgLink = "https://library.lol/covers/16000/be164417a5b65a0fe68e1e9a59f3f833-d.jpg",
                pdfLink = "https://cloudflare-ipfs.com/ipfs/bafykbzaceaytmjlhzgmha3vmc6z2dppr6imf6leqkfkthoyp5jhzbfmr3impm?filename=Stephen%20W%20Hawking%20-%20The%20theory%20of%20everything-Phoenix%20Books%20%282006%29.pdf",
                publisher = "Phoenix Books",
                title = "The theory of everything",
                year = "2006"
            )
        )
    }

}