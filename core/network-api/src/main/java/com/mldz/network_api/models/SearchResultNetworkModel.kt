package com.mldz.network_api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultNetworkModel(
    @SerialName("results")
    val results: List<ResultNetworkModel>?,
    @SerialName("total")
    val total: Int?,
    @SerialName("total_pages")
    val totalPages: Int?
) {
    @Serializable
    data class ResultNetworkModel(
        @SerialName("blur_hash")
        val blurHash: String?,
        @SerialName("color")
        val color: String?,
        @SerialName("created_at")
        val createdAt: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("height")
        val height: Int?,
        @SerialName("id")
        val id: String,
        @SerialName("liked_by_user")
        val likedByUser: Boolean?,
        @SerialName("likes")
        val likes: Int?,
        @SerialName("links")
        val links: LinksNetworkModel?,
        @SerialName("urls")
        val urls: UrlsNetworkModel,
        @SerialName("user")
        val user: UserNetworkModel?,
        @SerialName("width")
        val width: Int?
    ) {
        @Serializable
        data class LinksNetworkModel(
            @SerialName("download")
            val download: String?,
            @SerialName("html")
            val html: String?,
            @SerialName("self")
            val self: String?
        )

        @Serializable
        data class UrlsNetworkModel(
            @SerialName("full")
            val full: String?,
            @SerialName("raw")
            val raw: String?,
            @SerialName("regular")
            val regular: String,
            @SerialName("small")
            val small: String?,
            @SerialName("thumb")
            val thumb: String?
        )

        @Serializable
        data class UserNetworkModel(
            @SerialName("first_name")
            val firstName: String?,
            @SerialName("id")
            val id: String?,
            @SerialName("instagram_username")
            val instagramUsername: String?,
            @SerialName("last_name")
            val lastName: String?,
            @SerialName("links")
            val links: LinksNetworkModel?,
            @SerialName("name")
            val name: String?,
            @SerialName("portfolio_url")
            val portfolioUrl: String?,
            @SerialName("profile_image")
            val profileImage: ProfileImageNetworkModel?,
            @SerialName("twitter_username")
            val twitterUsername: String?,
            @SerialName("username")
            val username: String?
        ) {
            @Serializable
            data class LinksNetworkModel(
                @SerialName("html")
                val html: String?,
                @SerialName("likes")
                val likes: String?,
                @SerialName("photos")
                val photos: String?,
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
        }
    }
}