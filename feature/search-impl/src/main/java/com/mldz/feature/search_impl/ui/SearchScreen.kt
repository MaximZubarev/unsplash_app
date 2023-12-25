package com.mldz.feature.search_impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.mldz.core.ui.R
import com.mldz.core.ui.component.ErrorLoading
import com.mldz.core.ui.component.Loader
import com.mldz.core.ui.component.PhotoCard
import com.mldz.core.ui.component.PhotoCardLoader
import com.mldz.core.ui.component.PhotoFeed
import com.mldz.core.ui.icon.Icons
import com.mldz.core.ui.preview.BlackWhiteBackgroundPreview
import com.mldz.core.ui.preview.PhonePreview
import org.koin.androidx.compose.koinViewModel
import com.mldz.core.ui.R as uiR


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navigateToPhoto: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SearchScreen(
        state = state,
        navigateToPhoto = navigateToPhoto,
        navigateBack = navigateBack,
        onSearch = {
            viewModel.setEvent(SearchContract.Event.OnSearch(it))
        },
        onRepeatLoad = {
            viewModel.setEvent(SearchContract.Event.OnRepeatLoad)
        }
    )
}

@Composable
fun SearchScreen(
    state: SearchContract.State,
    navigateToPhoto: (String) -> Unit,
    navigateBack: () -> Unit,
    onSearch: (String) -> Unit,
    onRepeatLoad: () -> Unit
) {
    Column {
        AppBar(
            searchQuery = state.searchQuery,
            onSearch = onSearch,
            navigateBack = navigateBack,
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        when {
            state.idle -> StartState(modifier = Modifier.fillMaxSize())
            state.isEmpty -> EmptyState(modifier = Modifier.fillMaxSize())
            state.isLoading -> Loader(modifier = Modifier.fillMaxSize())
            state.error != null -> ErrorLoading(
                message = state.error,
                onRepeat = onRepeatLoad
            )
            else -> SearchResult(state, navigateToPhoto, onRepeatLoad)
        }
    }
}

@Composable
fun AppBar(
    searchQuery: String,
    onSearch: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = 10.dp, end = 10.dp)
    ) {
        IconButton(onClick = navigateBack) {
            Icon(
                imageVector = Icons.Back,
                contentDescription = "Localized description"
            )
        }
        SearchTextField(
            searchQuery = searchQuery,
            onSearch = onSearch,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    searchQuery: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = searchQuery,
        onValueChange = onSearch,
        modifier = modifier,
        placeholder = {
            Text(text = stringResource(id = uiR.string.search_hint))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearch("") }) {
                    Icon(
                        imageVector = Icons.Clear,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
    )
}

@Preview
@BlackWhiteBackgroundPreview
@PhonePreview
@Composable
fun SearchTextFieldPreview() {
    AppBar(
        searchQuery = "test",
        onSearch = {},
        navigateBack = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun StartState(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.empty),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Preview
@Composable
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
            painter = painterResource(id = R.drawable.empty),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.empty_search),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = R.string.empty_search_desc),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(40.dp))
    }
}

@Composable
fun SearchResult(
    state: SearchContract.State,
    navigateToPhoto: (String) -> Unit,
    onRepeatLoad: () -> Unit
) {
    val items = state.searchResult.collectAsLazyPagingItems()
    when (items.loadState.refresh) {
        is LoadState.Error -> {
            ErrorLoading(
                message = (items.loadState.refresh as LoadState.Error).error.message,
                modifier = Modifier.fillMaxSize(),
                onRepeat = onRepeatLoad
            )
        }
        is LoadState.Loading -> {
            if (state.isLoading) {
                Loader(modifier = Modifier.fillMaxSize())
            } else {
                StartState(modifier = Modifier.fillMaxSize())
            }
        }
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