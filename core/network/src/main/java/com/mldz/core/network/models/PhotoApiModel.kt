package com.mldz.core.network.models



import com.mldz.photoinspiration.data.sources.remote.models.UrlsApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoApiModel(
//    @SerialName("alt_description")
//    val altDescription: String,
//    @SerialName("blur_hash")
//    val blurHash: String,
//    @SerialName("breadcrumbs")
//    val breadcrumbs: List<Any>,
//    @SerialName("color")
//    val color: String,
//    @SerialName("created_at")
//    val createdAt: String,
//    @SerialName("current_user_collections")
//    val currentUserCollections: List<Any>,
//    @SerialName("description")
//    val description: String,
//    @SerialName("height")
//    val height: Int,
    @SerialName("id")
    val id: String,
//    @SerialName("liked_by_user")
//    val likedByUser: Boolean,
//    @SerialName("likes")
//    val likes: Int,
//    @SerialName("links")
//    val links: LinksApiModel,
//    @SerialName("promoted_at")
//    val promotedAt: String,
//    @SerialName("slug")
//    val slug: String,
//    @SerialName("sponsorship")
//    val sponsorship: SponsorshipApiModel,
//    @SerialName("topic_submissions")
//    val topicSubmissions: TopicSubmissionsApiModel,
//    @SerialName("updated_at")
//    val updatedAt: String,
    @SerialName("urls")
    val urls: UrlsApiModel,
//    @SerialName("user")
//    val user: UserApiModel,
//    @SerialName("width")
//    val width: Int
) {

}