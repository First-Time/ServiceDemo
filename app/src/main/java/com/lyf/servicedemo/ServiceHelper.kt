package com.lyf.servicedemo

import android.app.ActivityManager
import android.content.Context

/**
 * 判断Service是否正在运行
 */
fun isServiceAlive(context: Context, className: String): Boolean {
    val activityManager: ActivityManager =
        context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningServiceInfo = activityManager.getRunningServices(Int.MAX_VALUE)
    if (runningServiceInfo == null || runningServiceInfo.size == 0) return false
    runningServiceInfo.forEach {
        if (className == it.service.className) {
            return true
        }
    }
    return false
}