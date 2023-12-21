package com.mldz.photo_api.domain


interface LikePhotoUseCase {

    suspend fun invoke(photoId: String, isLike: Boolean): Boolean

}