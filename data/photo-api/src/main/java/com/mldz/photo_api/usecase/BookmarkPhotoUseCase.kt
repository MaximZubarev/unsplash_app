package com.mldz.photo_api.usecase


interface BookmarkPhotoUseCase {

    suspend operator fun invoke(id: String, url: String, isBookmark: Boolean): Boolean
}