package com.mldz.photo_api.usecase


interface LikePhotoUseCase {

    suspend operator fun invoke(photoId: String, isLike: Boolean): Boolean

}