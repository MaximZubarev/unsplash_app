package com.mldz.core.db_impl.di

import android.content.Context
import androidx.room.Room
import com.mldz.core.db_impl.Database
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan("com.mldz.core.db_impl.db")
class CoreDatabaseKoinModule {

    @Single
    fun database(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "unsplash_app_database"
        ).build()
    }
}