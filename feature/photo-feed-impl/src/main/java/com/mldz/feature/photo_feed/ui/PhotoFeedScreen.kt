package com.mldz.feature.photo_feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.mldz.core.ui.component.Error
import com.mldz.core.ui.component.ErrorLoading
import com.mldz.core.ui.component.Loader
import com.mldz.core.ui.component.PhotoCard
import com.mldz.core.ui.component.PhotoCardLoader
import com.mldz.core.ui.component.PhotoFeed
import com.mldz.core.ui.icon.Icons
import com.mldz.photo_api.models.Photo
import org.koin.androidx.compose.koinViewModel
import com.mldz.core.ui.R as uiR


private const val SPACE_BETWEEN_PHOTOS = 10

@Composable
internal fun PhotoFeedScreen(
    navigateToPhoto: (String) -> Unit,
    navigateToSearch: () -> Unit,
    viewModel: PhotoFeedViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListPhotoScreen(
        navigateToPhoto = navigateToPhoto,
        navigateToSearch = navigateToSearch,
        uiState = uiState.state,
        repeatLoad = { viewModel.setEvent(PhotoFeedContract.Event.OnRepeatLoad) }
    )
}

@Composable
internal fun ListPhotoScreen(
    navigateToPhoto: (String) -> Unit,
    navigateToSearch: () -> Unit,
    repeatLoad: () -> Unit,
    uiState: PhotoFeedContract.PhotoFeedUiState
) {
    Column {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = SPACE_BETWEEN_PHOTOS.dp,
                    end = SPACE_BETWEEN_PHOTOS.dp,
                ),
            onSearchClick = navigateToSearch
        )
        Spacer(modifier = Modifier.size(30.dp))
        when (uiState) {
            is PhotoFeedContract.PhotoFeedUiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
            is PhotoFeedContract.PhotoFeedUiState.Error -> ErrorAppend(uiState.message)
            is PhotoFeedContract.PhotoFeedUiState.Success -> {
                val items = uiState.list.collectAsLazyPagingItems()
                Box {
                    when (items.loadState.refresh) {
                        is LoadState.Error -> {
                            ErrorLoading(
                                message = (items.loadState.refresh as LoadState.Error).error.message,
                                modifier = Modifier.fillMaxSize(),
                                onRepeat = repeatLoad
                            )
                        }
                        is LoadState.Loading -> Loader(modifier = Modifier.fillMaxSize())
                        else -> {
                            Content(items = items, navigateToPhoto = navigateToPhoto)
                        }
                    }
                    if (items.loadState.append is LoadState.Error) {
                        ErrorAppend((items.loadState.append as LoadState.Error).error.message)
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
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
        Box(
            modifier = Modifier
                .clickable { onSearchClick() }
        ) {
            Icon(
                imageVector = Icons.Search,
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun ErrorAppend(
    message: String?
) {
    Error(
        text = message ?: "",
        modifier = Modifier
            .padding(horizontal = SPACE_BETWEEN_PHOTOS.dp)
            .navigationBarsPadding()
    )
}

@Composable
fun Content(
    items: LazyPagingItems<Photo>,
    navigateToPhoto: (String) -> Unit
) {
    PhotoFeed(
        items = items,
        spaceBetweenPhotos = SPACE_BETWEEN_PHOTOS,
        photoCard = { photo ->
            photo?.let {
                PhotoCard(
                    url = photo.url,
                    onClick = { navigateToPhoto(photo.id) },
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
