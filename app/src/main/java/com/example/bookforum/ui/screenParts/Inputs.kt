package com.example.bookforum.ui.screenParts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun FormInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    color: Color,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(labelText)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = color,
            unfocusedContainerColor = color,
            disabledContainerColor = color,
        ),
        modifier = modifier.fillMaxWidth(),
        singleLine = singleLine
    )
}

@Composable
fun FormInputWithMessage(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    @StringRes msgText: Int,
    color: Color,
    isValid: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(labelText)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = color,
            unfocusedContainerColor = color,
            disabledContainerColor = color,
        ),
        modifier = modifier.fillMaxWidth(),
        singleLine = true
    )
    if (!isValid) {
        Text(
            text = stringResource(msgText),
            modifier = modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.error
        )
    }
}