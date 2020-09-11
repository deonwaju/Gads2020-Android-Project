package gads2020.damolaolarewaju.testprojectgads.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import gads2020.damolaolarewaju.testprojectgads.networking.RemoteDataSource
import gads2020.damolaolarewaju.testprojectgads.networking.RemoteDataSourceImpl
import gads2020.damolaolarewaju.testprojectgads.networking.repositories.HomeRepository
import gads2020.damolaolarewaju.testprojectgads.networking.repositories.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRepository(mainRepositoryImpl: HomeRepositoryImpl): HomeRepository
    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}