package com.example.proyectofinalapps.utils


sealed class RequestResult {
    data class Success(val message: String): RequestResult()
    data class Failure(val errorMessage: String): RequestResult()
    object Loading: RequestResult()
}