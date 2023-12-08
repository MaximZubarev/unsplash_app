package com.mldz.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mldz.feature.profile.ProfileScreen


const val profileNavigationRoute = "profile_route"

fun NavGraphBuilder.profileRoute() {
    composable(route = profileNavigationRoute) {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(route = profileNavigationRoute, navOptions = navOptions)
}