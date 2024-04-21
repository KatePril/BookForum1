package com.example.bookforum.ui.forumScreens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.compose.BookForumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumTopAppBar (
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { TitleBody(modifier) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary)
    )
}

@Composable
private fun TitleBody(
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(end = 16.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = stringResource(R.string.logo),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = modifier.weight(0.5f))
        ButtonWithIcon(
            imageVector = Icons.Filled.Favorite,
            onClick = { /*TODO*/ },
            tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
        )
        ButtonWithIcon(
            imageVector = Icons.Filled.Face,
            onClick = { /*TODO*/ },
            tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ForumTopAppBarPreview() {
    BookForumTheme {
        ForumTopAppBar()
    }
}