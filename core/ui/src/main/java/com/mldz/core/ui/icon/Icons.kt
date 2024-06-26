package com.mldz.core.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.vector.ImageVector


object Icons {
    val PhotoFeed = Icons.Outlined.PhotoLibrary
    val PhotoFeedSelected = Icons.Default.PhotoLibrary
    val Bookmarks = Icons.Outlined.Bookmarks
    val BookmarksSelected = Icons.Default.Bookmarks
    val Profile = Icons.Outlined.Person
    val ProfileSelected = Icons.Default.Person
    val Search = Icons.Default.Search
    val Back = Icons.Default.ArrowBack
    val Place = Icons.Outlined.Place
    val Date = Icons.Outlined.CalendarMonth
    val ArrowDropDown = Icons.Default.ArrowDropDown
    val FavoriteOutlined = Icons.Outlined.FavoriteBorder
    val Favorite = Icons.Default.Favorite
    val Bookmark = Icons.Default.Bookmark
    val BookmarkOutlined = Icons.Outlined.BookmarkAdd
    val Clear = Icons.Outlined.Clear
    val Share = Icons.Default.IosShare
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}