package com.frensky.porto.model.exception.APIError

import com.frensky.porto.model.exception.APIException
import com.frensky.porto.model.exception.NetworkErrorCode.ERROR_OTHER

class APIOtherException(
    override val message: String,
    originMessage: String,
    errorCode: Int = ERROR_OTHER,
    urlRequest: String = "",
) : APIException(errorCode, message, originMessage, urlRequest)