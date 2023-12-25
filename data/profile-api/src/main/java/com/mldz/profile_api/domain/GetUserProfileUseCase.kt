package com.mldz.profile_api.domain

import com.mldz.profile_api.models.Profile


interface GetUserProfileUseCase {

    suspend fun invoke(username: String): Profile
}