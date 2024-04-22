package com.example.bookforum.ui.apiUi.screens.bodyScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R

@Composable
fun ErrorApiScreen(
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = "Error",
            modifier = modifier.size(250.dp)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorApiScreenPreview() {
//    ErrorApiScreen()
}