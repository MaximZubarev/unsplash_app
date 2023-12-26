package com.mldz.core.db_impl.di

import com.mldz.core.db_impl.Database
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
//@ComponentScan("com.mldz.core.db_impl")
class CoreDaosKoinModule {

    @Single
    fun bookmarkDao(database: Database) = database.bookmarkDao()
}