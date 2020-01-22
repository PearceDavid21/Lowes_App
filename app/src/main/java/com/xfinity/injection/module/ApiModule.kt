package com.xfinity.injection.module

import com.xfinity.data.remote.NewsService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @Singleton
    internal fun provideAngelApi(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}