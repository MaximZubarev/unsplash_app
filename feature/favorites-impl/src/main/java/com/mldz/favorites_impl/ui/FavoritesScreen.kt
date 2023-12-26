package com.mldz.favorites_impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.mldz.core.ui.R
import com.mldz.core.ui.component.PhotoCard
import com.mldz.core.ui.component.PhotoCardLoader
import com.mldz.core.ui.component.PhotoFeed
import com.mldz.photo_api.models.Photo
import org.koin.androidx.compose.koinViewModel
import com.mldz.core.ui.R as uiR


@Composable
fun FavoritesScreen(
    navigateToPhoto: (String) -> Unit,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val favorites = state.list.collectAsLazyPagingItems()
    Favorites(state = state, items = favorites, navigateToPhoto = navigateToPhoto)
}

@Composable
fun Favorites(
    state: FavoritesContract.State,
    items: LazyPagingItems<Photo>,
    navigateToPhoto: (String) -> Unit
) {
    Column {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        when {
            state.isLoading -> {}
            state.error != null -> {}
            else -> {
                if (items.itemCount == 0) {
                    EmptyState(modifier = Modifier.fillMaxSize())
                } else {
                    PhotoFeed(
                        items = items,
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
            }
        }
    }
}

@Composable
fun Title(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = uiR.string.bookmarks_title),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
@Preview
fun EmptyStatePreview() {
    EmptyState()
}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_photo),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.bookmarks_no),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = R.string.bookmarks_save),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(40.dp))
    }
}