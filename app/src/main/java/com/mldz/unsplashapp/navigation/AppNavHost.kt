package com.mldz.unsplashapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mldz.favorites.FavoritesEntry
import com.mldz.feature.photo.PhotoEntry
import com.mldz.feature.profile.ProfileEntry
import com.mldz.photo_feed_api.PhotoFeedEntry
import org.koin.compose.rememberKoinInject


const val PHOTO_FEED_GRAPH_ROUTE = "photo_feed_graph_route"
const val SEARCH_GRAPH_ROUTE = "search_graph_route"
const val FAVORITES_GRAPH_ROUTE = "favorites_graph_route"
const val PROFILE_GRAPH_ROUTE = "profile_graph_route"

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val photoFeed = rememberKoinInject<PhotoFeedEntry>()
    val favorites = rememberKoinInject<FavoritesEntry>()
    val profile = rememberKoinInject<ProfileEntry>()
    val photo = rememberKoinInject<PhotoEntry>()
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = PHOTO_FEED_GRAPH_ROUTE
    ) {
        photoFeedGraph(
            navController = navController,
            photoFeed = photoFeed,
            photo = photo
        )
        favoritesGraph(
            navController = navController,
            favorites = favorites,
            photo = photo
        )
        profileGraph(
            navController = navController,
            profile = profile,
            photo = photo
        )
        photoScreen(photo = photo)
    }
}

fun NavGraphBuilder.photoFeedGraph(
    navController: NavController,
    photoFeed: PhotoFeedEntry,
    photo: PhotoEntry
) {
    navigation(startDestination = photoFeed.featureRoute, route = PHOTO_FEED_GRAPH_ROUTE) {
        composable(route = photoFeed.featureRoute) {
            photoFeed.start(
                navigateToPhoto = { photoId ->
                    navController.openPhoto(photoId, photo.featureRoute)
                },
                navigateToSearch = { }
            )
        }
    }
}

fun NavGraphBuilder.favoritesGraph(
    navController: NavController,
    favorites: FavoritesEntry,
    photo: PhotoEntry
) {
    navigation(startDestination = favorites.featureRoute, route = FAVORITES_GRAPH_ROUTE) {
        composable(route = favorites.featureRoute) {
            favorites.start(
                navigateToPhoto = { photoId ->
                    navController.openPhoto(photoId, photo.featureRoute)
                },
            )
        }
    }
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    profile: ProfileEntry,
    photo: PhotoEntry
) {
    navigation(startDestination = profile.featureRoute, route = PROFILE_GRAPH_ROUTE) {
        composable(route = profile.featureRoute) {
            profile.start(
                profileId = "",
                navigateToPhoto = { photoId ->
                    navController.openPhoto(photoId, photo.featureRoute)
                },
            )
        }
    }
}

fun NavController.openPhoto(photoId: String, route: String) {
    this.navigate(
        route = route + photoId,
    )
}

fun NavGraphBuilder.photoScreen(photo: PhotoEntry) {
    composable(
        route = photo.featureRouteArg,
        arguments = listOf(navArgument(photo.photoIdArg) { type = NavType.StringType })
    ) {
        val photoId = it.arguments?.getString(photo.photoIdArg) ?: ""
        photo.start(photoId = photoId)
    }
}