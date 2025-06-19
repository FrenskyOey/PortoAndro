package com.frensky.porto.model.exception.APIError

import com.frensky.porto.model.exception.APIException
import com.frensky.porto.model.exception.NetworkErrorCode.ERROR_SERVER

class APIServerException(
    override val message: String,
    originMessage: String,
    errorCode: Int = ERROR_SERVER,
    urlRequest: String = "",
) : APIException(errorCode, message, originMessage, urlRequest)