package com.frensky.porto.data.network.exception

import com.chuckerteam.chucker.BuildConfig
import com.frensky.porto.data.network.ConnectionStatus
import com.frensky.porto.model.exception.NetworkErrorCode
import com.google.gson.JsonObject

object APIErrorHelper {
    fun generateErrorModel(
        errorJson: JsonObject?,
        errorCode: Int,
    ): ExceptionModel {
        val origin = getErrorMessage(errorJson)
        var errorMessage: String
        var codes: Int
        when {
            (errorCode == ConnectionStatus.TIMEOUT_CODE) -> {
                codes = NetworkErrorCode.ERROR_CONNECTION
                errorMessage = "Koneksi server terputus, mohon coba lagi"
            }
            (errorCode == ConnectionStatus.CONNECTION_ERROR) -> {
                codes = NetworkErrorCode.ERROR_CONNECTION
                errorMessage = "Ada gangguan koneksi internet"
            }
            (errorCode == ConnectionStatus.NETWORK_ERROR) -> {
                codes = NetworkErrorCode.ERROR_OTHER
                errorMessage = "Koneksi Error, mohon coba lagi"
            }
            (errorCode == 401) -> {
                codes = NetworkErrorCode.ERROR_AUTH
                errorMessage = "Token Tidak Valid, mohon logout"
            }
            (errorCode == 413 || errorCode == 502) -> {
                codes = NetworkErrorCode.ERROR_MVP
                errorMessage = "Mohon maaf, terjadi kendala dalam server kami, Silakan coba beberapa saat lagi."
            }
            (errorCode == 400 || (errorCode >= 402 && errorCode < 500)) -> {
                codes = NetworkErrorCode.ERROR_SERVER
                errorMessage = origin
            }
            else -> {
                codes = NetworkErrorCode.ERROR_SERVER
                errorMessage = "Mohon maaf, terjadi kendala dalam server kami, Silakan coba beberapa saat lagi."
            }
        }
        return ExceptionModel(errorMessage, origin, codes)
    }

    fun getErrorMessage(errorBody: JsonObject?): String {
        var error: String
        if (errorBody != null) {
            try {
                val errorJson: JsonObject = errorBody
                val errorData = parseErrorsMessage(errorJson)
                if (!errorData.isNullOrEmpty()) {
                    error = errorData.get(0)
                } else {
                    val json = errorJson.toString()
                    error = json
                }
            } catch (e: Exception) {
                error = "Mohon maaf, terjadi kendala dalam server kami, Silakan coba beberapa saat lagi."
                if (BuildConfig.DEBUG) {
                    error = "DEBUG: body data is not Json Object"
                }
                e.printStackTrace()
            }
        } else {
            error = "Mohon maaf, terjadi kendala dalam server kami, Silakan coba beberapa saat lagi."
            if (BuildConfig.DEBUG) {
                error = "DEBUG: body data is null"
            }
        }
        return error
    }

    private fun parseErrorsMessage(errorJson: JsonObject?): ArrayList<String>? =
        try {
            val errorArray: ArrayList<String> = java.util.ArrayList()
            if (errorJson != null) {
                if (errorJson["message"] != null) {
                    errorArray.add(errorJson["message"].asString)
                }
            }
            errorArray
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
}