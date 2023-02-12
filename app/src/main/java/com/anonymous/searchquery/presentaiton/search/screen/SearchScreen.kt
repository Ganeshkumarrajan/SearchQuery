package com.anonymous.searchquery.presentaiton.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.anonymous.searchquery.presentaiton.search.viewmodel.SearchViewModel
import com.anonymous.searchquery.presentaiton.search.viewmodel.UserEvent
import com.anonymous.searchquery.presentaiton.util.UIState
import com.anonymous.searchquery.ui.theme.component.*
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .padding(10.dp)
    ) {
        var searchFiledState = remember { TextFieldState() }
        SearchBox(searchFiledState, Modifier.fillMaxWidth()) {
            viewModel.onEvent(UserEvent.Search(searchFiledState.text))
        }

        onSearch(viewModel)
    }

}

@Composable
private fun onSearch(viewModel: SearchViewModel) {
    when (val result = viewModel.searchResult.collectAsState().value) {
        is UIState.Success -> {
            showObjects(result.data)
        }
        is UIState.Error -> {

        }
        is UIState.Loading -> {
            LoadingIndicator()
        }
    }
}

@Composable
fun showObjects(objects: List<SearchUI>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(objects) { item ->
            Card(
                modifier = Modifier.padding(10.dp),
                backgroundColor = Color.LightGray
            ) {
                ItemView(objectIDs = item)
            }
        }
    }
}

