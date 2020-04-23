package com.example.hackathon.util.exception

import java.lang.Exception

class ApiException(val errorCode: Int,
                   val errorMessage: String) : Exception() {
}