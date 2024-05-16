package com.example.bookforum.ui.apiUi.screens.bodyScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.bookforum.R

@Composable
fun ErrorApiScreen(
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = "Error",
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}