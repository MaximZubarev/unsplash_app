package com.mldz.favorites_impl

import androidx.compose.runtime.Composable
import com.mldz.favorites.FavoritesEntry
import org.koin.core.annotation.Single


@Single
class FavoritesEntryImpl: FavoritesEntry() {

    @Composable
    override fun start(navigateToPhoto: (String) -> Unit) {
        FavoritesScreen()
    }
}