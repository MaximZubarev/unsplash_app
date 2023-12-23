package com.mldz.photo_impl.paging

import com.mldz.network_api.NetworkApi
import com.mldz.photo_api.models.Photo


class SearchPaging(
    private val networkApi: NetworkApi,
    private val query: String
) : PhotoPaging<Photo>() {

    override suspend fun getData(page: Int): List<Photo> {
        val searched = networkApi.search(query = query, page = page)
        return searched.results?.let { result ->
            result.map { Photo(it.id, it.urls.regular) }
        } ?: emptyList()
    }
}