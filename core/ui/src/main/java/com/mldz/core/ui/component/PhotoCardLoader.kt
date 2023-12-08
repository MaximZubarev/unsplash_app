package com.mldz.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun PhotoCardLoader(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}