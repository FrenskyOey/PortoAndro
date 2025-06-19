package com.frensky.porto.model.exception

class ServerException(
    val errorCode: Int,
    override val message: String,
    val originMessage: String,
    val urlRequest: String,
) : Exception(message)