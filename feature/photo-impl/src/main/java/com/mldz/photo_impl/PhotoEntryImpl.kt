package com.mldz.photo_impl

import androidx.compose.runtime.Composable
import com.mldz.feature.photo.PhotoEntry
import com.mldz.photo_impl.ui.PhotoScreen
import com.mldz.photo_impl.ui.PhotoViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single
import org.koin.core.parameter.parametersOf


@Single
internal class PhotoEntryImpl: PhotoEntry() {

    @Composable
    override fun start(photoId: String) {
        val viewModel = koinViewModel<PhotoViewModel>()
        PhotoScreen(viewModel)
    }
}
