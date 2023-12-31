package com.mldz.feature.photo_feed

import androidx.compose.runtime.Composable
import com.mldz.feature.photo_feed.ui.PhotoFeedScreen
import com.mldz.photo_feed_api.PhotoFeedEntry
import org.koin.core.annotation.Single


@Single
class PhotoFeedEntryImpl: PhotoFeedEntry() {
    @Composable
    override fun Start(
        navigateToPhoto: (String) -> Unit,
        navigateToSearch: () -> Unit
    ) {
        PhotoFeedScreen(
            navigateToPhoto, navigateToSearch
        )
    }
}