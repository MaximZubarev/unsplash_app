package com.mldz.profile_impl.usecase

import com.mldz.photo_api.repository.PhotoRepository
import com.mldz.profile_api.models.Profile
import com.mldz.profile_api.usecase.GetUserProfileUseCase
import com.mldz.profile_impl.repository.ProfileRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Factory


@Factory
class GetUserProfileUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val photoRepository: PhotoRepository
) : GetUserProfileUseCase {

    override suspend fun invoke(username: String): Profile {
        return coroutineScope {
            val profile = async {
                profileRepository.getUserProfile(username = username)
            }
            val photos = async {
                photoRepository.getUserPhotos(username = username)
            }
            profile.await().copy(photosFlow = photos.await())
        }
    }
}