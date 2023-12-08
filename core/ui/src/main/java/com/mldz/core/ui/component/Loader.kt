package com.mldz.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.mldz.core.ui.preview.DevicePreviews
import com.mldz.core.ui.preview.PhonePreview
import com.mldz.core.ui.preview.ThemePreviews
import com.mldz.core.ui.preview.WhiteBackgroundPreview


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
@WhiteBackgroundPreview
@Composable
private fun LoaderPreview() {
    Loader(
        modifier = Modifier.fillMaxSize()
    )
}