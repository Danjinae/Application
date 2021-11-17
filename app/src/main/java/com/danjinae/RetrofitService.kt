package com.danjinae

import com.danjinae.vo.CommentModel
import com.danjinae.vo.GuestVehicleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @POST("/comment/add")
    fun postComment(
        @Query("comment") comment: String,
        @Query("userId") userId: Int
    ): Call<CommentModel>

    @GET("/coment/list")
    fun getComment(): retrofit2.Call<CommentModel>


    @POST("/vehicle/guest")
    fun postVehicleGuest(
        @Query("number") number: String,
        @Query("phone") phone: String,
        @Query("userId") userId: Int
    ): Call<GuestVehicleModel>
}