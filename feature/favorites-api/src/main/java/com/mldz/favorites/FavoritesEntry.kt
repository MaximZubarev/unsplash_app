package com.mldz.favorites

import androidx.compose.runtime.Composable
import com.mldz.core.common.feature.FeatureEntry


abstract class FavoritesEntry : FeatureEntry {

    override val featureRoute = "favorites_route"

    @Composable
    abstract fun Start(
        navigateToPhoto: (String) -> Unit
    )
}