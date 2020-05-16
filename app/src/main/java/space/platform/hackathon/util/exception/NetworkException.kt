package space.platform.hackathon.util.exception

import java.lang.Exception

class NetworkException : Exception() {

    fun errorMessage() = "Пожалуйста, проверьте интернет соединение"
}