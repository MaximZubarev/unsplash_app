package com.mldz.core.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
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


@Composable
fun PhotoCard(
    url: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    AsyncImage(
        model = url,
        placeholder = placeholder(
            debugPreview = R.drawable.image_placeholder_2,
            prodPreview = R.drawable.image_placeholder_1
        ),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    )
}

@Composable
private fun placeholder(
    @DrawableRes debugPreview: Int,
    @DrawableRes prodPreview: Int
) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        painterResource(id = prodPreview)
    }

@Preview
@Composable
private fun PhotoCardPreview() {
    PhotoCard(
        url = "",
        contentDescription = "",
        onClick = { }
    )
}