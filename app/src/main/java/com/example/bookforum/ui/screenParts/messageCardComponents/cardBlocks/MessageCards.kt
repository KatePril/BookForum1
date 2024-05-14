package com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageCard(
    onMessageClick: () -> Unit,
    containerColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Card(
        onClick = onMessageClick,
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.default_elevation)
        ),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        content = content
    )
}

@Composable
fun CurrentUserMsgCard(
    onMessageClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    MessageCard(
        onMessageClick = onMessageClick,
        containerColor = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.padding_large),
            topEnd = dimensionResource(R.dimen.padding_large),
            bottomStart = dimensionResource(R.dimen.padding_large)
        ),
        modifier = modifier,
        content = content
    )
}

@Composable
fun ReceiverMsgCard(
    onMessageClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        MessageCard(
            onMessageClick = onMessageClick,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.padding_large),
                topEnd = dimensionResource(R.dimen.padding_large),
                bottomEnd = dimensionResource(R.dimen.padding_large)
            ),
            modifier = modifier.weight(2f),
            content = content
        )
        Spacer(modifier = modifier.weight(1f))
    }
}