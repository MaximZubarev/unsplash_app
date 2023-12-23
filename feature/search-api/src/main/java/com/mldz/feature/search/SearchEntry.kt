package com.mldz.feature.search

import androidx.compose.runtime.Composable


abstract class SearchEntry {

    val featureRoute = "search_route"

    @Composable
    abstract fun Start(
        navigateToPhoto: (String) -> Unit,
        navigateBack: () -> Unit
    )
}