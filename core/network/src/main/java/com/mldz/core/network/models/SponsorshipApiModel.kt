package com.mldz.photoinspiration.data.sources.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SponsorshipApiModel(
    @SerialName("impression_urls")
    val impressionUrls: List<String>,
    @SerialName("sponsor")
    val sponsor: SponsorApiModel,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("tagline_url")
    val taglineUrl: String
)