package com.mldz.photoinspiration.data.sources.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicSubmissionsApiModel(
    @SerialName("travel")
    val travel: TravelApiModel
)