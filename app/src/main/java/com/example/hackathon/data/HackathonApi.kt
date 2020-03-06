package com.example.hackathon.data

import com.example.hackathon.data.auth.model.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HackathonApi {

    //region AUTH
    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun signUp(
        @Field("login") login: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<User>

    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<User>

    @GET("auth/logout")
    suspend fun logout(): Response<Unit>
    //endregion

    //region USER
    @GET("auth/user")
    suspend fun getUser(): Response<User>
    //endregion
}