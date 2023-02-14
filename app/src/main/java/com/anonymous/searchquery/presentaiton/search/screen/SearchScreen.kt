@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.anonymous.searchquery.presentaiton.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anonymous.searchquery.presentaiton.navigation.ScreenNames
import com.anonymous.searchquery.presentaiton.search.events.UserEvent
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.anonymous.searchquery.presentaiton.search.viewmodel.SearchViewModel
import com.anonymous.searchquery.presentaiton.util.UIState
import com.anonymous.searchquery.ui.theme.component.*

@Composable
fun SearchScreen(nav: NavHostController, viewModel: SearchViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .padding(10.dp)
    ) {
        val searchFiledState: TextFieldState = remember { TextFieldState() }
        SearchBox(searchFiledState, Modifier.fillMaxWidth()) {
            viewModel.run { onEvent(UserEvent.Search(searchFiledState.text)) }
        }

        OnSearch(viewModel, nav)
    }
}

@Composable
private fun OnSearch(viewModel: SearchViewModel, nav: NavHostController,) {
    when (val result = viewModel.searchResult.collectAsState().value) {
        is UIState.Success -> {
            ShowObjects(result.data) {
                nav.navigate("${ScreenNames.Details.route}/$it")
            }
        }
        is UIState.Error -> {
            ErrorText(message = result.message)
        }
        is UIState.Loading -> {
            LoadingIndicator()
        }
        else -> {}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ShowObjects(objects: List<SearchUI>, onItemSelect: (id: Long) -> (Unit)) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(objects) { item ->
            Card(
                modifier = Modifier.padding(10.dp),
                backgroundColor = Color.LightGray,
                onClick = {
                    onItemSelect.invoke(item.idObjects)
                }
            ) {
                ItemView(objectIDs = item)
            }
        }
    }
}
