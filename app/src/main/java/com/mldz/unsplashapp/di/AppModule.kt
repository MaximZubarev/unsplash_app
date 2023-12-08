package com.mldz.unsplashapp.di

import com.mldz.core.common.di.DispatchersKoinModule
import com.mldz.feature.photo_feed.di.FeaturePhotoFeedModule
import com.mldz.network_impl.di.CoreNetworkModule
import com.mldz.photo_impl.di.DataPhotoModule
import org.koin.dsl.module
import org.koin.ksp.generated.module


val appModule = module {
    includes(
        DispatchersKoinModule().module,
        CoreNetworkModule().module,
        DataPhotoModule().module,
        FeaturePhotoFeedModule().module
    )
}