package com.mldz.core.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mldz.core.ui.R
import com.mldz.core.ui.preview.WhiteBackgroundPreview


@Composable
fun PhotoCard(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    AsyncImage(
        model = url,
        placeholder = debugPlaceholder(debugPreview = R.drawable.image_placeholder_2),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(8.dp))
    )
}

@Composable
private fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

@Preview
@Composable
private fun PhotoCardPreview() {
    PhotoCard(
        url = "",
        contentDescription = ""
    )
}