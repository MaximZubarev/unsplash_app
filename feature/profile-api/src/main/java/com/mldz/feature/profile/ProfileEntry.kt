package com.mldz.feature.profile

import androidx.compose.runtime.Composable


abstract class ProfileEntry {

    val featureRoute = "profile_route"

    @Composable
    abstract fun start(
        profileId: String,
        navigateToPhoto: (String) -> Unit
    )
}