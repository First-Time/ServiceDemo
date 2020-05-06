package com.lyf.servicedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar.setDisplayHomeAsUpEnabled(false)

        initListener()

        Log.i(TAG, "Thread ID = ${Thread.currentThread().id}")

        serviceIntent = Intent(this, TestOneService::class.java)

        /*Log.i(TAG, "before StartService")

        //连续启动Service
        val intentOne = Intent(this, TestOneService::class.java)
        startService(intentOne)

        val intentTwo = Intent(this, TestOneService::class.java)
        startService(intentTwo)

        val intentThree = Intent(this, TestOneService::class.java)
        startService(intentThree)

        //停止Service
        val intentFour = Intent(this, TestOneService::class.java)
        stopService(intentFour)

        //再次启动Service
        val intentFive = Intent(this, TestOneService::class.java)
        startService(intentFive)

        Log.i(TAG, "after StartService")*/
    }

    private fun initListener() {
        btn_start_service.setOnClickListener {
            val componentName = startService(serviceIntent)
            componentName?.let { println(componentName.className) }
        }

        btn_stop_service.setOnClickListener {
            stopService(serviceIntent)
        }

        btn_start_activityA.setOnClickListener {
            startActivity(Intent(this, ActivityA::class.java))
        }
    }
}
