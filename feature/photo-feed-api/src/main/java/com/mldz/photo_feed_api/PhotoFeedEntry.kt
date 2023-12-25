package com.mldz.photo_feed_api

import androidx.compose.runtime.Composable
import com.mldz.core.common.feature.FeatureEntry


abstract class PhotoFeedEntry : FeatureEntry {

    override val featureRoute = "photo_feed_route"

    @Composable
    abstract fun Start(
        navigateToPhoto: (String) -> Unit,
        navigateToSearch: () -> Unit,
    )
}