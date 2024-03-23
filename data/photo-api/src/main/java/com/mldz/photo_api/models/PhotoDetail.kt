package com.mldz.photo_api.models

import com.mldz.network_api.models.Exif
import com.mldz.network_api.models.PhotoDetailNetworkModel


data class PhotoDetail(
    val id: String,
    val urls: Paths,
    val likes: Int,
    val likedByUser: Boolean,
    val title: String?,
    val views: Int?,
    val downloads: Int,
    val user: User?,
    val exif: com.mldz.photo_api.models.Exif?,
    val location: String?,
    val date: String?,
    val description: String?,
    val isBookmark: Boolean = false,
    val link: String?
)

data class User(
    val id: String?,
    val name: String?,
    val username: String?,
    val userPhoto: String?
)

data class Exif(
    val make: String?,
    val model: String?,
    val name: String?,
    val exposureTime: String?,
    val aperture: String?,
    val focalLength: String?,
    val iso: Int?
)

fun toPhotoDetail(photoApi: PhotoDetailNetworkModel, isBookmark: Boolean, dateFormatter: (String?) -> String?): PhotoDetail {
    return PhotoDetail(
        id = photoApi.id,
        urls = Paths(
            raw = photoApi.urls.raw,
            regular = photoApi.urls.regular,
            full = photoApi.urls.full
        ),
        likes = photoApi.likes,
        likedByUser = photoApi.likedByUser,
        title = photoApi.user?.name,
        views = photoApi.views,
        downloads = photoApi.downloads,
        user = User(
            id = photoApi.user?.id,
            name = photoApi.user?.name,
            username = photoApi.user?.username,
            userPhoto = photoApi.user?.profileImage?.medium
        ),
        exif = toExif(exif = photoApi.exif),
        location = photoApi.location?.name,
        date = dateFormatter(photoApi.createdAt),
        description = photoApi.altDescription,
        isBookmark = isBookmark,
        link = photoApi.links?.self
    )
}

fun toExif(exif: Exif?): com.mldz.photo_api.models.Exif? {
    return exif?.let {
        com.mldz.photo_api.models.Exif(
            make = exif.make,
            model = exif.model,
            name = exif.name,
            exposureTime = exif.exposureTime,
            aperture = exif.aperture,
            focalLength = exif.focalLength,
            iso = exif.iso
        )
    }
}