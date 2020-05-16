package space.platform.hackathon.util.response.error

import space.platform.hackathon.data.base.model.Error
import com.google.gson.Gson
import retrofit2.Response

class ErrorUtils {

    companion object {
        fun parseError(response: Response<*>): Error =
            Gson().fromJson(response.errorBody()?.string(), Error::class.java)
    }
}