package com.mldz.feature.profile

import androidx.compose.runtime.Composable
import com.mldz.core.common.feature.FeatureEntry


abstract class ProfileEntry : FeatureEntry {

    override val featureRoute = "profile_route/"
    val profileIdArg = "profileIdArg"
    val defaultArg = "me"
    val featureRouteMyProfile = "profile_route/$defaultArg"
    val featureRouteArg = "profile_route/{$profileIdArg}"

    @Composable
    abstract fun Start(
        navigateToPhoto: (String) -> Unit,
        navigateBack: (() -> Boolean)?
    )
}