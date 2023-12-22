package com.mldz.core.db_api.entity


data class PhotoBookmarkEntity(
    val id: String,
    val url: String,
    val bookmark: Boolean = true
)