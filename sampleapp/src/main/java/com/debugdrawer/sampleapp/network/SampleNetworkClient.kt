package com.debugdrawer.sampleapp.network

import com.debugdrawer.utils.NetworkInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Sample network client for demonstrating network logging.
 */
@Singleton
class SampleNetworkClient @Inject constructor(
    private val networkInterceptor: NetworkInterceptor
) {
    private val apiService: ApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        apiService.getUsers()
    }

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        apiService.getPosts()
    }
}

/**
 * API service interface for sample requests.
 */
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("posts")
    suspend fun getPosts(): List<Post>
}

/**
 * Sample data models.
 */
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
