package com.danjinae.network

import com.danjinae.vo.CommentModel
import com.danjinae.vo.GuestVehicleModel
import com.danjinae.vo.PostModel
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("/comment/add")
    fun postComment(
        @Body comment: CommentModel,
        @Query("postId") postId: Int
    ): Call<CommentModel>

    @GET("/comment/list")
    fun getComment(): retrofit2.Call<CommentModel>

    @Headers("Content-Type: application/json")
    @POST("/post/add")
    fun addPost(
        @Body post: PostModel
    ): Call<PostModel>

    @POST("/vehicle/guest")
    @Headers("Content-Type: application/json")
    fun postVehicleGuest(
        @Body vehicleModel: GuestVehicleModel
        ): Call<GuestVehicleModel>

    @GET("/vehicle/select/info")
    fun getVehicleInfo(
        @Query("number") number: String
    ): Call<GuestVehicleModel>
}