package com.mldz.unsplashapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mldz.feature.search.SearchEntry
import com.mldz.unsplashapp.navigation.AppNavHost
import com.mldz.unsplashapp.navigation.BottomNavigation
import org.koin.compose.koinInject


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val searchRoute: SearchEntry = koinInject()
    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route != searchRoute.featureRoute
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    navController = navController
                )
            }
        }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}