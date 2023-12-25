package com.mldz.profile_impl.data

import com.mldz.network_api.NetworkApi
import com.mldz.network_api.models.UserProfileNetworkModel
import com.mldz.profile_api.models.Profile
import com.mldz.profile_impl.domain.ProfileRepository
import org.koin.core.annotation.Single


@Single
class ProfileRepositoryImpl(
    private val networkApi: NetworkApi
): ProfileRepository {

    override suspend fun getUserProfile(username: String): Profile {
        return networkApi.getUserProfile(username = username).toProfile()
    }
}

private fun UserProfileNetworkModel.toProfile() = Profile(
    id = this.id,
    username = this.username,
    name = this.name,
    bio = this.bio,
    location = this.location,
    likes = this.totalLikes,
    totalPhotos = this.totalPhotos,
    followers = this.followersCount,
    imageUrl = this.profileImage?.large,
    photosFlow = null
)