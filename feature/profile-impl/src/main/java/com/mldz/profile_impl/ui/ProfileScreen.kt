package com.mldz.profile_impl.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import coil.compose.AsyncImage
import com.mldz.core.ui.component.ErrorLoading
import com.mldz.core.ui.component.Loader
import com.mldz.core.ui.component.Location
import com.mldz.core.ui.icon.Icons
import com.mldz.core.ui.preview.BlackWhiteBackgroundPreview
import com.mldz.profile_api.models.Profile
import kotlinx.coroutines.flow.Flow
import com.mldz.core.ui.R as uiR
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.mldz.core.ui.R
import com.mldz.core.ui.component.PhotoCard
import com.mldz.core.ui.component.PhotoCardLoader
import com.mldz.core.ui.component.PhotoFeed


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navigateToPhoto: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileScreen(
        state = state,
        navigateToPhoto = navigateToPhoto,
        navigateBack = navigateBack,
        onRepeatLoad = {
            viewModel.setEvent(ProfileContract.Event.OnRepeatLoad)
        }
    )
}

@Composable
fun ProfileScreen(
    state: ProfileContract.State,
    navigateToPhoto: (String) -> Unit,
    navigateBack: () -> Unit,
    onRepeatLoad: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                title = state.profile?.username,
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                state.isLoading -> Loader(modifier = Modifier.fillMaxSize())
                state.error != null -> {
                    ErrorLoading(
                        message = state.error,
                        onRepeat = onRepeatLoad,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                state.profile != null -> {
                    Content(
                        profile = state.profile,
                        navigateToPhoto = navigateToPhoto
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String?,
    navigateBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
        ),
        title = {
            title?.let {
                Text(
                    text = stringResource(id = uiR.string.username) + title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Back,
                    contentDescription = null
                )
            }
        },
        windowInsets = WindowInsets(
            top = 0.dp
        ),
    )
}

@Preview
@BlackWhiteBackgroundPreview
@Composable
fun UserPreview() {
//    User(
//        profile = Profile(),
//        modifier = Modifier.fillMaxWidth()
//    )
}

@Composable
fun Content(
    profile: Profile,
    navigateToPhoto: (String) -> Unit
) {
    profile.photosFlow?.let {
        val photos = it.collectAsLazyPagingItems()
        when (photos.loadState.refresh) {
            is LoadState.Error -> {
                ErrorLoading(
                    message = (photos.loadState.refresh as LoadState.Error).error.message,
                    modifier = Modifier.fillMaxSize(),
                    onRepeat = { }
                )
            }
            else -> {
                PhotoFeed(
                    items = photos,
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
                    header = { User(profile = profile) },
                    emptyState = {
                        if (photos.itemCount == 0 && photos.loadState.refresh is LoadState.NotLoading) {
                            EmptyPhotos(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = 20.dp)
                            )
                        }
                    },
                    key = photos.itemKey { photo -> photo.id }
                )
            }
        }
    }
}

@Composable
fun User(
    profile: Profile
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = profile.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = profile.name,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(10.dp))
        profile.location?.let {
            Location(location = it, modifier = Modifier.fillMaxWidth())
        }
        profile.bio?.let {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        UserStats(profile = profile, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Composable
fun UserStats(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        StatItem(
            value = profile.likes,
            labelId = uiR.string.likes_label,
            modifier = Modifier.weight(1f)
        )
        StatItem(
            value = profile.totalPhotos,
            labelId = uiR.string.photos,
            modifier = Modifier.weight(1f)
        )
        StatItem(
            value = profile.followers,
            labelId = uiR.string.followers,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatItem(
    value: Int,
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W800,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
@Preview
fun EmptyPhotosPreview() {
    EmptyPhotos()
}

@Composable
fun EmptyPhotos(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_photo),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.no_photos),
            style = MaterialTheme.typography.titleMedium
        )
    }
}