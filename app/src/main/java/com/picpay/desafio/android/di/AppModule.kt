package com.picpay.desafio.android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://careers.picpay.com/tests/mobdev/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providePicPayService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(service: PicPayService) = UserRemoteDataSource(service)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UserRemoteDataSource,
                          localDataSource: UserDao
    ) =
        UserRepository(remoteDataSource, localDataSource)




}