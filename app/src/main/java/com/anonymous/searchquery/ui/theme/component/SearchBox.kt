package com.anonymous.searchquery.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anonymous.searchquery.R

@Composable
fun SearchBox(searchFiledState:TextFieldState, modifier: Modifier, onSearch:()->(Unit) ) {

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(60.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.app_name)
            )
            SearchText(searchFiledState,onSearch)
        }

    }
}

@Preview
@Composable
fun PreviewSearchBox() {
    var searchFiledState = remember { TextFieldState() }
    SearchBox(searchFiledState,Modifier.fillMaxWidth(0.2f)) {

    }
}


