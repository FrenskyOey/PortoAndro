package com.frensky.porto.data.network.interceptor

import android.content.Context
import com.frensky.porto.data.api.NetworkMonitor
import com.frensky.porto.data.model.BaseResponse
import com.frensky.porto.data.network.ConnectionStatus
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class BasicInterceptor @Inject
constructor(
    private val context: Context,
    private val gson: Gson,
    private val networkHelper: NetworkMonitor,
) : Interceptor {
    companion object {
        const val KEY_HEADER_ACCEPT = "Accept"
        const val KEY_VERSION_CODE = "X-Version-Code"
        const val KEY_APP_VERSION = "X-App-Version"
        const val KEY_ANDROID_ID = "X-Android-ID"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (!networkHelper.isConnected()) {
            val error = BaseResponse<Any?>(null, "Network Error", false, ConnectionStatus.CONNECTION_ERROR)
            val response =
                Response
                    .Builder()
                    .protocol(Protocol.HTTP_1_1)
                    .message("Network Exception Error : CONNECTION_ERROR")
                    .request(originalRequest)
                    .code(ConnectionStatus.CONNECTION_ERROR)
                    .body(ResponseBody.create(MediaType.parse("application/json"), gson.toJson(error)))
            return response.build()
        }

        val requestBuilder =
            originalRequest
                .newBuilder()
                .addHeader(KEY_HEADER_ACCEPT, "application/json")
                .method(originalRequest.method(), originalRequest.body())


        //requestBuilder.addHeader(
          //  KEY_ANDROID_ID,
            //Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID),
        //)
        //requestBuilder.addHeader(KEY_VERSION_CODE, BuildConfig.VERSION_CODE.toString())
        //requestBuilder.addHeader(KEY_APP_VERSION, BuildConfig.VERSION_NAME)

        val newRequest = requestBuilder.build()
        try {
            return chain.proceed(newRequest)
        } catch (e: SocketTimeoutException) {
            val error =
                BaseResponse<Any?>(
                    null,
                    "Network Error",
                    false,
                    ConnectionStatus.TIMEOUT_CODE,
                )
            val response =
                Response
                    .Builder()
                    .protocol(Protocol.HTTP_1_1)
                    .message("Network Exception Error : " + e.message ?: "-")
                    .request(newRequest)
                    .code(ConnectionStatus.TIMEOUT_CODE)
                    .body(ResponseBody.create(MediaType.parse("application/json"), gson.toJson(error)))
            return response.build()
        } catch (e: UnknownHostException) {
            val error = BaseResponse<Any?>(null, "Network Error", false, ConnectionStatus.CONNECTION_ERROR)
            val response =
                Response
                    .Builder()
                    .protocol(Protocol.HTTP_1_1)
                    .message("Network Exception Error : " + e.message ?: "-")
                    .request(newRequest)
                    .code(ConnectionStatus.CONNECTION_ERROR)
                    .body(ResponseBody.create(MediaType.parse("application/json"), gson.toJson(error)))
            return response.build()
        } catch (e: Exception) {
            val error = BaseResponse<Any?>(null, "Network Error", false, ConnectionStatus.NETWORK_ERROR)
            val response =
                Response
                    .Builder()
                    .protocol(Protocol.HTTP_1_1)
                    .message("Network Exception Error : " + e.message ?: "-")
                    .request(newRequest)
                    .code(ConnectionStatus.NETWORK_ERROR)
                    .body(ResponseBody.create(MediaType.parse("application/json"), gson.toJson(error)))
            return response.build()
        }
    }
}
