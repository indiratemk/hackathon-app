package space.platform.hackathon.data.base

import space.platform.hackathon.util.response.ApiResponse
import space.platform.hackathon.util.response.error.ErrorUtils
import space.platform.hackathon.util.exception.ApiException
import space.platform.hackathon.util.exception.NetworkException
import retrofit2.Response

open class BaseRemoteDataSource {

    suspend fun <T: Any> getResponse(call: suspend() -> Response<T>): ApiResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                return ApiResponse.Success(response.body()!!)
            }
            val error = ErrorUtils.parseError(response)
            return ApiResponse.Error(ApiException(error.statusCode, error.message))
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Error(NetworkException())
        }
    }
}