package az.altacademy.androidgroup2.lessons.lesson27

import az.altacademy.androidgroup2.lessons.lesson28_2.CreatePostModel
import az.altacademy.androidgroup2.lessons.lesson28_2.CreatePostResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApiService {

    @POST("posts")
    fun createPost(
        @Body requestBody: CreatePostModel
    ):Response<CreatePostResponse>

    @HTTP(
        method = "DELETE",
        path = "posts",
        hasBody = true
    )
    suspend fun deletePost(
        @Body request: CreatePostModel
    ): Call<CreatePostResponse>


    @DELETE("posts/{id}")
    suspend fun deletePost2(
        @Path("id") postId: Int
    ): Call<CreatePostResponse>
}