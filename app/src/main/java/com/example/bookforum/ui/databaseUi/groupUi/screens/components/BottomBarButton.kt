package com.example.bookforum.ui.databaseUi.groupUi.screens.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R

@Composable
internal fun BottomBarButton (
    onCLick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    Button(
        onClick = onCLick,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .fillMaxWidth(),
        content = content
    )
}