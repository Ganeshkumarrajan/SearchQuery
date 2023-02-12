package com.anonymous.searchquery.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchText(searchFiledState: TextFieldState = remember { TextFieldState() }, onSearch:()->(Unit)) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = Modifier
            .wrapContentHeight()
            .background(Color.Transparent),
        value = searchFiledState.text,
        onValueChange = {
            searchFiledState.text = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text(text = "Type your name") },
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearch.invoke()
            }
        )
    )
}

class TextFieldState() {
    var text: String by mutableStateOf("")
}
