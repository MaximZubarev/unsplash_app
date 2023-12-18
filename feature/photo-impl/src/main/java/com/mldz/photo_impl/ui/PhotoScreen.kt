package com.mldz.photo_impl.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier


@Composable
fun PhotoScreen(
    viewModel: PhotoViewModel
) {
    val photoId = viewModel.state.collectAsState()
    Text(text = photoId.value)
}