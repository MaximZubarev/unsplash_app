package com.mldz.network_api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PhotoDetailNetworkModel(
    val id: String,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("alt_description") val altDescription: String?,
    val downloads: Int,
    val likes: Int,
    @SerialName("liked_by_user") val likedByUser: Boolean,
    val description: String?,
    val exif: Exif?,
    val location: Location?,
    val tags: List<Tag?>,
    val urls: Urls,
    val links: Links?,
    val user: User?,
    val views: Int?,
)

@Serializable
data class Exif(
    val make: String?,
    val model: String?,
    val name: String?,
    @SerialName("exposure_time") val exposureTime: String?,
    val aperture: String?,
    @SerialName("focal_length") val focalLength: String?,
    val iso: Int?
)

@Serializable
data class Location(
    val city: String?,
    val country: String?,
    val position: Position?,
    val name: String?
)

@Serializable
data class Position(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Tag(
    val title: String?
)

@Serializable
data class Collection(
    val id: Int,
    val title: String?,
    @SerialName("published_at") val publishedAt: String?,
    @SerialName("last_collected_at") val lastCollectedAt: String?,
    @SerialName("updated_at") val collectionUpdatedAt: String?,
    @SerialName("cover_photo") val coverPhoto: String?, // Change this to the respective data class if available
    val user: String? // Change this to the respective data class if available
)

@Serializable
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

@Serializable
data class Links(
    val self: String?,
    val html: String?,
    val download: String?,
    @SerialName("download_location") val downloadLocation: String?
)

@Serializable
data class User(
    val id: String?,
    @SerialName("updated_at") val updatedAt: String?,
    val username: String?,
    val name: String?,
    @SerialName("portfolio_url") val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    @SerialName("total_likes") val totalLikes: Int,
    @SerialName("total_photos") val totalPhotos: Int,
    @SerialName("total_collections") val totalCollections: Int,
    val links: UserLinks?
)

@Serializable
data class UserLinks(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?,
    val portfolio: String?
)
