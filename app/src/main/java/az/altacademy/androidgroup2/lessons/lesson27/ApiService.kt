package az.altacademy.androidgroup2.lessons.lesson27

import az.altacademy.androidgroup2.lessons.lesson27_2.LikePostRequest
import az.altacademy.androidgroup2.lessons.lesson27_2.LikePostResponse
import az.altacademy.androidgroup2.lessons.lesson27_2.LoginRequest
import az.altacademy.androidgroup2.lessons.lesson27_2.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("facts")
    fun getFacts(): Call<List<FactsResponse>>

    @GET("posts/{id}/comments")
    fun getFactById(@Path("id") id: String?): Call<FactResponse>

    @GET("comments")
    fun getCommentsByPathId(
        @Query("postId") postId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Call<List<Any>>

    @GET("comments")
    fun getCommentsByPathId2(
        @QueryMap queryParams: Map<String, Any>
    ): Call<List<Any>>


    @PUT("auth/mail")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @PUT("post/like")
    fun likePost(
        @Body request: LikePostRequest
    ): Call<LikePostResponse>
}