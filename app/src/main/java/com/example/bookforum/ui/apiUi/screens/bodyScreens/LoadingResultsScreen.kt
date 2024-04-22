package com.example.bookforum.ui.apiUi.screens.bodyScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookforum.R

@Composable
fun LoadingResultScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoadingResultScreenPreview() {
    LoadingResultScreen()
}