package com.mldz.feature.photo

import androidx.compose.runtime.Composable


abstract class PhotoEntry {

    val photoIdArg = "photoIdArg"
    val canNavigateToProfileArg = "canNavigateToProfileArg"
    val featureRoute = "photo_route/"
    val featureRouteArg = "photo_route/{$photoIdArg}/{$canNavigateToProfileArg}"

    @Composable
    abstract fun Start(
        photoId: String,
        navigateBack: () -> Unit,
        navigateToProfile: (String) -> Unit
    )
}