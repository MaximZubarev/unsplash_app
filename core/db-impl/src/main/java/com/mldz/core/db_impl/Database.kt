package com.mldz.core.db_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mldz.core.db_impl.dao.BookmarkDao
import com.mldz.core.db_impl.entity.Bookmark


@Database(
    entities = [
        Bookmark::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}