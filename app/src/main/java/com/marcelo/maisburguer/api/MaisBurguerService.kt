package com.marcelo.maisburguer.api

import com.marcelo.maisburguer.BuildConfig
import com.marcelo.maisburguer.data.UserRequest
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MaisBurguerService {

    @POST("users")
    suspend fun postUser(
        @Body
        userRequest: UserRequest,
        @Header("x-secret-key")
        secretKey: String = BuildConfig.X_SECRET_KEY
        ): Response<ResponseBody>

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        fun create(): MaisBurguerService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val clientOk = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientOk)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MaisBurguerService::class.java)
        }
    }
}