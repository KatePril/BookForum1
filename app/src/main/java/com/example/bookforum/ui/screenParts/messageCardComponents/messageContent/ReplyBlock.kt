package com.example.bookforum.ui.screenParts.messageCardComponents.messageContent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.bookforum.R

@Composable
fun ReplyBlock(
    username: String,
    text: String,
    surfaceColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = surfaceColor,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "$username: $text",
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.padding(dimensionResource(R.dimen.default_elevation))
        )
    }
}