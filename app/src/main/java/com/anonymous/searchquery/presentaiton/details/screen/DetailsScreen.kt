package com.anonymous.searchquery.presentaiton.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.searchquery.presentaiton.details.model.ObjectDetailsUI
import com.anonymous.searchquery.presentaiton.details.viewmodel.ObjectDetailsViewModel
import com.anonymous.searchquery.presentaiton.util.UIState
import com.anonymous.searchquery.ui.theme.component.ErrorText
import com.anonymous.searchquery.ui.theme.component.ImageView
import com.anonymous.searchquery.ui.theme.component.LoadingIndicator
import com.anonymous.searchquery.ui.theme.component.TitleText

@Composable
fun DerailsScreen(id: Long, viewModel: ObjectDetailsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OnResult(viewModel)
        viewModel.objectDetails(id)
    }
}

@Composable
private fun OnResult(viewModel: ObjectDetailsViewModel) {
    when (val result = viewModel.objectDetails.collectAsState().value) {
        is UIState.Success -> {

            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                ShowObjectDetails(result.data)
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

@Composable
private fun ShowObjectDetails(data: ObjectDetailsUI) {
    ShowAdditionalImages(data.additionalImages)
    ShowOtherFields(data.otherDetails)
    ImageView(
        url = data.imageURL,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)

    )
}

@Composable
private fun ShowAdditionalImages(urls: List<String>) {
    if (urls.isNotEmpty()) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1), content = {
                items(urls) { imageURL ->
                    ImageView(
                        url = imageURL,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp)
                    )
                }
            },
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ShowOtherFields(others: List<String>) {
    LazyVerticalGrid(columns = GridCells.Fixed(1), content = {
        items(others) { titleText ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                backgroundColor = Color.LightGray,
            ) {
                TitleText(
                    message = titleText
                )
            }
        }
    })
}
