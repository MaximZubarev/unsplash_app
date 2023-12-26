package com.mldz.profile_api.usecase

import com.mldz.profile_api.models.Profile


interface GetUserProfileUseCase {

    suspend operator fun invoke(username: String): Profile
}