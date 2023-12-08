package com.mldz.unsplashapp.ui

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.mldz.core.ui.icon.Icon
import com.mldz.core.ui.icon.Icons
import com.mldz.core.ui.theme.UnsplashAppTheme
import com.mldz.favorites.navigation.navigateToFavorites
import com.mldz.feature.photo_feed.navigation.navigateToPhotoFeed
import com.mldz.feature.photo_feed.navigation.photoFeedNavigationRoute
import com.mldz.feature.profile.navigation.navigateToProfile
import com.mldz.unsplashapp.UnsplashApp
import kotlin.math.log
import com.mldz.core.ui.R as uiR


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val titleTextId: Int
) {
    PHOTO_FEED(
        selectedIcon = Icon.ImageVectorIcon(Icons.PhotoFeedSelected),
        unselectedIcon = Icon.ImageVectorIcon(Icons.PhotoFeed),
        titleTextId = uiR.string.feed
    ),
    FAVORITES(
        selectedIcon = Icon.ImageVectorIcon(Icons.BookmarksSelected),
        unselectedIcon = Icon.ImageVectorIcon(Icons.Bookmarks),
        titleTextId = uiR.string.saved
    ),
    PROFILE(
        selectedIcon = Icon.ImageVectorIcon(Icons.ProfileSelected),
        unselectedIcon = Icon.ImageVectorIcon(Icons.Profile),
        titleTextId = uiR.string.profile
    )
}

private val screens = TopLevelDestination.values().asList()

@Composable
fun BottomNavigation(
    navController: NavController
) {
    NavigationBar(
        containerColor = Color.Transparent
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { destination ->
            val selected = currentDestination?.hierarchy?.any {
                it.route?.contains(destination.name, true) ?: false
            } ?: false
            BottomNavigationItem(
                selected = selected,
                titleId = destination.titleTextId,
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )
                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                onClick = { onNavigateToDestination(navController, destination) }
            )
        }
    }
}

private fun onNavigateToDestination(
    navController: NavController,
    destination: TopLevelDestination
) {
    val topLevelNavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    when (destination) {
        TopLevelDestination.PHOTO_FEED -> navController.navigateToPhotoFeed(topLevelNavOptions)
        TopLevelDestination.FAVORITES -> navController.navigateToFavorites(topLevelNavOptions)
        TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
    }
}

@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    titleId: Int,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        icon = if (selected) selectedIcon else icon,
        onClick = onClick,
        label = { Text(stringResource(id = titleId)) }
    )
}