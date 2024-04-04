package com.example.bookforum.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SampleScreen(
    uiState: String,
    modifier: Modifier = Modifier
) {
    Text(text = uiState)
}