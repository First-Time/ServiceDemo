package com.lyf.servicedemo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

fun dumpTaskAffinity(activity: BaseActivity) {
    val info = activity.packageManager.getApplicationInfo(
        activity.packageName,
        PackageManager.GET_META_DATA
    )
    log("taskAffinity: ${info.taskAffinity}")
}

fun printTaskInfo(activity: BaseActivity, methodName: String) {
    log("${activity.localClassName} $methodName taskId: ${activity.taskId} hashCode: ${activity.hashCode()}")
}

fun log(message: String, tag: String = "ServiceDemo") {
    Log.i(tag, message)
}

inline fun <reified T : AppCompatActivity> Context.toActivity() {
    startActivity(Intent(this, T::class.java))
}