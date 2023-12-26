package com.mldz.profile_impl

import androidx.compose.runtime.Composable
import com.mldz.feature.profile.ProfileEntry
import com.mldz.profile_impl.ui.ProfileScreen
import com.mldz.profile_impl.ui.ProfileViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.Factory


@Factory
class ProfileEntryImpl: ProfileEntry() {

    @Composable
    override fun Start(
        navigateToPhoto: (String) -> Unit,
        navigateBack: (() -> Boolean)?
    ) {
        val viewModel = koinViewModel<ProfileViewModel>()
        ProfileScreen(
            viewModel = viewModel,
            navigateToPhoto = navigateToPhoto,
            navigateBack = navigateBack
        )
    }
}