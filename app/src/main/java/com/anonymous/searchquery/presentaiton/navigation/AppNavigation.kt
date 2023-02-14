package com.anonymous.searchquery.presentaiton.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anonymous.searchquery.presentaiton.details.screen.DerailsScreen
import com.anonymous.searchquery.presentaiton.search.screen.SearchScreen

@Composable
fun NavigationApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenNames.Search.route
    ) {
        composable(route = ScreenNames.Search.route) {
            SearchScreen(navController)
        }
        composable(route = "${ScreenNames.Details.route}/{objectID}") { backStackEntry ->
            val objectID = backStackEntry.arguments?.getString("objectID") ?: ""
            DerailsScreen(objectID.toLong())
        }
    }
}

sealed class ScreenNames(val route: String) {
    object Search : ScreenNames("Search")
    object Details : ScreenNames("Details")
}
