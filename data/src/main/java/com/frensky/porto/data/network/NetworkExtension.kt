package com.frensky.porto.data.network

import com.frensky.porto.data.network.exception.APIErrorHelper
import com.frensky.porto.model.exception.APIError.APIAuthException
import com.frensky.porto.model.exception.APIError.APIConnectionException
import com.frensky.porto.model.exception.APIError.APIOtherException
import com.frensky.porto.model.exception.APIError.APIServerException
import com.frensky.porto.model.exception.APIException
import com.frensky.porto.model.exception.NetworkErrorCode
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response

fun <T> Response<T>.isValid(): Boolean = isSuccessful()

fun <T> Response<T>.getErrorData(): APIException {
    val errorBody = errorBody()
    val errorCode = code()
    val errorJson: JsonObject? =
        try {
            Gson().fromJson(errorBody?.charStream(), JsonObject::class.java)
        } catch (e: Exception) {
            null
        }

    val errorData = APIErrorHelper.generateErrorModel(errorJson, errorCode)
    val url = raw().request()?.url()?.toString() ?: ""

    when {
        (errorData.code == NetworkErrorCode.ERROR_CONNECTION) -> {
            return APIConnectionException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_SERVER) -> {
            return APIServerException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_AUTH) -> {
            return APIAuthException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_OTHER) -> {
            return APIOtherException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_MVP) -> {
            return APIServerException(errorData.message, errorData.message, errorData.code, url)
        }
        else -> {
            return APIServerException(errorData.message, errorData.origin, errorData.code, url)
        }
    }
}

fun <T> Response<T>.generateErrorException(
    errorBody: JsonObject?,
    errorCode: Int,
): APIException {
    val errorData = APIErrorHelper.generateErrorModel(errorBody, errorCode)
    val url = raw().request()?.url()?.toString() ?: ""

    when {
        (errorData.code == NetworkErrorCode.ERROR_CONNECTION) -> {
            return APIConnectionException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_SERVER) -> {
            return APIServerException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_AUTH) -> {
            return APIAuthException(errorData.message, errorData.origin, errorData.code, url)
        }
        (errorData.code == NetworkErrorCode.ERROR_OTHER) -> {
            return APIOtherException(errorData.message, errorData.origin, errorData.code, url)
        }
        else -> {
            return APIServerException(errorData.message, errorData.origin, errorData.code, url)
        }
    }
}