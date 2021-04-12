package com.example.marvelapp.di

import androidx.paging.PagedList
import com.example.marvelapp.BuildConfig
import com.example.marvelapp.data.retrofit.ApiInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideGsonConverterFactory() = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideCoroutineCallAdapterFactory() = CoroutineCallAdapterFactory()


    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        gsonConverterFactory: GsonConverterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
        ,
        okHttpClient: OkHttpClient
    ) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideInterceptor() = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .url(
                chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apikey", BuildConfig.apikey)
                    .addQueryParameter("hash", BuildConfig.hash)
                    .build()
            )
            .build()
        return@Interceptor chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun initPagingConfig(
        pageSize: Int = 10,
        enablePlaceHolder: Boolean = true,
        prefetchDistance: Int = 2
    ) =
        PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(enablePlaceHolder)
            .setPrefetchDistance(prefetchDistance)
            .build()
}