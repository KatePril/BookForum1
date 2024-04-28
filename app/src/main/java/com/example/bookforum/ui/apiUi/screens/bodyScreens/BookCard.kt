package com.example.bookforum.ui.apiUi.screens.bodyScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookforum.R
import com.example.bookforum.network.apiObjects.Book
import com.example.bookforum.ui.screenParts.ExpandButton
import com.example.compose.BookForumTheme

@Composable
internal fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (expanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row (
                modifier = modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BookApiPhoto(imgLink = book.imgLink, modifier = modifier.weight(1f))
                BookApiItemInfo(book = book, modifier = modifier.weight(2f))
                ExpandButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }

            if (expanded) {
                BookApiDescription(description = book.description)
            }
        }


    }
}

@Composable
private fun BookApiPhoto(imgLink: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgLink)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        modifier = modifier.padding(end = 16.dp)
    )
}

@Composable
private fun BookApiItemInfo(
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
private fun BookApiDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier.padding(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        )
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookApiObjectCardPreview() {
    BookForumTheme {
        BookCard(
            book = Book(
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