package com.mldz.photoinspiration.data.sources.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SponsorApiModel(
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
//    @SerialName("last_name")
//    val lastName: Any,
    @SerialName("links")
    val links: LinksApiModelX,
    @SerialName("location")
    val location: String,
    @SerialName("name")
    val name: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String,
    @SerialName("profile_image")
    val profileImage: ProfileImageApiModel,
    @SerialName("social")
    val social: SocialApiModel,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("username")
    val username: String
)