package com.anonymous.searchquery.ui.theme.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anonymous.searchquery.presentaiton.search.model.SearchUI

@Composable
fun ItemView(objectIDs: SearchUI) {
    Text(
        text = objectIDs.idObjects.toString(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    )
}
