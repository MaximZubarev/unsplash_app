package com.mldz.feature.photo_feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import com.mldz.core.ui.component.Error
import com.mldz.core.ui.component.Loader
import com.mldz.core.ui.component.PhotoCard
import com.mldz.core.ui.component.PhotoFeed
import org.koin.androidx.compose.koinViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.mldz.core.ui.component.PhotoCardLoader
import com.mldz.photo_api.models.Photo
import com.mldz.core.ui.R as uiR


private const val SPACE_BETWEEN_PHOTOS = 10

@Composable
internal fun PhotoFeedScreen(
    viewModel: PhotoFeedViewModel = koinViewModel()
) {
    val uiState: PhotoFeedUiState by viewModel.uiState.collectAsStateWithLifecycle()
    var items: LazyPagingItems<Photo>? = null
    if (uiState is PhotoFeedUiState.Success) {
        items = (uiState as PhotoFeedUiState.Success).list.collectAsLazyPagingItems()
    }
    ListPhotoScreen(uiState = uiState, items = items)
}

@Composable
internal fun ListPhotoScreen(
    uiState: PhotoFeedUiState,
    items: LazyPagingItems<Photo>?
) {
    Column {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = SPACE_BETWEEN_PHOTOS.dp,
                    end = SPACE_BETWEEN_PHOTOS.dp,
                )
        )
        Spacer(modifier = Modifier.size(10.dp))
//        SearchWidget(onText = {}, modifier = Modifier.padding(horizontal = SPACE_BETWEEN_PHOTOS.dp))
        Spacer(modifier = Modifier.size(20.dp))
        when (uiState) {
            is PhotoFeedUiState.Loading -> Loader(
                modifier = Modifier.fillMaxSize()
            )
            is PhotoFeedUiState.Error -> Error(
                text = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            is PhotoFeedUiState.Success -> {
                items?.let {
                    PhotoFeed(
                        items = items,
                        spaceBetweenPhotos = SPACE_BETWEEN_PHOTOS,
                        photoCard = { photo ->
                            photo?.let {
                                PhotoCard(
                                    url = photo.urls.regular,
                                    modifier = Modifier.fillMaxWidth(),
                                    contentDescription = photo.id
                                )
                            }
                        },
                        photoCardLoader = {
                            PhotoCardLoader(
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        key = items.itemKey { it.id }
                    )
                }
            }
        }
//        uiState.error?.let { error ->
//            Spacer(modifier = Modifier.weight(1f))
//            Error(
//                text = error,
//                modifier = Modifier
//                    .padding(horizontal = SPACE_BETWEEN_PHOTOS.dp)
//                    .navigationBarsPadding()
//            )
//        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = uiR.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = uiR.string.title_desc),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

