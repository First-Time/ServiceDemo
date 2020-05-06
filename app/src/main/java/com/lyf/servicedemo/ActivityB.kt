package com.lyf.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_b.*

class ActivityB : AppCompatActivity() {
    companion object {
        private const val TAG = "Service"
        private val className = ActivityB::class.java.simpleName
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
        setContentView(R.layout.activity_b)

        initListener()
    }

    private fun initListener() {
        btn_bind_service.setOnClickListener {
            val intent = Intent(this, TestTwoService::class.java)
            intent.putExtra("from", className)
            Log.i(TAG, "--------------------------------------------------")
            Log.i(TAG, "$className 执行 bindService")
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
        }

        btn_unbind_service.setOnClickListener {
            if (isBind) {
                Log.i(TAG, "--------------------------------------------------")
                Log.i(TAG, "$className 执行 unbindService")
                unbindService(conn)
            }
        }

        btn_finish.setOnClickListener {
            Log.i(TAG, "--------------------------------------------------")
            Log.i(TAG, "$className 执行 finish")
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "$className - onDestroy")
    }
}
