package com.emrexample.mobile.dagger

import android.app.Application
import com.emrexample.mobile.api.StartsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import fr.speekha.httpmocker.MockResponseInterceptor
import fr.speekha.httpmocker.gson.GsonMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This module exposes the API endpoint components
 */

const val BASE_URL = "https://api.github.com"

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Singleton
    @Provides
    fun provideStarsService(okHttpClient: OkHttpClient,
                                 converterFactory: GsonConverterFactory
    ) = provideService(okHttpClient, GsonConverterFactory.create() ,StartsApi::class.java)


    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideSimpleMockInterceptor(application: Application) =
        MockResponseInterceptor.Builder()
            .parseScenariosWith(GsonMapper())
            .loadFileWith { application.assets.open(it) }
            .setInterceptorStatus(MockResponseInterceptor.Mode.ENABLED)
            .addFakeNetworkDelay(500L)
            .build()

    @Provides
    fun provideOkHttpClient(interceptor: MockResponseInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()


    @com.emrexample.mobile.dagger.StarApi
    @Provides
    fun providePrivateOkHttpClient(interceptor: MockResponseInterceptor,
                                   okHttpClient: OkHttpClient
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .build()
    }




    private fun createRetrofit(okHttpClient: OkHttpClient,
                               converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun <T> provideService(okHttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory,
                                   clazz: Class<T>
    ): T {
        return createRetrofit(okHttpClient,converterFactory).create(clazz)
    }




}