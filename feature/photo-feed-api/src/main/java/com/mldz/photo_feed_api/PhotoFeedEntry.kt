package com.mldz.photo_feed_api

import androidx.compose.runtime.Composable


const val PHOTO_FEED_ROUTE: String = "photo_feed_route"

abstract class PhotoFeedEntry {

    val featureRoute = PHOTO_FEED_ROUTE

    @Composable
    abstract fun start(
        navigateToPhoto: (String) -> Unit,
        navigateToSearch: () -> Unit,
    )
}