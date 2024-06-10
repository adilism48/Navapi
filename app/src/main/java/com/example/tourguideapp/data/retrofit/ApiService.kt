package com.example.tourguideapp.data.retrofit

import com.example.tourguideapp.data.model.Photo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}