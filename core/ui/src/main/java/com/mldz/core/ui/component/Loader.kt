package com.mldz.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mldz.core.ui.preview.PhonePreview
import com.mldz.core.ui.preview.BlackWhiteBackgroundPreview


@Composable
fun Loader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@PhonePreview
@BlackWhiteBackgroundPreview
@Composable
private fun LoaderPreview() {
    Loader(
        modifier = Modifier.fillMaxSize()
    )
}