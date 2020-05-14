package com.vverbytskyi.todoapp.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vverbytskyi.todoapp.network.TodoService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory { provideMoshi() }
    factory { provideJsonConverterFactory(get()) }
    factory { provideOkHttpClient() }

    single { provideRetrofit(get(), get(), "https://jsonplaceholder.typicode.com") }
    single { provideTodoService(get()) }
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideJsonConverterFactory(moshi: Moshi): Converter.Factory {
    return MoshiConverterFactory.create(moshi)
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    jsonConverterFactory: Converter.Factory,
    baseUrl: String
): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(jsonConverterFactory)
        .baseUrl(baseUrl)
        .build()
}

fun provideTodoService(retrofit: Retrofit): TodoService {
    return retrofit.create(TodoService::class.java)
}