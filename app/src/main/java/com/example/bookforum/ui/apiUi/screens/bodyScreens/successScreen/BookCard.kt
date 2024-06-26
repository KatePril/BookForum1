package com.example.bookforum.ui.apiUi.screens.bodyScreens.successScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookforum.R
import com.example.bookforum.network.apiObjects.Book
import com.example.bookforum.ui.screenParts.buttons.ExpandButton
import com.example.bookforum.ui.theme.BookForumTheme

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
                start = dimensionResource(R.dimen.padding_large),
                top = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_large),
                bottom = dimensionResource(R.dimen.padding_large)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (expanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.default_elevation)
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
                modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
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