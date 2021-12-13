package com.danjinae.network

import com.danjinae.vo.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("/comment/add")
    fun postComment(
        @Body comment: Comment,
        @Query("postId") postId: Int
    ): Call<Boolean>

    @GET("/comment/list")
    fun getComment(
        @Query("postId") postId: Int
    ): Call<CommentList>

    @POST("/post/add")
    fun addPost(
        @Body post: Post
    ): Call<Boolean>

    @GET("/post/select")
    fun selectPost(
        @Query("postId") postId: Int
    ): Call<PostResponse>

    @GET("/post/total-list")
    fun getPostList(): Call<PostList>

    @POST("/vehicle/guest")
    fun postVehicleGuest(
        @Body vehicleRequest: VehicleRequest
        ): Call<List<VehicleRequest>>

    @GET("/vehicle/select/info")
    fun getVehicleInfo(
        @Query("vehicleId") vehicleId: Int
    ): Call<VehicleInfo>

    @GET("vehicle/select/list")
    fun getVehicleSelectList(
        @Query ("number") number: String
    ): Call<VehicleList>

    @GET("/mgfee/getmgfee")
    fun getMgFee(
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
        @Path ("cplid") cplid: Int
    ): Call<ComplaintProcess>

    @GET("notice/get")
    fun getNoticeList(
        @Query ("userId") userId: Int
    ): Call<Notice>

    @PUT("/user/signup")
    fun putSingUp(
        @Body signUp: LoginUserRequest
    ): Call<Boolean>

    @POST("/user/login")
    fun postLogin(
        @Body login: LoginUserRequest
    ): Call<Boolean>

    @GET("/user/aptchoice")
    fun getapt(
        @Query ("address") address: String
    ):Call<AptList>

    @POST("/user/newfcmtoken")
    fun newToken(
        @Body token: FcmToken
    ):Call<Boolean>
}