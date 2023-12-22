package com.mldz.photo_api.domain


interface BookmarkPhotoUseCase {

    suspend fun invoke(id: String, url: String, isBookmark: Boolean): Boolean
}