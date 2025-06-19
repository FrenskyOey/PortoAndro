package com.frensky.porto.model.exception.APIError

import com.frensky.porto.model.exception.APIException
import com.frensky.porto.model.exception.NetworkErrorCode.ERROR_CONNECTION

class APIConnectionException(
    override val message: String,
    originMessage: String,
    errorCode: Int = ERROR_CONNECTION,
    urlRequest: String = "",
) : APIException(errorCode, message, originMessage, urlRequest)