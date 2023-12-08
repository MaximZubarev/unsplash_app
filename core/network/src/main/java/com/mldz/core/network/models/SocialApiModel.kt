package com.mldz.photoinspiration.data.sources.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialApiModel(
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String,
    @SerialName("twitter_username")
    val twitterUsername: String
)