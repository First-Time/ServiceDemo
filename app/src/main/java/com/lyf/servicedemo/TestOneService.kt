package com.lyf.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestOneService : Service() {
    private val TAG = "Service"
    private var isContinue = true
    private val className = TestOneService::class.java.simpleName

    override fun onCreate() {
        Log.i(TAG, "$className onCreate - Thread ID = ${Thread.currentThread().id}")
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(
            TAG,
            "$className onStartCommand - startId = $startId Thread ID = ${Thread.currentThread().id}"
        )
        var number = 0
        Thread(Runnable {
            while (isContinue) {
                Log.i(
                    TAG,
                    "$className Thread ID = ${Thread.currentThread().id} number = ${number++}"
                )
                Thread.sleep(500)
                /*if (number == 10) {
                    stopSelf()
                }*/
            }
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i(TAG, "$className onBind - ThreadID = ${Thread.currentThread().id}")
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "$className onDestroy - Thread ID = ${Thread.currentThread().id}")
        isContinue = false
        super.onDestroy()
    }
}
