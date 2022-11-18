package ru.marselich.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marselich.moviemaniaapp.BuildConfig
import java.util.concurrent.TimeUnit


object MovieApiFactory {


    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): MovieApiService {
        return getRetrofit().create(MovieApiService::class.java)
    }

}