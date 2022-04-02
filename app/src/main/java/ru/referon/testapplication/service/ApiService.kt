package ru.referon.testtask

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.referon.testapplication.model.MainModel

private val BASE_URL = "https://olimpia.fitnesskit-admin.ru/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("schedule/get_v3/?club_id=2")
    suspend fun getLessons(): MainModel

}
object PostsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}