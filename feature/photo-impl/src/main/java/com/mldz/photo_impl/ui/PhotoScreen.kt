package com.mldz.photo_impl.ui

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mldz.core.ui.component.ErrorLoading
import com.mldz.core.ui.component.Loader
import com.mldz.core.ui.component.PhotoCard
import com.mldz.core.ui.icon.Icons
import com.mldz.core.ui.preview.BlackWhiteBackgroundPreview
import com.mldz.photo_api.models.Exif
import com.mldz.photo_api.models.Paths
import com.mldz.photo_api.models.PhotoDetail
import com.mldz.photo_api.models.User
import kotlinx.coroutines.flow.collectLatest
import com.mldz.core.ui.R as uiR


private const val SIDE_MARGINS = 10

@Composable
fun PhotoScreen(
    viewModel: PhotoViewModel,
    navigateBack: () -> Unit,
    navigateToProfile: (String) -> Unit
) {
    val photo by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is PhotoContract.Effect.ShowLiked -> {
                    Toast.makeText(context, uiR.string.liked, Toast.LENGTH_SHORT).show()
                }
                is PhotoContract.Effect.ShowBookmarked -> {
                    Toast.makeText(context, uiR.string.bookmarked, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    PhotoState(
        state = photo,
        navigateBack = navigateBack,
        navigateToProfile = navigateToProfile,
        onShowDetails = { viewModel.setEvent(PhotoContract.Event.OnShowDetails) },
        onLike = { viewModel.setEvent(PhotoContract.Event.OnLike) },
        onBookmark = { viewModel.setEvent(PhotoContract.Event.OnBookmark) },
        onRepeatLoad = { viewModel.setEvent(PhotoContract.Event.OnRepeatLoad) }
    )
}

@Composable
fun PhotoState(
    state: PhotoContract.State,
    navigateBack: () -> Unit,
    navigateToProfile: (String) -> Unit,
    onShowDetails: () -> Unit,
    onLike: () -> Unit,
    onBookmark: () -> Unit,
    onRepeatLoad: () -> Unit
) {
    var appBarTitle = remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            AppBar(
                title = appBarTitle.value,
                isBookmark = state.photo?.isBookmark ?: false,
                navigateBack = navigateBack,
                onBookmark = onBookmark
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
                state.photo != null -> {
                    val title = state.photo.user?.name?.let {
                        stringResource(id = uiR.string.by) + it
                    } ?: stringResource(id = uiR.string.photo)
                    appBarTitle.value = title
                    PhotoView(
                        state = state,
                        onExifClick = onShowDetails,
                        onLike = onLike,
                        onBookmark = onBookmark
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    isBookmark: Boolean,
    navigateBack: () -> Unit,
    onBookmark: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
        ),
        title = {
            Text(
                title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
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
        actions = {
            IconButton(onClick = onBookmark) {
                val iconBookmark = if (isBookmark) Icons.Bookmark else Icons.BookmarkOutlined
                Icon(
                    imageVector = iconBookmark,
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
fun PhotoView(
    state: PhotoContract.State,
    onExifClick: () -> Unit,
    onLike: () -> Unit,
    onBookmark: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = SIDE_MARGINS.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Location(location = state.photo?.location, modifier = Modifier.weight(1f))
            state.photo?.date?.let {
                Date(date = it)
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        PhotoCard(
            url = state.photo?.urls?.regular ?: "",
            onClick = { /*TODO*/ },
        )
        Spacer(modifier = Modifier.size(10.dp))
        PhotoDetails(
            state = state,
            onExifClick = onExifClick
        )
    }
}

@Preview
@BlackWhiteBackgroundPreview
@Composable
fun PhotoDetailsPreview() {
    PhotoDetails(
        state = PhotoContract.State(
            photo = PhotoDetail(
                "",
                Paths("", "", ""),
                10,
                true,
                "title",
                100,
                1000,
                User("", "username"),
                Exif("make", "model", "name", "expTime", "aper", "length", 100),
                "Moscow",
                "07 08 01997",
                "desc"
            )
        ),
        onExifClick = { }
    )
}

@Composable
fun PhotoDetails(
    state: PhotoContract.State,
    onExifClick: () -> Unit
) {
    val photo = state.photo
    photo?.let {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Author(name = photo.user?.name, description = photo.description)
            Spacer(modifier = Modifier.size(20.dp))
            PhotoMainInfo(photo = photo)
            Spacer(modifier = Modifier.size(40.dp))
            photo.exif?.let {
                ExifTitle(isShow = state.showDetails, onClick = onExifClick)
            }
            if (state.showDetails) {
                ExifView(exif = photo.exif)
            }
        }
    }
}

@Composable
fun Author(
    name: String?,
    description: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = uiR.drawable.take_a_photo),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .alpha(0.6f)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column {
            name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PhotoMainInfo(
    photo: PhotoDetail
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ){
        DetailColumn(amount = photo.likes, stringId = uiR.string.likes)
        photo.views?.let {
            DetailColumn(amount = it, stringId = uiR.string.views)
        }
        DetailColumn(amount = photo.downloads, stringId = uiR.string.downloads)
    }
}

@Composable
fun DetailColumn(
    amount: Int,
    @StringRes stringId: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.W800,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(id = stringId),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun Location(location: String?, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        location?.let {
            Icon(
                imageVector = Icons.Place,
                contentDescription = null,
                modifier = Modifier.alpha(0.4f)
            )
            Text(
                text = location,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
            )
        }
    }
}

@Composable
fun Date(date: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Date,
            contentDescription = null,
            modifier = Modifier.alpha(0.4f)
        )
        Text(
            text = date,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
        )
    }
}

@Composable
fun ExifTitle(
    isShow: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
    ) {
        Text(
            text = stringResource(id = uiR.string.exif),
            style = MaterialTheme.typography.titleMedium
        )
        Canvas(modifier = Modifier.weight(1f)) {
            drawLine(
                color = Color.LightGray,
                start = Offset(20f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2f,
            )
        }
        Icon(
            imageVector = Icons.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier.rotate(if (isShow) 180f else 0f)
        )
    }
}

@Preview
@Composable
fun ExifViewPreview() {
    ExifView(exif = Exif("make", "model", "name", "expTime", "aper", "length", 100))
}

@Composable
fun ExifView(
    exif: Exif?
) {
    Column {
        Spacer(modifier = Modifier.size(20.dp))
        exif?.let {
            exif.make?.let {
                ExifDetailText(titleId = uiR.string.exif_make, value = it)
            }
            exif.model?.let {
                ExifDetailText(titleId = uiR.string.exif_model, value = it)
            }
            exif.name?.let {
                ExifDetailText(titleId = uiR.string.exif_name, value = it)
            }
            exif.exposureTime?.let {
                ExifDetailText(titleId = uiR.string.exif_exposure_time, value = it)
            }
            exif.aperture?.let {
                ExifDetailText(titleId = uiR.string.exif_aperture, value = it)
            }
            exif.focalLength?.let {
                ExifDetailText(titleId = uiR.string.exif_focalLength, value = it)
            }
            exif.iso?.let {
                ExifDetailText(titleId = uiR.string.exif_iso, value = it.toString())
            }
        }
    }
}

@Composable
fun ExifDetailText(
    @StringRes titleId: Int,
    value: String
) {
    (stringResource(id = titleId) + value).also {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
