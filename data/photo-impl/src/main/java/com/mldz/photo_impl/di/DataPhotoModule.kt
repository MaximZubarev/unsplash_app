package com.mldz.photo_impl.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mldz.network_api.NetworkApi
import com.mldz.photo_api.models.Photo
import com.mldz.photo_impl.data.PhotoFeedPaging
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan("com.mldz.photo_impl")
class DataPhotoModule {

    @Single
    fun pager(remoteDataSource: NetworkApi): Pager<Int, Photo> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 1),
            pagingSourceFactory = {
                PhotoFeedPaging(remoteDataSource)
            }
        )
    }
}