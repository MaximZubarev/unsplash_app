package com.mldz.photo_impl

import androidx.compose.runtime.Composable
import com.mldz.feature.photo.PhotoEntry
import com.mldz.photo_impl.ui.PhotoScreen
import com.mldz.photo_impl.ui.PhotoViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.Single


@Single
internal class PhotoEntryImpl: PhotoEntry() {

    @Composable
    override fun Start(
        photoId: String,
        navigateBack: () -> Unit,
        navigateToProfile: (String) -> Unit
    ) {
        val viewModel = koinViewModel<PhotoViewModel>()
        PhotoScreen(
            viewModel = viewModel,
            navigateBack = navigateBack,
            navigateToProfile = navigateToProfile
        )
    }
}
