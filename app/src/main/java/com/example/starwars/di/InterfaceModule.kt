package com.example.starwars.di

import com.example.starwars.database.ILocalDataSource
import com.example.starwars.database.LocalDataSource
import com.example.starwars.network.IRemoteDataSource
import com.example.starwars.network.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class InterfaceModule {
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource):
            IRemoteDataSource

    @Binds
    abstract fun provideLocalDataSource(localDataSource: LocalDataSource):
            ILocalDataSource
}