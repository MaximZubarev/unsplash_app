package com.mldz.unsplashapp.di

import com.mldz.core.common.di.DispatchersKoinModule
import com.mldz.core.db_impl.di.CoreDaosKoinModule
import com.mldz.core.db_impl.di.CoreDatabaseKoinModule
import com.mldz.favorites_impl.di.FeatureFavoritesModule
import com.mldz.feature.photo_feed.di.FeaturePhotoFeedModule
import com.mldz.feature.search_impl.di.FeatureSearchModule
import com.mldz.network_impl.di.CoreNetworkModule
import com.mldz.photo_impl.di.DataPhotoModule
import com.mldz.photo_impl.di.FeaturePhotoModule
import com.mldz.profile_impl.di.FeatureProfileModule
import org.koin.dsl.module
import org.koin.ksp.generated.module


val appModule = module {
    includes(
        DispatchersKoinModule().module,
        CoreNetworkModule().module,
        CoreDatabaseKoinModule().module,
        CoreDaosKoinModule().module,
        DataPhotoModule().module,
        FeaturePhotoFeedModule().module,
        FeatureFavoritesModule().module,
        FeatureProfileModule().module,
        FeaturePhotoModule().module,
        FeatureSearchModule().module,
    )
}