package com.catganisation.catalog.injection.modules

import android.app.Application
import com.catganisation.catalog.BuildConfig
import com.catganisation.catalog.data.remote.interceptors.CatsApiKeyInterceptor
import com.catganisation.catalog.data.remote.interceptors.SessionTokenInterceptor
import com.catganisation.catalog.data.remote.services.CatsApi
import com.catganisation.catalog.data.remote.services.UserApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

private const val CACHE_MAX_SIZE = (10 * 1024 * 1024).toLong()

@Module
class NetworkModule {

    @Provides
    fun provideUserApi(
        okHttpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder,
        sessionTokenInterceptor: SessionTokenInterceptor
    ): UserApi {
        return retrofitBuilder
            .client(
                okHttpClientBuilder.addNetworkInterceptor(sessionTokenInterceptor)
                    .build()
            )
            .baseUrl(BuildConfig.USER_API_ENDPOINT)
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    fun provideCatsApi(
        okHttpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): CatsApi {
        return retrofitBuilder
            .client(
                okHttpClientBuilder.addNetworkInterceptor(CatsApiKeyInterceptor())
                    .build()
            )
            .baseUrl(BuildConfig.CATS_API_ENDPOINT)
            .build()
            .create(CatsApi::class.java)
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    @Provides
    internal fun provideHttpClientBuilder(application: Application): OkHttpClient.Builder {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(interceptor)
        }

        val cacheDir = File(application.cacheDir.absolutePath, application.packageName)
        okHttpBuilder.cache(Cache(cacheDir, CACHE_MAX_SIZE))
        return okHttpBuilder
    }
}