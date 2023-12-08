package com.mldz.unsplashapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mldz.favorites.navigation.favoritesRoute
import com.mldz.feature.photo_feed.navigation.photoFeedNavigationRoute
import com.mldz.feature.photo_feed.navigation.photoFeedRoute
import com.mldz.feature.profile.navigation.profileRoute


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = photoFeedNavigationRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            photoFeedRoute()
            favoritesRoute()
            profileRoute()
        }
    }
}