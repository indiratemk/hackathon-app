package space.platform.hackathon.data

import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Paging
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.data.notifications.model.Notification
import retrofit2.Response
import retrofit2.http.*
import space.platform.hackathon.data.tasks.Task

interface HackathonApi {

    //region AUTH
    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun signUp(
        @Field("login") login: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Result<User, Unit>>

    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Result<User, Unit>>

    @GET("auth/logout")
    suspend fun logout(): Response<Result<Boolean, Unit>>
    //endregion

    //region USER
    @GET("auth/user")
    suspend fun getCurrentUser(): Response<Result<User, Unit>>

    @GET("users/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<Result<User, Unit>>

    @GET("users/{id}/hackathons")
    suspend fun getOwnHackathons(@Path("id") userId: Int): Response<Result<List<Hackathon>, Unit>>
    //endregion

    //region HACKATHON
    @GET("hackathons")
    suspend fun getHackathons(@Query("page") page: Int): Response<Result<List<Hackathon>, Paging>>

    @GET("hackathons/search")
    suspend fun searchHackathons(@Query("q") query: String): Response<Result<List<Hackathon>, Paging>>

    @GET("hackathons/{id}")
    suspend fun getHackathon(@Path("id") id: Int): Response<Result<Hackathon, Unit>>

    @GET("hackathons/{id}/check-participation")
    suspend fun checkParticipation(@Path("id") id: Int): Response<Result<Boolean, Unit>>

    @GET("hackathons/{id}/participants")
    suspend fun getParticipants(@Path("id") id: Int): Response<Result<List<Participant>, Unit>>

    @GET("hackathons/{id}/teams")
    suspend fun getTeams(@Path("id") id: Int): Response<Result<List<Team>, Unit>>
    //endregion

    //region PARTICIPANTS
    @FormUrlEncoded
    @POST("participants/{id}/register")
    suspend fun register(@Path("id") id: Int,
                         @Field("type") type: Int): Response<Result<Boolean, Unit>>

    @POST("participants/{id}/unregister")
    suspend fun unregister(@Path("id") id: Int): Response<Result<Boolean, Unit>>

    @POST("participants/{hid}/{uid}/confirm")
    suspend fun confirmParticipation(@Path("hid") hackathonId: Int,
                                     @Path("uid") userId: Int): Response<Result<Boolean, Unit>>

    @GET("participants/{id}/hackathons")
    suspend fun getParticipatedHackathons(@Path("id") userId: Int): Response<Result<List<Hackathon>, Unit>>

    @GET("participants/{id}/current")
    suspend fun getCurrent(@Path("id") hackathonId: Int): Response<Result<Participant, Unit>>

    @POST("participants/{hid}/{uid}/leave-team")
    suspend fun leaveTeam(@Path("hid") hackathonId: Int,
                          @Path("uid") userId: Int): Response<Result<Boolean, Unit>>
    //endregion

    //region NOTIFICATIONS
    @GET("notifications/count")
    suspend fun getNotificationsCount(): Response<Result<HashMap<String, Int>, Unit>>

    @GET("notifications/current")
    suspend fun getNotifications(): Response<Result<List<Notification>, Unit>>

    @DELETE("notifications/{id}")
    suspend fun removeNotification(@Path("id") id: Int): Response<Result<Boolean, Unit>>

    @FormUrlEncoded
    @POST("feedback/{id}")
    suspend fun sendFeedback(@Path("id") hackathonId: Int,
                             @Field("message") message: String,
                             @Field("score") score: Int): Response<Result<Boolean, Unit>>
    //endregion

    //region TEAMS
    @FormUrlEncoded
    @POST("teams/invite")
    suspend fun inviteParticipant(@Field("receiver_id") receiverId: Int,
                                  @Field("team_id") teamId: Int): Response<Result<Notification, Unit>>

    @FormUrlEncoded
    @POST("teams")
    suspend fun createTeam(@Field("hid") hackathonId: Int,
                           @Field("name") title: String): Response<Result<Team, Unit>>

    @DELETE("teams/{id}")
    suspend fun removeTeam(@Path("id") teamId: Int): Response<Result<Boolean, Unit>>

    @DELETE("teams/{team_id}/kick/{user_id}")
    suspend fun kickUser(@Path("team_id") teamId: Int,
                         @Path("user_id") userId: Int): Response<Result<Boolean, Unit>>

    @FormUrlEncoded
    @POST("teams/accept-invite")
    suspend fun acceptInvite(@Field("code") code: String,
                             @Field("details_id") detailsId: Int,
                             @Field("team_id") teamId: Int): Response<Result<Boolean, Unit>>
    //endregion

    //region TASKS
    @GET("tasks/{id}")
    suspend fun getTasks(@Path("id") id: Int): Response<Result<List<Task>, Unit>>
    //endregion
}