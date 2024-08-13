package com.mldz.photo_impl.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mldz.feature.photo.PhotoEntry


fun NavController.openPhoto(
    photoId: String,
    route: String,
    canNavigateToProfile: Boolean = true
) {
    navigate(
        route = "$route$photoId/$canNavigateToProfile",
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.photoScreen(
    navController: NavController,
    photo: PhotoEntry,
    navigateToProfile: (String) -> Unit,
    navigateToShare: (String?, String?) -> Unit,
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
                    navigateToProfile(username)
                } else {
                    navController.popBackStack()
                }
            },
            navigateToShare = { title, link -> navigateToShare(title, link) },
        )
    }
}