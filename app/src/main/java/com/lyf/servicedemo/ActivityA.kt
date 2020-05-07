package com.lyf.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_a.*

class ActivityA : AppCompatActivity() {
    private var isConnected = false

    companion object {
        private const val TAG = "Service"
        private val className = ActivityA::class.java.simpleName
        private lateinit var myService: TestTwoService
        private var isBind = false
    }

    private object conn : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBind = true
            val myBinder = service as TestTwoService.MyBinder
            myService = myBinder.service
            Log.i(TAG, "$className - onServiceConnected")
            val num = myService.getRandomNumber()
            Log.i(TAG, "$className - getRandomNumber = $num")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBind = false
            Log.i(TAG, "$className - onServiceDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        Log.i(TAG, "$className - onCreate - Thread = ${Thread.currentThread().name}")

        initListener()
    }

    private fun initListener() {
        btn_start_service.setOnClickListener {
            val intent = Intent(this, TestTwoService::class.java)
            startService(intent)
        }

        btn_stop_service.setOnClickListener {
            val intent = Intent(this, TestTwoService::class.java)
            stopService(intent)
        }

        btn_bind_service.setOnClickListener {
            val intent = Intent(this, TestTwoService::class.java)
            intent.putExtra("from", className)
            Log.i(TAG, "--------------------------------------------------")
            Log.i(TAG, "$className 执行 bindService")
            isConnected = bindService(intent, conn, Context.BIND_AUTO_CREATE)
        }

        btn_unbind_service.setOnClickListener {
            if (isConnected) {
                Log.i(TAG, "--------------------------------------------------")
                Log.i(TAG, "$className 执行 unbindService")
                unbindService(conn)
                isConnected = false
            }
        }

        btn_start_activityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            Log.i(TAG, "--------------------------------------------------")
            Log.i(TAG, "$className 启动 ActivityB")
            startActivity(intent)
        }

        btn_finish.setOnClickListener {
            Log.i(TAG, "--------------------------------------------------")
            Log.i(TAG, "$className 执行 finish")
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isConnected) {
            unbindService(conn)
            isConnected = false
        }
        Log.i(TAG, "$className - onDestroy")
    }
}
