package com.mldz.core.db_impl.di

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.mldz.core.db_impl.Database
import com.mldz.core.db_impl.dao.BookmarkDao
import com.mldz.core.db_impl.entity.Bookmark
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single


@Module
@ComponentScan("com.mldz.core.db_impl")
class CoreDatabaseKoinModule {

    @Single
    fun database(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "unsplash_app_database"
        ).build()
    }

    @Single
    @Named("DbPager")
    fun pager(bookmarkDao: BookmarkDao): Pager<Int, Bookmark> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = { bookmarkDao.getAll() }
        )
    }
}