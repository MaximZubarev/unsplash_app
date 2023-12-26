package com.mldz.photo_api.usecase

import com.mldz.photo_api.models.PhotoDetail
import kotlinx.coroutines.flow.Flow


interface GetPhotoByIdUseCase {

    operator fun invoke(photoId: String): Flow<PhotoDetail>
}