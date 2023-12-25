package com.mldz.profile_impl.domain

import com.mldz.photo_api.domain.PhotoRepository
import com.mldz.profile_api.domain.GetUserProfileUseCase
import com.mldz.profile_api.models.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetUserProfileUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val photoRepository: PhotoRepository,
    private val ioDispatcher: CoroutineDispatcher
) : GetUserProfileUseCase {

    override suspend fun invoke(username: String): Profile {
        return withContext(ioDispatcher) {
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