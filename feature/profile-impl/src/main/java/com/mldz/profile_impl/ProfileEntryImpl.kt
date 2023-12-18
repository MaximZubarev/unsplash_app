package com.mldz.profile_impl

import androidx.compose.runtime.Composable
import com.mldz.feature.profile.ProfileEntry
import com.mldz.profile_impl.ui.ProfileScreen
import org.koin.core.annotation.Single


@Single
class ProfileEntryImpl: ProfileEntry() {

    @Composable
    override fun start(
        profileId: String,
        navigateToPhoto: (String) -> Unit
    ) {
        ProfileScreen()
    }
}