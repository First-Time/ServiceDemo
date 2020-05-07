package com.lyf.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class TestTwoService : Service() {
    private val TAG = "Service"
    private var isContinue = true
    private val className = TestTwoService::class.java.simpleName

    class MyBinder : Binder() {
        val service: TestTwoService
            get() = TestTwoService()
    }

    private val binder = MyBinder()

    private val generator = Random

    override fun onCreate() {
        Log.i(TAG, "$className - onCreate - Thread = ${Thread.currentThread().name}")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(
            TAG,
            "$className - onStartCommand - startId = $startId Thread = ${Thread.currentThread().name}"
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i(
            TAG, "$className - onBind - Thread = ${Thread.currentThread()
                .name}"
        )
        var number = 0
        Thread(Runnable {
            while (isContinue) {
                Log.i(
                    TAG,
                    "$className Thread ID = ${Thread.currentThread().id} number = ${number++}"
                )
                Thread.sleep(500)
                if (number == 10) {
                    isContinue = false
//                    stopSelf()
                }
            }
        }).start()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "$className - onUnbind - from = ${intent?.getStringExtra("from")}")
        isContinue = false
        return true
    }

    override fun onRebind(intent: Intent?) {
        Log.i(TAG, "$className - onRebind - Thread = ${Thread.currentThread().name}")
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Log.i(TAG, "$className - onDestroy - Thread = ${Thread.currentThread().name}")
        super.onDestroy()
    }

    fun getRandomNumber(): Int {
        return generator.nextInt()
    }
}
