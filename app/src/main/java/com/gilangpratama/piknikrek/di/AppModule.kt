package com.gilangpratama.piknikrek.di

import com.gilangpratama.piknikrek.data.local.LocalDataSource
import com.gilangpratama.piknikrek.data.local.datastore.AppPreference
import com.gilangpratama.piknikrek.data.remote.RemoteDataSource
import com.gilangpratama.piknikrek.data.remote.api.ApiService
import com.gilangpratama.piknikrek.data.repository.setting.SettingRepository
import com.gilangpratama.piknikrek.data.repository.setting.SettingRepositoryImpl
import com.gilangpratama.piknikrek.data.repository.wisata.WisataRepository
import com.gilangpratama.piknikrek.data.repository.wisata.WisataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWisataRepository(remoteDataSource: RemoteDataSource): WisataRepository =
        WisataRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideSettingRepository(localDataSource: LocalDataSource): SettingRepository =
        SettingRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(appPreference: AppPreference): LocalDataSource =
        LocalDataSource(appPreference)
}