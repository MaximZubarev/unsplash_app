package com.mldz.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mldz.favorites.FavoritesScreen


const val favoritesNavigationRoute = "favorites_route"

fun NavGraphBuilder.favoritesRoute() {
    composable(route = favoritesNavigationRoute) {
        FavoritesScreen()
    }
}

fun NavController.navigateToFavorites(navOptions: NavOptions? = null) {
    this.navigate(route = favoritesNavigationRoute, navOptions = navOptions)
}