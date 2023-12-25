package com.mldz.network_api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileNetworkModel(
    @SerialName("badge")
    val badge: BadgeNetworkModel?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("downloads")
    val downloads: Int?,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("followed_by_user")
    val followedByUser: Boolean?,
    @SerialName("followers_count")
    val followersCount: Int,
    @SerialName("following_count")
    val followingCount: Int?,
    @SerialName("id")
    val id: String,
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("links")
    val links: LinksNetworkModel?,
    @SerialName("location")
    val location: String?,
    @SerialName("name")
    val name: String,
    @SerialName("profile_image")
    val profileImage: ProfileImageNetworkModel?,
    @SerialName("social")
    val social: SocialNetworkModel?,
    @SerialName("total_collections")
    val totalCollections: Int?,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("username")
    val username: String
) {
    @Serializable
    data class BadgeNetworkModel(
        @SerialName("link")
        val link: String?,
        @SerialName("primary")
        val primary: Boolean?,
        @SerialName("slug")
        val slug: String?,
        @SerialName("title")
        val title: String?
    )

    @Serializable
    data class LinksNetworkModel(
        @SerialName("html")
        val html: String?,
        @SerialName("likes")
        val likes: String?,
        @SerialName("photos")
        val photos: String?,
        @SerialName("portfolio")
        val portfolio: String?,
        @SerialName("self")
        val self: String?
    )

    @Serializable
    data class ProfileImageNetworkModel(
        @SerialName("large")
        val large: String?,
        @SerialName("medium")
        val medium: String?,
        @SerialName("small")
        val small: String?
    )

    @Serializable
    data class SocialNetworkModel(
        @SerialName("instagram_username")
        val instagramUsername: String?,
        @SerialName("portfolio_url")
        val portfolioUrl: String?,
        @SerialName("twitter_username")
        val twitterUsername: String?
    )
}