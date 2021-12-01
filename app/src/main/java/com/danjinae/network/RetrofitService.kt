package com.danjinae.network

import com.danjinae.vo.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("/comment/add")
    fun postComment(
        @Body comment: Comment,
        @Query("postId") postId: Int
    ): Call<Boolean>

    @GET("/comment/list")
    fun getComment(
        @Query("PostId") postId: Int
    ): Call<CommentList>

    @Headers("Content-Type: application/json")
    @POST("/post/add")
    fun addPost(
        @Body post: Post
    ): Call<Boolean>

    @GET("/post/total-list")
    fun getPostList(): Call<PostList>

    @Headers("Content-Type: application/json","Connection: Keep-alive")
    @POST("/vehicle/guest")
    fun postVehicleGuest(
        @Body vehicle: Vehicle
        ): Call<List<Vehicle>>

    @GET("/vehicle/select/info")
    fun getVehicleInfo(
        @Query("vehicleId") vehicleId: Int
    ): Call<VehicleInfo>

    @GET("/mgfee/getmgfee")
    fun getMgFee(
        @Query ("aptId") aptId: Int,
        @Query ("userId") userId: Int
    ): Call<MgFee>

    @POST("complaint/add")
    fun addComplaint(
        @Body complaint: Complaint
    ): Call<Boolean>

    @GET("complaint/get/{aptid}")
    fun getManagerComplaintList(
        @Path ("aptid") aptid: Int
    ): Call<ComplaintList>

    @GET("/complaint/select/{cplid}")
    fun getManagerComplaint(
        @Query ("cplid") cplid: Int
    ): Call<ComplaintProcess>

    @GET("notice/get")
    fun getNoticeList(
        @Query ("userId") userId: Int
    ): Call<Notice>

    @PUT("user/signup")
    fun putSingUp(
        @Body signUp: LoginUserRequest
    ): Call<Boolean>

    @POST("user/login")
    fun postLogin(
        @Body login: LoginUserRequest
    ): Call<Boolean>
}