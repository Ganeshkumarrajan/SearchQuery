package com.anonymous.searchquery.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchButton(onSearch:()->(Unit), modifier: Modifier){
    Button( modifier = modifier.background(MaterialTheme.colors.primary),  onClick = {}) {
        Text(text = "Go")
    }
}