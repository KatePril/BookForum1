package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.cardBlocks

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageCard(
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