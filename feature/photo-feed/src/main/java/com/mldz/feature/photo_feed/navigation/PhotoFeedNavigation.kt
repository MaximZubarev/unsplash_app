package com.mldz.feature.photo_feed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mldz.feature.photo_feed.PhotoFeedScreen


const val photoFeedNavigationRoute = "photo_feed_route"

fun NavGraphBuilder.photoFeedRoute() {
    composable(route = photoFeedNavigationRoute) {
        PhotoFeedScreen()
    }
}

fun NavController.navigateToPhotoFeed(navOptions: NavOptions? = null) {
    this.navigate(route = photoFeedNavigationRoute, navOptions = navOptions)
}