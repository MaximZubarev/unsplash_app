package com.mldz.profile_impl.domain

import com.mldz.profile_api.models.Profile


interface ProfileRepository {

    suspend fun getUserProfile(username: String): Profile
}