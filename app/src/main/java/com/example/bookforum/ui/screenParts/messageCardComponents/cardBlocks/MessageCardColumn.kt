package com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R

@Composable
fun MessageCardColumn(
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = Modifier.padding(
            top = dimensionResource(R.dimen.padding_medium),
            bottom = dimensionResource(R.dimen.padding_large),
            start = dimensionResource(R.dimen.padding_large),
            end = dimensionResource(R.dimen.padding_large)
        ),
        content = content
    )
}