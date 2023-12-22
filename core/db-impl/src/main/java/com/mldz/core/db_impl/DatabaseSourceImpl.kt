package com.mldz.core.db_impl

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.mldz.core.db_api.DatabaseSource
import com.mldz.core.db_api.entity.PhotoBookmarkEntity
import com.mldz.core.db_impl.dao.BookmarkDao
import com.mldz.core.db_impl.entity.Bookmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single


@Single
class DatabaseSourceImpl(
    private val bookmarkDao: BookmarkDao,
    @Named("DbPager") private val pager: Pager<Int, Bookmark>
) : DatabaseSource {
    override suspend fun bookmarkPhoto(id: String, url: String): Long {
        return bookmarkDao.insert(Bookmark(id = id, url = url))
    }

    override suspend fun unBookmarkPhoto(id: String): Int {
        return bookmarkDao.delete(id = id)
    }

    override suspend fun getPhotoById(id: String): PhotoBookmarkEntity? {
        return bookmarkDao.getById(id = id)?.let {
            PhotoBookmarkEntity(it.id, it.url, it.bookmark)
        }
    }

    override fun getBookmarkedPhotos(): Flow<PagingData<PhotoBookmarkEntity>> {
        return pager.flow
            .map {
                it.map { bookmark -> PhotoBookmarkEntity(bookmark.id, bookmark.url) }
            }

    }

}