package com.mldz.feature.photo

import androidx.compose.runtime.Composable


abstract class PhotoEntry {

    val photoIdArg = "photoIdArg"
    val featureRoute = "photo_route/"
    val featureRouteArg = "photo_route/{$photoIdArg}"

    @Composable
    abstract fun Start(photoId: String)
}