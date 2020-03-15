package com.example.hackathon.data

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.hackathon.model.Hackathon
import retrofit2.Response
import retrofit2.http.*

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

    //region HACKATHON
    @GET("hackathons")
    suspend fun getHackathons(): Response<Paging<Hackathon>>

    @GET("hackathons/search")
    suspend fun searchHackathons(@Query("q") query: String): Response<Paging<Hackathon>>
    //endregion
}