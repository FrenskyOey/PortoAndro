package com.frensky.porto.model.exception

open class APIException(
    val errorCode: Int,
    override val message: String,
    val originMessage: String,
    val urlRequest: String,
) : Exception(message)