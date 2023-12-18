package com.mldz.favorites

import androidx.compose.runtime.Composable


abstract class FavoritesEntry {

    val featureRoute = "favorites_route"

    @Composable
    abstract fun start(
        navigateToPhoto: (String) -> Unit
    )
}