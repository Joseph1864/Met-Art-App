package com.example.metartapp.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataRemoteModule = module {

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://collectionapi.metmuseum.org/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get<OkHttpClient>())
            .build()
    }

    single<MetApi> {
        get<Retrofit>().create(MetApi::class.java)
    }

}