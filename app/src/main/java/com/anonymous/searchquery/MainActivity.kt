package com.anonymous.searchquery

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anonymous.searchquery.presentaiton.search.screen.SearchScreen
import com.anonymous.searchquery.presentaiton.search.viewmodel.SearchViewModel
import com.anonymous.searchquery.presentaiton.search.viewmodel.UserEvent
import com.anonymous.searchquery.ui.theme.SearchQueryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var myBackground: Drawable? = null
    val viewmodel by viewModels<SearchViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        R.string.app_name
        setContent {
            SearchQueryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SearchScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SearchQueryTheme {
        Greeting("Android")
    }
}
