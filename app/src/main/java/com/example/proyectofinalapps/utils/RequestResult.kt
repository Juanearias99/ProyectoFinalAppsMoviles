package com.example.proyectofinalapps.utils

import com.mapbox.base.common.logger.model.Message

sealed class RequestResult {
    data class Success(val message: String): RequestResult()
    data class Failure(val errorMessage: String): RequestResult()
    object Loading: RequestResult()
}