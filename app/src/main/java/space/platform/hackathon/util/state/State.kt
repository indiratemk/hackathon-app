package space.platform.hackathon.util.state

sealed class State<T>(
    val result: T? = null,
    val message: String? = null,
    val errorCode: Int? = 200,
    val isLoading: Boolean = false
) {
    class Success<T>(data: T) : State<T>(data)
    class Loading<T>(isLoading: Boolean) : State<T>(isLoading = isLoading)
    class NetworkError<T>(message: String) : State<T>(message = message)
    class BackendError<T>(errorCode: Int, message: String) : State<T>(errorCode = errorCode, message = message)
}