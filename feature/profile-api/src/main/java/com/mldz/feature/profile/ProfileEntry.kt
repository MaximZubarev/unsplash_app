package com.mldz.feature.profile

import androidx.compose.runtime.Composable
import com.mldz.core.common.feature.FeatureEntry


abstract class ProfileEntry : FeatureEntry {

    override val featureRoute = "profile_route/me"
    val featureRouteUser = "profile_route/"
    val profileIdArg = "profileIdArg"
    val featureRouteArg = "profile_route/{$profileIdArg}"

    @Composable
    abstract fun Start(
        username: String,
        navigateToPhoto: (String) -> Unit,
        navigateBack: () -> Unit
    )
}