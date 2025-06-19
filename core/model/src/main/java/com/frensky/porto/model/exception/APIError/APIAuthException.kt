package com.frensky.porto.model.exception.APIError

import com.frensky.porto.model.exception.APIException
import com.frensky.porto.model.exception.NetworkErrorCode.ERROR_AUTH

class APIAuthException(
    override val message: String,
    originMessage: String,
    errorCode: Int = ERROR_AUTH,
    urlRequest: String = "",
) : APIException(errorCode, message, originMessage, urlRequest)