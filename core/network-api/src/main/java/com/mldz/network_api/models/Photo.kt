package com.mldz.network_api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PhotoNetworkModel(
//    @SerialName("alt_description")
//    val altDescription: String,
//    @SerialName("blur_hash")
//    val blurHash: String,
//    @SerialName("breadcrumbs")
//    val breadcrumbs: List<BreadcrumbNetworkModel>,
//    @SerialName("color")
//    val color: String,
//    @SerialName("created_at")
//    val createdAt: String,
//    @SerialName("current_user_collections")
//    val currentUserCollections: List<String>,
//    @SerialName("description")
//    val description: String?,
//    @SerialName("height")
//    val height: Int,
    @SerialName("id")
    val id: String,
//    @SerialName("liked_by_user")
//    val likedByUser: Boolean,
//    @SerialName("likes")
//    val likes: Int,
//    @SerialName("links")
//    val links: LinksNetworkModel,
//    @SerialName("promoted_at")
//    val promotedAt: String?,
//    @SerialName("slug")
//    val slug: String,
//    @SerialName("sponsorship")
//    val sponsorship: SponsorshipNetworkModel?,
//    @SerialName("topic_submissions")
//    val topicSubmissions: TopicSubmissionsNetworkModel?,
//    @SerialName("updated_at")
//    val updatedAt: String,
    @SerialName("urls")
    val urls: UrlsNetworkModel,
//    @SerialName("user")
//    val user: UserNetworkModel,
//    @SerialName("width")
//    val width: Int
)

@Serializable
data class BreadcrumbNetworkModel(
    @SerialName("index")
    val index: Int,
    @SerialName("slug")
    val slug: String,
    @SerialName("title")
    val title: String,
    @SerialName("type")
    val type: String
)

@Serializable
data class LinksNetworkModel(
    @SerialName("download")
    val download: String,
    @SerialName("download_location")
    val downloadLocation: String,
    @SerialName("html")
    val html: String,
    @SerialName("self")
    val self: String
)

@Serializable
data class SponsorshipNetworkModel(
    @SerialName("impression_urls")
    val impressionUrls: List<String>,
    @SerialName("sponsor")
    val sponsor: SponsorNetworkModel,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("tagline_url")
    val taglineUrl: String
)

@Serializable
data class SponsorNetworkModel(
    @SerialName("accepted_tos")
    val acceptedTos: Boolean,
    @SerialName("bio")
    val bio: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("for_hire")
    val forHire: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("links")
    val links: LinksNetworkModel,
    @SerialName("location")
    val location: String,
    @SerialName("name")
    val name: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String,
    @SerialName("profile_image")
    val profileImage: ProfileImageNetworkModel,
    @SerialName("social")
    val social: SocialNetworkModel,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("username")
    val username: String
)

@Serializable
data class TopicSubmissionsNetworkModel(
    @SerialName("3d-renders")
    val dRenders: DRendersNetworkModel?,
    @SerialName("film")
    val film: FilmNetworkModel?,
    @SerialName("technology")
    val technology: TechnologyNetworkModel?,
    @SerialName("wallpapers")
    val wallpapers: WallpapersNetworkModel?
)

@Serializable
data class DRendersNetworkModel(
    @SerialName("status")
    val status: String
)

@Serializable
data class FilmNetworkModel(
    @SerialName("approved_on")
    val approvedOn: String,
    @SerialName("status")
    val status: String
)

@Serializable
data class TechnologyNetworkModel(
    @SerialName("approved_on")
    val approvedOn: String,
    @SerialName("status")
    val status: String
)

@Serializable
data class WallpapersNetworkModel(
    @SerialName("status")
    val status: String
)

@Serializable
data class UrlsNetworkModel(
    @SerialName("full")
    val full: String,
    @SerialName("raw")
    val raw: String,
    @SerialName("regular")
    val regular: String,
    @SerialName("small")
    val small: String,
    @SerialName("small_s3")
    val smallS3: String,
    @SerialName("thumb")
    val thumb: String
)

@Serializable
data class UserNetworkModel(
    @SerialName("accepted_tos")
    val acceptedTos: Boolean,
    @SerialName("bio")
    val bio: String?,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("for_hire")
    val forHire: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("links")
    val links: LinksNetworkModel,
    @SerialName("location")
    val location: String,
    @SerialName("name")
    val name: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    @SerialName("profile_image")
    val profileImage: ProfileImageNetworkModel,
    @SerialName("social")
    val social: SocialNetworkModel,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("username")
    val username: String
)

@Serializable
data class ProfileImageNetworkModel(
    @SerialName("large")
    val large: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("small")
    val small: String
)

@Serializable
data class SocialNetworkModel(
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("paypal_email")
    val paypalEmail: String?,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    @SerialName("twitter_username")
    val twitterUsername: String?
)