package com.example.bookforum.apiTests.fake

import com.example.bookforum.network.apiObjects.Book

object FakeDataSource {
    val booksList = listOf(
        Book(
            ISBN = "1597775088,9781597775083",
            author = "Stephen W Hawking",
            description = "\"The Theory of Everything\" is a unique opportunity to explore the cosmos with the greatest mind since Einstein. Based on a series of lectures given at Cambridge University, Professor Hawking's work introduced \"the history of ideas about the universe\" as well as today's most important scientific theories about time, space, and the cosmos in a clear, easy-to-understand way.",
            imgLink = "https://library.lol/covers/16000/be164417a5b65a0fe68e1e9a59f3f833-d.jpg",
            pdfLink = "https://cloudflare-ipfs.com/ipfs/bafykbzaceaytmjlhzgmha3vmc6z2dppr6imf6leqkfkthoyp5jhzbfmr3impm?filename=Stephen%20W%20Hawking%20-%20The%20theory%20of%20everything-Phoenix%20Books%20%282006%29.pdf",
            publisher = "Phoenix Books",
            title = "The theory of everything",
            year = "2006"
        ),
        Book(
            ISBN = "9780471465959,0-471-46595-X",
            author = "Paul Halpern",
            description = "The fundamental conundrum in physics today is the incompatibility of Einstein’s theory of general relativity with quantum mechanics. To bridge the gap between the two theories, a number of physicists have posited novel solutions involving hyperspace dimensions beyond the four that we can perceive and, most recently, branes, or membranes that exist in the fifth dimension and beyond. This lively account describes, in plain language, the history of hyperspace theory.",
            imgLink = "https://library.lol/covers/79000/57a8fb03890d89cf1d681f629982fc24-d.jpg",
            pdfLink = "https://cloudflare-ipfs.com/ipfs/bafykbzaceb5ejfppmbvysuijlxcgydcvrhwp74ouu4etncoiabp56dpzebro6?filename=Paul%20Halpern%20-%20The%20great%20beyond_%20higher%20dimensions%2C%20parallel%20universes%20and%20the%20extraordinary%20search%20for%20a%20theory%20of%20everything-J.%20Wiley%20%282004%29.pdf",
            publisher = "J. Wiley",
            title = "The great beyond: higher dimensions, parallel universes and the extraordinary search for a theory of everything",
            year = "2004"
        )
    )
}