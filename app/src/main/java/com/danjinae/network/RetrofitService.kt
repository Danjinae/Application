package com.danjinae.network

import com.danjinae.vo.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("/comment/add")
    fun postComment(
        @Body comment: CommentModel,
        @Query("postId") postId: Int
    ): Call<Boolean>

    @GET("/comment/list")
    fun getComment(): retrofit2.Call<CommentModel>

    @Headers("Content-Type: application/json")
    @POST("/post/add")
    fun addPost(
        @Body post: PostModel
    ): Call<Boolean>

    @GET("/post/total-list")
    fun getPostList(): Call<List<PostList>>

    @Headers("Content-Type: application/json","Connection: Keep-alive")
    @POST("/vehicle/guest")
    fun postVehicleGuest(
        @Body vehicle: GuestVehicleModel
        ): Call<List<GuestVehicleModel>>

    @GET("/vehicle/select/info")
    fun getVehicleInfo(
        @Query("vehicleId") vehicleId: Int
    ): Call<VehicleInfo>
}