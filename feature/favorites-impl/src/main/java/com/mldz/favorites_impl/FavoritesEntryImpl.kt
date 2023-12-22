package com.mldz.favorites_impl

import androidx.compose.runtime.Composable
import com.mldz.favorites.FavoritesEntry
import com.mldz.favorites_impl.ui.FavoritesScreen
import org.koin.core.annotation.Single


@Single
class FavoritesEntryImpl: FavoritesEntry() {

    @Composable
    override fun Start(navigateToPhoto: (String) -> Unit) {
        FavoritesScreen(
            navigateToPhoto = navigateToPhoto
        )
    }
}