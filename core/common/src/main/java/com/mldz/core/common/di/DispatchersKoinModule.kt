package com.mldz.core.common.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
class DispatchersKoinModule {

    @Single
    fun ioDispatcher() = Dispatchers.IO
}