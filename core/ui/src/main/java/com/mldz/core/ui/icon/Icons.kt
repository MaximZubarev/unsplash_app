package com.mldz.core.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.ui.graphics.vector.ImageVector


object Icons {
    val PhotoFeed = Icons.Outlined.PhotoLibrary
    val PhotoFeedSelected = Icons.Default.PhotoLibrary
    val Bookmarks = Icons.Outlined.Bookmarks
    val BookmarksSelected = Icons.Default.Bookmarks
    val Profile = Icons.Outlined.Person
    val ProfileSelected = Icons.Default.Person
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}