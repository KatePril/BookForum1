package com.example.bookforum.ui.screenParts.forms

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun FormInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    color: Color,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = true,
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
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        )
    )
}

@Composable
fun FormInputWithMessage(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    @StringRes msgText: Int,
    color: Color,
    modifier: Modifier = Modifier,
    isValid: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = true
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
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        )
    )
    if (!isValid) {
        Text(
            text = stringResource(msgText),
            modifier = modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.error
        )
    }
}