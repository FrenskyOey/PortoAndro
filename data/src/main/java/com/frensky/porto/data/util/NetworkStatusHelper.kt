package com.frensky.porto.data.util

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import com.frensky.porto.data.api.NetworkMonitor
import javax.inject.Inject

class NetworkStatusHelper
@Inject
constructor(
    val context: Context,
) : NetworkMonitor {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isConnected(): Boolean {
        var isConnected = false
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) isConnected = true
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) isConnected = true
        }
        return isConnected
    }
}