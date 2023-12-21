package com.mldz.core.db_impl

import com.mldz.core.db_api.DatabaseSource
import com.mldz.core.db_api.entity.PhotoBookmarkEntity
import com.mldz.core.db_impl.dao.BookmarkDao
import com.mldz.core.db_impl.entity.Bookmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single


@Single
class DatabaseSourceImpl(
    private val bookmarkDao: BookmarkDao
): DatabaseSource {
    override suspend fun bookmarkPhoto(id: String): Long {
        return bookmarkDao.insert(Bookmark(id = id))
    }

    override suspend fun unBookmarkPhoto(id: String): Int {
        return bookmarkDao.delete(id = id)
    }

    override suspend fun getPhotoById(id: String): PhotoBookmarkEntity? {
        return bookmarkDao.getById(id = id)?.let {
            PhotoBookmarkEntity(it.id, it.bookmark)
        }
    }

    override fun getBookmarkedPhotos(): Flow<PhotoBookmarkEntity> {
        return bookmarkDao.getAll().map { PhotoBookmarkEntity(it.id, it.bookmark) }
    }
}