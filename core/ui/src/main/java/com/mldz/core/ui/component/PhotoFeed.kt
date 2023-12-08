package com.mldz.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey


@Composable
fun <T : Any> PhotoFeed(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    spaceBetweenPhotos: Int = 10,
    columns: Int = 2,
    photoCard: @Composable (item: T?) -> Unit,
    photoCardLoader: @Composable () -> Unit,
    key: ((index: Int) -> Any)? = null,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(horizontal = spaceBetweenPhotos.dp),
        columns = StaggeredGridCells.Fixed(columns),
        verticalItemSpacing = spaceBetweenPhotos.dp,
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenPhotos.dp),
        contentPadding = WindowInsets.systemBars.only(WindowInsetsSides.Bottom).asPaddingValues(),
        content = {
            items(count = items.itemCount, key = key) { index ->
                photoCard(items[index])
            }
            if (items.loadState.append == LoadState.Loading) {
                repeat(2) {
                    item {
                        photoCardLoader()
                    }
                }
            }
        })
}

@Preview
@Composable
private fun PhotoFeedPreview() {
//    PhotoFeed(
//        items = LazyPagingItems<Int>(),
//        photoCard = { PhotoCard(url = "") }
//    )
}