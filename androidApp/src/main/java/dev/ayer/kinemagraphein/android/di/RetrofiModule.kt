package dev.ayer.kinemagraphein.android.di

import dev.ayer.kinemagraphein.android.BuildConfig
import dev.ayer.kinemagraphein.android.data.sources.RetrofitApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val retrofitInstanceName = "retrofit-instance-name"
private const val apiBaseUrl = "https://api.tvmaze.com"

val retrofitModule = module {
    factory {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(get())
    }

    single<OkHttpClient> {
        val loggingInterceptor: HttpLoggingInterceptor = get()
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single<Retrofit>(named(retrofitInstanceName)) {
        Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
    }

    single<RetrofitApiService> {
        val retrofit: Retrofit = get(qualifier = named(retrofitInstanceName))
        retrofit.create(RetrofitApiService::class.java)
    }
}
