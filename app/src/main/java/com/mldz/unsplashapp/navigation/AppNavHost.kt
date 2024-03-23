package com.mldz.unsplashapp.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mldz.favorites.FavoritesEntry
import com.mldz.feature.photo.PhotoEntry
import com.mldz.feature.profile.ProfileEntry
import com.mldz.feature.search.SearchEntry
import com.mldz.photo_feed_api.PhotoFeedEntry
import org.koin.compose.rememberKoinInject


const val PHOTO_FEED_GRAPH_ROUTE = "photo_feed_graph_route"
const val FAVORITES_GRAPH_ROUTE = "favorites_graph_route"
const val PROFILE_GRAPH_ROUTE = "profile_graph_route"

private const val TEXT_PLAIN = "text/plain"

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val photoFeed = rememberKoinInject<PhotoFeedEntry>()
    val favorites = rememberKoinInject<FavoritesEntry>()
    val profile = rememberKoinInject<ProfileEntry>()
    val myProfile = rememberKoinInject<ProfileEntry>()
    val photo = rememberKoinInject<PhotoEntry>()
    val search = rememberKoinInject<SearchEntry>()
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = PHOTO_FEED_GRAPH_ROUTE
    ) {
        photoFeedGraph(
            navController = navController,
            photoFeed = photoFeed,
            photoRoute = photo.featureRoute,
            searchRoute = search.featureRoute,
            navigateToPhoto = { }
        )
        favoritesGraph(
            navController = navController,
            favorites = favorites,
            photo = photo
        )
        profileGraph(
            navController = navController,
            profile = myProfile,
            photo = photo
        )
        photoScreen(
            navController = navController,
            photo = photo,
            profileRoute = profile.featureRoute
        )
        searchScreen(
            navController = navController,
            search = search,
            photoRoute = photo.featureRoute
        )
        profileScreen(
            navController = navController,
            profile = myProfile,
            photoRoute = photo.featureRoute
        )
    }
}

fun NavGraphBuilder.photoFeedGraph(
    navController: NavController,
    photoFeed: PhotoFeedEntry,
    photoRoute: String,
    searchRoute: String,
    navigateToPhoto: () -> Unit
) {
    // TODO: parametres
    navigation(startDestination = photoFeed.featureRoute, route = PHOTO_FEED_GRAPH_ROUTE) {
        composable(route = photoFeed.featureRoute) {
            photoFeed.Start(
                navigateToPhoto = { photoId ->
                    navController.openPhoto(photoId, photoRoute)
                },
                navigateToSearch = {
                    navController.navigate(searchRoute)
                }
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
            favorites.Start(
                navigateToPhoto = { photoId ->
                    navController.openPhoto(photoId, photo.featureRoute)
                }
            )
        }
    }
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    profile: ProfileEntry,
    photo: PhotoEntry
) {
    navigation(startDestination = profile.featureRouteMyProfile, route = PROFILE_GRAPH_ROUTE) {
        composable(
            route = profile.featureRouteMyProfile,
            arguments = listOf(
                navArgument(
                    profile.profileIdArg
                ) {
                    defaultValue = profile.defaultArg
                    type = NavType.StringType
                }
            )
        ) {
            profile.Start(
                navigateToPhoto = { photoId ->
                    navController.openPhoto(photoId, photo.featureRoute, false)
                },
                navigateBack = null
            )
        }
    }
}

fun NavController.openPhoto(
    photoId: String,
    route: String,
    canNavigateToProfile: Boolean = true
) {
    this.navigate(
        route = "$route$photoId/$canNavigateToProfile",
    )
}

fun NavController.openProfile(username: String, route: String) {
    this.navigate(
        route = route + username
    )
}

private fun NavController.navigateToShare(title: String?, text: String?, navOptions: NavOptions? = null) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        putExtra(Intent.EXTRA_TITLE, title)
        type = TEXT_PLAIN
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

fun NavGraphBuilder.photoScreen(
    navController: NavController,
    photo: PhotoEntry,
    profileRoute: String
) {
    composable(
        route = photo.featureRouteArg,
        arguments = listOf(
            navArgument(photo.photoIdArg) { type = NavType.StringType },
            navArgument(photo.canNavigateToProfileArg) { type = NavType.BoolType }
        )
    ) {
        val canNavigateToProfile = it.arguments?.getBoolean(photo.canNavigateToProfileArg) ?: true
        photo.Start(
            navigateBack = { navController.popBackStack() },
            navigateToProfile = { username ->
                if (canNavigateToProfile) {
                    navController.openProfile(username, profileRoute)
                } else {
                    navController.popBackStack()
                }
            },
            navigateToShare = { title, link -> navController.navigateToShare(title, link) }
        )
    }
}

fun NavGraphBuilder.searchScreen(
    navController: NavController,
    search: SearchEntry,
    photoRoute: String
) {
    composable(route = search.featureRoute) {
        search.Start(
            navigateToPhoto = { navController.openPhoto(it, photoRoute) },
            navigateBack = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.profileScreen(
    navController: NavController,
    profile: ProfileEntry,
    photoRoute: String
) {
    composable(
        route = profile.featureRouteArg,
        arguments = listOf(
            navArgument(
                profile.profileIdArg
            ) {
                defaultValue = profile.defaultArg
                type = NavType.StringType
            }
        )
    ) {
        profile.Start(
            navigateToPhoto = { photoId ->
                navController.openPhoto(photoId, photoRoute, false)
            },
            navigateBack = { navController.popBackStack() }
        )
    }
}
